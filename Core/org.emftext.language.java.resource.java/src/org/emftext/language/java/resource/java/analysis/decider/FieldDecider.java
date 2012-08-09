/*******************************************************************************
 * Copyright (c) 2006-2012
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
package org.emftext.language.java.resource.java.analysis.decider;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportingElement;
import org.emftext.language.java.imports.StaticClassifierImport;
import org.emftext.language.java.imports.StaticMemberImport;
import org.emftext.language.java.members.AdditionalField;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.references.ReflectiveClassReference;
import org.emftext.language.java.references.SelfReference;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypesFactory;

/**
 * A decider that looks for fields declared in a classifier.
 */
public class FieldDecider extends AbstractDecider {

	private Field standardArrayLengthField = null;

	private Reference fieldReference = null;

	public Field getArrayLengthFiled(Commentable objectContext) {
		if (standardArrayLengthField  == null) {
			standardArrayLengthField = MembersFactory.eINSTANCE.createField();
			standardArrayLengthField.setName("length");
			ClassifierReference typeReference = TypesFactory.eINSTANCE.createClassifierReference();
			typeReference.setTarget(objectContext.getLibClass("Integer"));
			standardArrayLengthField.setTypeReference(typeReference);
		}
		return standardArrayLengthField;
	}

	private boolean insideDefiningClassifier = true;
	private boolean isStatic = false;

	public EList<? extends EObject> getAdditionalCandidates(String identifier, EObject container) {
		EList<EObject> resultList = new BasicEList<EObject>();
		if (container instanceof Classifier) {
			if (container instanceof ConcreteClassifier && insideDefiningClassifier){
				EList<Member> memberList =
					((Classifier)container).getAllMembers(fieldReference);
				for(Member member : memberList) {
					if (member instanceof Field) {
						resultList.add(member);
						resultList.addAll(((Field)member).getAdditionalFields());
					}
				}
				insideDefiningClassifier = false;
				isStatic = ((ConcreteClassifier)container).isStatic();
			}
			else {
				EList<Member> memberList =
					((Classifier)container).getAllMembers(fieldReference);
				for(Member member : memberList) {
					if (member instanceof Field) {
						if (!isStatic || ((Field)member).isStatic()) {
							resultList.add(member);
							resultList.addAll(((Field)member).getAdditionalFields());
						}
					}
				}
			}
		}

		if (container instanceof AnonymousClass) {
			resultList.addAll(((AnonymousClass)container).getMembers());

			EList<Member> memberList =
				((AnonymousClass)container).getAllMembers(fieldReference);
			for(Member member : memberList) {
				if (member instanceof Field) {
					resultList.add(member);
					resultList.addAll(((Field)member).getAdditionalFields());
				}
			}
			return resultList;
		}

		if(container instanceof CompilationUnit) {
			addImports(container, resultList);
			addArrayLengthFiled(resultList, (CompilationUnit) container);
		}

		return resultList;
	}

	private void addArrayLengthFiled(EList<EObject> resultList, Commentable objectContext) {
		//Arrays have the additional member field "length"
		//We always add the field since we do not know if we have an array or not
		resultList.add(getArrayLengthFiled(objectContext));
	}

	private void addImports(EObject container,
			EList<EObject> resultList) {
		if(container instanceof ImportingElement) {
			for(Import aImport : ((ImportingElement)container).getImports()) {
				if (aImport instanceof StaticMemberImport) {
					resultList.addAll(((StaticMemberImport)aImport).getStaticMembers());
				}
				else if (aImport instanceof StaticClassifierImport) {
					resultList.addAll(aImport.getImportedMembers());
				}
			}
		}
	}

	public boolean isPossibleTarget(String id, EObject element) {
		if (element instanceof Field || element instanceof AdditionalField) {
			NamedElement ne = (NamedElement) element;
			return id.equals(ne.getName());
		}
		return false;
	}

	public boolean containsCandidates(EObject container, EReference containingReference) {
		return false;
	}

	public boolean walkInto(EObject element) {
		return false;
	}

	public boolean canFindTargetsFor(EObject referenceContainer,
			EReference containingReference) {
		if (referenceContainer instanceof MethodCall) {
			return false;
		}
		if (!(referenceContainer instanceof Reference)) {
			return false;
		}
		Reference reference = (Reference) referenceContainer;
		if (reference.getNext() instanceof ReflectiveClassReference) {
			return false;
		}
		if (reference.getNext() instanceof SelfReference) {
			return false;
		}
		fieldReference = (Reference) referenceContainer;
		return true;
	}

}
