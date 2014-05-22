/*******************************************************************************
 * Copyright (c) 2006-2014
 * Software Technology Group, Dresden University of Technology
 * DevBoost GmbH, Berlin, Amtsgericht Charlottenburg, HRB 140026
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Software Technology Group - TU Dresden, Germany;
 *   DevBoost GmbH - Berlin, Germany
 *      - initial API and implementation
 ******************************************************************************/
package org.emftext.language.java.extensions.generics;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.expressions.CastExpression;
import org.emftext.language.java.expressions.ConditionalExpression;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.NestedExpression;
import org.emftext.language.java.generics.ExtendsTypeArgument;
import org.emftext.language.java.generics.QualifiedTypeArgument;
import org.emftext.language.java.generics.TypeArgument;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.TypeParametrizable;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.emftext.language.java.literals.Super;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.references.ReflectiveClassReference;
import org.emftext.language.java.references.SelfReference;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.PrimitiveType;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElement;
import org.emftext.language.java.util.TemporalCompositeClassifier;
import org.emftext.language.java.util.TemporalTypeArgumentHolder;
import org.emftext.language.java.util.UniqueEList;

public class TypeParameterExtension {
	
	/**
	 * @return all type restrictions
	 */
	public static EList<ConcreteClassifier> getAllSuperClassifiers(TypeParameter me) {
		EList<ConcreteClassifier> result = new UniqueEList<ConcreteClassifier>();
		for (TypeReference typeRef : me.getExtendTypes()) {
			Type type = typeRef.getTarget();
			if (type instanceof ConcreteClassifier) {
				ConcreteClassifier concreteClassifier = (ConcreteClassifier) type;
				result.add(concreteClassifier);
			}
			if (type instanceof Classifier) {
				Classifier classifier = (Classifier) type;
				result.addAll(classifier.getAllSuperClassifiers());	
			}
		}		
		
		return result;
	}
	
	/**
	 * Returns all members of the given classifier including inner classes and 
	 * all members of super types (extended classes and implemented interfaces).
	 * 
	 * @param context to check protected visibility
	 * @return member list
	 */
	public static EList<Member> getAllMembers(TypeParameter me, Commentable context) {
		EList<Member> memberList = new UniqueEList<Member>();

		UniqueEList<Type> possiblyVisibleSuperClassifier = new UniqueEList<Type>();
		for (TypeReference typeReference : me.getExtendTypes()) {
			Type target = typeReference.getTarget();
			possiblyVisibleSuperClassifier.add(target);
		}
		
		for (ConcreteClassifier superClassifier : me.getAllSuperClassifiers()) {
			for (Member member : superClassifier.getMembers()) {
				if (member instanceof AnnotableAndModifiable) {					
					AnnotableAndModifiable modifiable = (AnnotableAndModifiable) member;

					if (!modifiable.isHidden(context)) {
						memberList.add(member);
					} else if (possiblyVisibleSuperClassifier.contains(superClassifier)) {
						memberList.add(member);
					}
				} else {
					memberList.add(member);
				}
			}
			memberList.addAll(superClassifier.getDefaultMembers());
		}
		return memberList;
	}
	
	/**
	 * Returns the type bound to the given parameter in the context of the given
	 * reference.
	 * 
	 * @param typeReference
	 * @param reference
	 * @return bound type or parameter if not bound
	 */
	public static Type getBoundType(TypeParameter me,
			TypeReference typeReference, Reference reference) {

		EList<Type> resultList = new BasicEList<Type>();
		
		TypeParametrizable typeParameterDeclarator = (TypeParametrizable) me.eContainer();
		Reference parentReference = null;
		EList<Type> prevTypeList = new UniqueEList<Type>();
		
		if (reference != null && 
				reference.getPrevious() instanceof NestedExpression) {
			
			NestedExpression nestedExpression = (NestedExpression) reference.getPrevious();
			Expression expression = null;
			Expression nestedExpressionExpression = nestedExpression.getExpression();
			if (nestedExpressionExpression instanceof Reference) {
				expression = nestedExpressionExpression;
			} else if (nestedExpressionExpression instanceof ConditionalExpression) {
				ConditionalExpression conditionalExpression = (ConditionalExpression) nestedExpressionExpression;
				expression = conditionalExpression.getExpressionIf();
			}
			
			if (expression instanceof Reference) {
				Reference expressionReference = (Reference) expression;
				// Navigate down references
				while (expressionReference.getNext() != null) {
					expressionReference = expressionReference.getNext();
				}
				
				parentReference = expressionReference;
				Type prevType = nestedExpressionExpression.getType();
				if (prevType instanceof TemporalCompositeClassifier) {
					TemporalCompositeClassifier temporalCompositeClassifier = (TemporalCompositeClassifier)prevType;
					for (EObject nextSuperType : temporalCompositeClassifier.getSuperTypes()) {
						prevTypeList.add((Type) nextSuperType);
					}
				}
				else {
					prevTypeList.add(prevType);
				}
			}
			else if (nestedExpressionExpression instanceof CastExpression) {
				CastExpression castExpression = (CastExpression) nestedExpressionExpression;
				prevTypeList.add(castExpression.getTypeReference().getTarget());
			}
		}
		else if (reference != null && reference.getPrevious() != null) {
			parentReference = reference.getPrevious();
			while (parentReference instanceof SelfReference) {
				if (((SelfReference)parentReference).getSelf() instanceof Super) {
					if (parentReference.eContainer() instanceof Reference) {
						parentReference = (Reference) parentReference.eContainer();
					}
					else {
						ConcreteClassifier containingClassifier = reference.getContainingConcreteClassifier();
						if (containingClassifier != null) {
							prevTypeList.add(containingClassifier);
						}
						parentReference = null;
					}
				}
				else {
					break;
				}
			}
			
			if (parentReference != null) {
				Type prevType = parentReference.getReferencedType();
				if (prevType instanceof TemporalCompositeClassifier) {
					TemporalCompositeClassifier temporalCompositeClassifier = (TemporalCompositeClassifier) prevType;
					for (EObject aType : temporalCompositeClassifier.getSuperTypes()) {
						prevTypeList.add((Type)aType);
					}
				}
				else {
					prevTypeList.add(prevType);
				}
			}
		}
		else if (reference != null) {
			// Prev type is one of the containing classes which can still bind
			// by inheritance
			ConcreteClassifier containingClassifier = reference.getContainingConcreteClassifier();
			while (containingClassifier != null) {
				prevTypeList.add(containingClassifier);
				EObject container = containingClassifier.eContainer();
				if (container instanceof Commentable) {
					Commentable commentableContainer = (Commentable) container;
					containingClassifier = commentableContainer.getContainingConcreteClassifier();
				} else {
					containingClassifier = null;
				}
			}
		}
		
		for (Type prevType : prevTypeList) {
			int typeParameterIndex = -1;
			if (typeParameterDeclarator instanceof ConcreteClassifier) {
				typeParameterIndex = typeParameterDeclarator.getTypeParameters().indexOf(me);
				if (reference != null) {
					ClassifierReference classifierReference = null;
					if (parentReference instanceof ElementReference) {
						ReferenceableElement prevReferenced = ((ElementReference) parentReference).getTarget();
						if (prevReferenced instanceof TypedElement) {
							TypeReference prevTypeReference = ((TypedElement) prevReferenced).getTypeReference ();
							if (prevTypeReference != null) {
								classifierReference = prevTypeReference.getPureClassifierReference(); 
							}
						}
					}
					
					if (parentReference instanceof TypedElement) {
						//e.g. New Constructor Call
						TypeReference prevParentReference = ((TypedElement)parentReference).getTypeReference ();
						if (prevParentReference != null) {
							classifierReference = prevParentReference.getPureClassifierReference(); 
						}
					}
					
					if (prevType instanceof ConcreteClassifier) {
						//bound through inheritance?
						int idx = 0;
						for (ClassifierReference superClassifierReference : ((ConcreteClassifier) prevType).getSuperTypeReferences()) {
							if (typeParameterIndex < superClassifierReference.getTypeArguments().size())  {
								//is this an argument for the correct class?
								if (typeParameterDeclarator.equals(superClassifierReference.getTarget()) ||
										((Classifier)superClassifierReference.getTarget()).getAllSuperClassifiers().contains(
												typeParameterDeclarator)) {					 
									TypeArgument arg = superClassifierReference.getTypeArguments().get(typeParameterIndex);
									if (arg instanceof QualifiedTypeArgument) {
										resultList.add(idx, ((QualifiedTypeArgument) arg).getTypeReference().getTarget());
										idx++;
									}
								}
	
							}
						}
						
						EList<TypeArgument> typeArgumentList;
						TemporalTypeArgumentHolder ttah = null;
						for (Adapter adapter : prevType.eAdapters()) {
							if (adapter instanceof TemporalTypeArgumentHolder) {
								ttah = (TemporalTypeArgumentHolder) adapter; 
								prevType.eAdapters().remove(ttah);
								break;
							}
						}
						if (ttah != null) {
							typeArgumentList = ttah.getTypeArguments();
						}
						else if (classifierReference != null) {
							typeArgumentList = classifierReference.getTypeArguments();
						}
						else {
							typeArgumentList = ECollections.emptyEList();
						}
						
						if (typeParameterIndex < typeArgumentList.size())  {
							TypeArgument arg = typeArgumentList.get(typeParameterIndex);
							if (arg instanceof QualifiedTypeArgument) {
								ClassifierReference theTypeRef = ((QualifiedTypeArgument) arg).getTypeReference().getPureClassifierReference();
								if (theTypeRef != null) {
									Type theType = theTypeRef.getBoundTarget(parentReference);
									if (theType != null) {
										if (!theTypeRef.getTypeArguments().isEmpty()) {
											ttah = new TemporalTypeArgumentHolder();
											ttah.getTypeArguments().addAll(theTypeRef.getTypeArguments());
											theType.eAdapters().add(ttah);
										}
										resultList.add(0, theType);
									}
								}
							}
							if (arg instanceof ExtendsTypeArgument) {
								for(TypeReference extendedType : ((ExtendsTypeArgument) arg).getExtendTypes()) {
									resultList.add(0, extendedType.getBoundTarget(parentReference));
								}
							}
						}
			
					}
					else if (prevType instanceof TypeParameter) {
						//the prev. type parameter, although unbound, may contain type restrictions through extends 
						resultList.add(prevType);
						for (TypeReference extendedRef : ((TypeParameter) prevType).getExtendTypes()) {
							ConcreteClassifier extended = (ConcreteClassifier)extendedRef.getTarget();
							int idx = ((TypeParametrizable)prevType.eContainer()).getTypeParameters().indexOf(prevType);
							if (extended.getTypeParameters().size() > idx) {
								//also add more precise bindings from extensions
								resultList.add(extended.getTypeParameters().get(idx));
							}
						}
					}
				}
				if (reference != null && reference.eContainer() instanceof ReflectiveClassReference) {
					if (reference.eContainer().eContainer() instanceof Reference) {
						//the ".class" instantiation implicitly binds the T parameter of java.lang.Class to the class itself
						resultList.add(0, ((Reference)reference.eContainer().eContainer()).getReferencedType());
					}
				}
			}
		}
		
		if (typeParameterDeclarator instanceof Method) {
			if (reference instanceof MethodCall) {
				Method method = (Method) typeParameterDeclarator;
				MethodCall methodCall = (MethodCall) reference;
				if (method.getTypeParameters().size() == methodCall.getCallTypeArguments().size()) {
					TypeArgument typeArgument = methodCall.getCallTypeArguments().get(method.getTypeParameters().indexOf(me));
					if (typeArgument instanceof QualifiedTypeArgument) {
						resultList.add(0, ((QualifiedTypeArgument)typeArgument).getTypeReference().getBoundTarget(parentReference)); 
					} 
				}

				//class type parameter
				int idx = method.getParameters().indexOf(typeReference.eContainer());
				
				//method type parameter
				if (idx == -1) {
					for (Parameter parameter : method.getParameters()) {
						for (TypeArgument typeArgument : parameter.getTypeArguments()) {
							if (typeArgument instanceof QualifiedTypeArgument) {
								if (((QualifiedTypeArgument) typeArgument).getTypeReference().getTarget().equals(me)) {
									idx = method.getParameters().indexOf(parameter);
								}
							}
						}
						ClassifierReference paramTypeReference = parameter.getTypeReference().getPureClassifierReference();
						if (paramTypeReference != null) {
							for (TypeArgument typeArgument : paramTypeReference.getTypeArguments()) {
								if (typeArgument instanceof QualifiedTypeArgument) {
									if (me.equals(((QualifiedTypeArgument) typeArgument).getTypeReference().getTarget())) {
										idx = method.getParameters().indexOf(parameter);
									}
								}
							}
						}
					}
				}
				
				if (idx < methodCall.getArguments().size() && idx >= 0) {
					Expression argument = methodCall.getArguments().get(idx);
					Parameter parameter = method.getParameters().get(idx);
					ClassifierReference parameterType = parameter.getTypeReference().getPureClassifierReference();
					if (argument instanceof NewConstructorCall) {
						ClassifierReference argumentType = ((NewConstructorCall)argument).getTypeReference().getPureClassifierReference();
						if (argumentType != null && parameterType.getTypeArguments().size() == argumentType.getTypeArguments().size()) {
							for (TypeArgument typeArgument : parameterType.getTypeArguments()) {
								if (typeArgument instanceof QualifiedTypeArgument) {
									if (((QualifiedTypeArgument) typeArgument).getTypeReference().getTarget().equals(me)) {
										resultList.add(0, ((QualifiedTypeArgument)argumentType.getTypeArguments().get(parameterType.getTypeArguments().indexOf(typeArgument))).getTypeReference(
											).getTarget());
									}
								}
							}
						}
						if (argumentType != null && parameterType.getTarget() instanceof TypeParameter) {
							resultList.add(0, argumentType.getTarget());
						}
					}
					else if (parameterType != null && argument instanceof Reference) {
						Reference argReference = (Reference) argument;
						
						while (argReference.getNext() instanceof Reference &&
								!(argReference.getNext() instanceof ReflectiveClassReference) ) {
							argReference = argReference.getNext();
						}
	
						
						if (argReference instanceof ElementReference) {
							ElementReference elementReference = (ElementReference) argReference;
							while (elementReference.getNext() instanceof ElementReference) {
								elementReference = (ElementReference) elementReference.getNext();
							}
							if (elementReference.getTarget() instanceof TypedElement) {
								TypeReference typeRef = ((TypedElement)elementReference.getTarget()).getTypeReference();
								if (typeRef != null) {
									ClassifierReference argumentType = typeRef.getPureClassifierReference();
									if (argumentType != null && parameterType.getTypeArguments().size() == argumentType.getTypeArguments().size()) {
										for (TypeArgument typeArgument : parameterType.getTypeArguments()) {
											if (typeArgument instanceof QualifiedTypeArgument) {
												if (((QualifiedTypeArgument) typeArgument).getTypeReference().getTarget().equals(me)) {
													int idx2 = parameterType.getTypeArguments().indexOf(typeArgument);
													if (argumentType.getTypeArguments().get(idx2) instanceof QualifiedTypeArgument) {
														resultList.add(0, ((QualifiedTypeArgument)argumentType.getTypeArguments().get(idx2)).getTypeReference().getTarget());
													}
													else if (argumentType.getTypeArguments().get(idx2) instanceof ExtendsTypeArgument) {
														for(TypeReference extendedType : ((ExtendsTypeArgument) argumentType.getTypeArguments().get(idx2)).getExtendTypes()) {
															resultList.add(0, extendedType.getTarget());
														}
													}
												}
											}
										}
									}
									if (argumentType != null && parameterType.getTarget() instanceof TypeParameter) {
										resultList.add(0,argumentType.getTarget());
									}
								}
							}
							if (elementReference.getNext() instanceof ReflectiveClassReference) {
								if (parameterType.getTypeArguments().size() == 1) {
									for (TypeArgument typeArgument : parameterType.getTypeArguments()) {
										if (typeArgument instanceof QualifiedTypeArgument) {
											if (((QualifiedTypeArgument) typeArgument).getTypeReference().getTarget().equals(me)) {
												resultList.add(0, elementReference.getReferencedType());
											}
										}
									}
								}
							}
						}
						else {
							if (parameterType.getTarget() instanceof TypeParameter) {
								while (argReference.getNext() instanceof Reference) {
									argReference = argReference.getNext();
								}
								resultList.add(0, ((Reference) argReference).getReferencedType());
							}
						}
					}			
				}
				
				//return type
				if (method.equals(typeReference.eContainer())) {
					//bound by the type of a method argument?
					EList<Classifier> allSuperTypes = null;
					for (Parameter parameter : method.getParameters()) {
						if (me.equals(parameter.getTypeReference().getTarget())) {
							idx = method.getParameters().indexOf(parameter);
							Classifier argumentType = (Classifier) methodCall.getArguments().get(idx).getType();
							if (allSuperTypes == null) {
								allSuperTypes = new UniqueEList<Classifier>();
								allSuperTypes.add(argumentType);
								allSuperTypes.addAll(argumentType.getAllSuperClassifiers());
							}
							else {
								allSuperTypes.add(argumentType);
								EList<Classifier> allOtherSuperTypes = new UniqueEList<Classifier>();
								allOtherSuperTypes.add(argumentType);
								allOtherSuperTypes.addAll(argumentType.getAllSuperClassifiers());
								EList<Classifier> temp = allSuperTypes;
								allSuperTypes = new UniqueEList<Classifier>();
								for (Classifier st : allOtherSuperTypes) {
									if (temp.contains(st)) {
										allSuperTypes.add(st);
									}
								}
							}
						}
	 				}
					//all types given by all bindings
					if (allSuperTypes != null) {
						resultList.addAll(allSuperTypes);
					}
				}
			}
		}
		
		//remove nulls
		for (Iterator<?> it = resultList.iterator(); it.hasNext(); ) {
			if (it.next() == null) {
				it.remove();
			}
		}
		
		if (resultList.isEmpty() || 
				(resultList.size() == 1 && resultList.get(0).equals(me))) {
			return me;
		}
		else {
			TemporalCompositeClassifier temp = new TemporalCompositeClassifier(me);
			for (Type aResult : resultList) {
				if (aResult instanceof PrimitiveType) {
					aResult = ((PrimitiveType) aResult).wrapPrimitiveType();
				}
				
				if (aResult instanceof TemporalCompositeClassifier) {
					//flatten
					temp.getSuperTypes().addAll(((TemporalCompositeClassifier) aResult).getSuperTypes());
				}
				else {
					temp.getSuperTypes().add((Classifier) aResult);	
				}
			}
			temp.getSuperTypes().add(me);
			return temp;
		}
	}
}
