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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.JavaUniquePathConstructor;
import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.commons.NamespaceAwareElement;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportingElement;
import org.emftext.language.java.imports.PackageImport;
import org.emftext.language.java.imports.StaticClassifierImport;
import org.emftext.language.java.imports.StaticMemberImport;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.PackageReference;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.resource.java.analysis.helper.ScopedTreeWalker;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.util.TemporalFullNameHolder;

/**
 * A decider that looks for concrete classifiers.
 */
public class ConcreteClassifierDecider extends AbstractDecider {

	protected Resource resource;

	private EList<ConcreteClassifier> innerTypeSuperTypeList = new BasicEList<ConcreteClassifier>();
	private ConcreteClassifier baseClassifier = null;
	private boolean insideDefiningClassifier = true;

	private Commentable reference = null;

	/**
	 * @return true for statements lists
	 */
	public boolean containsCandidates(EObject container, EReference containingReference) {

		if (StatementsPackage.Literals.STATEMENT_CONTAINER__STATEMENT.equals(containingReference)) {
			return true;
		}
		if (StatementsPackage.Literals.STATEMENT_LIST_CONTAINER__STATEMENTS.equals(containingReference)) {
			return true;
		}
		return false;
	}

	/**
	 * @return inner classifiers (also of super classes) and imported classifiers
	 */
	public EList<? extends EObject> getAdditionalCandidates(String identifier, EObject container) {
		EList<EObject> resultList = new BasicEList<EObject>();

		if (container instanceof PackageReference) {
			PackageReference p = (PackageReference) container;
			String packageName = packageName(p);
			resultList.addAll(JavaClasspath.get(resource).getClassifiers(
					packageName, "*"));
		}

		if(container instanceof Classifier
				&& !container.equals(baseClassifier)) { //not if we come down from the extends reference
			Classifier classifier = (Classifier) container;

			//classifier itself has first priority
			resultList.add(classifier);

			if (!classifier.eIsProxy()) {
				for(Member member : classifier.getAllMembers(
						reference)) {
					if(member instanceof ConcreteClassifier) {
						innerTypeSuperTypeList.add((ConcreteClassifier) member);
					}
				}
				if (classifier instanceof ConcreteClassifier) {
					if (insideDefiningClassifier){
						innerTypeSuperTypeList.addAll(((ConcreteClassifier) classifier).getAllInnerClassifiers());

						insideDefiningClassifier = false;
						//isStatic = ModifiableUtil.isStatic((ConcreteClassifier)classifier);
					}
					else {
						EList<ConcreteClassifier> innerClassifierList = ((ConcreteClassifier) classifier).getAllInnerClassifiers();
						innerTypeSuperTypeList.addAll(innerClassifierList);

					}
				}
			}
		}

		if(container instanceof AnonymousClass) {
			for(Member member : ((AnonymousClass) container).getAllMembers(
					reference)) {
				if(member instanceof ConcreteClassifier) {
					innerTypeSuperTypeList.add((ConcreteClassifier) member);
				}
			}
			ConcreteClassifier superClassifier = ((AnonymousClass)container).getSuperClassifier();
			if (superClassifier != null) {
				innerTypeSuperTypeList.addAll(superClassifier.getAllInnerClassifiers());
			}
		}

		addImportsAndInnerClasses(container, resultList);

		//this is required for classes that contain '$' in their name
		if(container instanceof CompilationUnit && identifier.contains(JavaUniquePathConstructor.CLASSIFIER_SEPARATOR)) {
			String[] path = identifier.split("\\" + JavaUniquePathConstructor.CLASSIFIER_SEPARATOR, -1);
			EList<EObject> innerClassifiers = new BasicEList<EObject>(resultList);
			String outerName = null;
			outer: for(int i = 0; i < path.length; i++) {
				for(EObject cand : innerClassifiers) {
					if (cand instanceof ConcreteClassifier) {
						ConcreteClassifier innerClassifier = (ConcreteClassifier) cand;
						if(path[i].equals(innerClassifier.getName())) {
							innerClassifiers.clear();
							if (outerName != null) {
								outerName = outerName + path[i] + "$"; 
							}
							if (!innerClassifier.eIsProxy()) {
								if (outerName == null) {
									outerName = innerClassifier.getContainingCompilationUnit().getNamespacesAsString() + 
											innerClassifier.getName() + "$"; 
								}
								innerClassifiers.addAll(innerClassifier.getInnerClassifiers());
								for(ConcreteClassifier superClassifier : innerClassifier.getAllSuperClassifiers()) {
									innerClassifiers.addAll(
											superClassifier.getInnerClassifiers());
								}
							} else {
								//This special case is required in the unusual case that a class is name "ClassName$"
								//in this case "$" is not really a separator. JaMoPP things that ClassName
								//(without $) is a class. The proxy we have points at the class, but the
								//class does not exist as such.
								if (outerName == null) {
									outerName = ((InternalEObject) innerClassifier).eProxyURI().trimFragment().toString().substring(
											JavaUniquePathConstructor.JAVA_CLASSIFIER_PATHMAP.length());
									outerName = outerName.subSequence(
											0, outerName.length() - JavaUniquePathConstructor.JAVA_FILE_EXTENSION.length()) + "$";	
								}
								for(EObject innerClassifierProxy : JavaClasspath.get(container).getClassifiers(
										outerName, "*")) {
									innerClassifiers.add((ConcreteClassifier) EcoreUtil.resolve(
											innerClassifierProxy, container));
								}
							}
							if (i + 1 == path.length - 1) {
								resultList.addAll(innerClassifiers);
							}
							continue outer;
						}	
					}
				}

			}
		}
		
		return resultList;
	}

	private String packageName(PackageReference p) {
		String s = "";
		while (p != null) {
			s =  p.getName() + "." + s;
			EObject container = p.eContainer();
			if (container instanceof PackageReference) {
				p = (PackageReference) container;
			} else {
				p = null;
			}
		}
		return s;
	}

	private void addImportsAndInnerClasses(EObject container,
			EList<EObject> resultList) {
		//1) Inner classifiers of superclasses
		if(container instanceof JavaRoot) {
			resultList.addAll(innerTypeSuperTypeList);
		}
		if(container instanceof ImportingElement) {
			//2) direct classifier imports
			for(Import aImport : ((ImportingElement)container).getImports()) {
				if(aImport instanceof ClassifierImport) {
					resultList.add(((ClassifierImport)aImport).getClassifier());
				}
				else if (aImport instanceof StaticMemberImport) {
					StaticMemberImport staticMemberImport = (StaticMemberImport) aImport;
					if (!staticMemberImport.getStaticMembers().isEmpty()) {
						//access first element to trigger proxy resolution and avoid ConcurrentModificationException
						staticMemberImport.getStaticMembers().get(0);
					}
					resultList.addAll(staticMemberImport.getStaticMembers());
				}
				else if (aImport instanceof StaticClassifierImport) {
					resultList.addAll(aImport.getImportedMembers());
				}
			}
		}


		//3) same package
		if(container instanceof JavaRoot) {
			resultList.addAll(((JavaRoot) container).getClassifiersInSamePackage());
		}
		if(container instanceof ImportingElement) {
			//4) other packages
			EList<EObject> packageImports = new BasicEList<EObject>();
			for(Import aImport : ((ImportingElement)container).getImports()) {
				if(aImport instanceof PackageImport) {
					packageImports.addAll(aImport.getImportedClassifiers());
				}

			}
			//the last imported package has priority over the previous
			//ECollections.reverse(packageImports);
			resultList.addAll(packageImports);
		}
		//5) java.lang
		if(container instanceof JavaRoot || container.eContainer() == null) {
			resultList.addAll(org.emftext.language.java.JavaClasspath.get(container).getDefaultImports());
		}
	}

	/**
	 * @return true if the element is a concrete classifier with the correct name
	 */
	public boolean isPossibleTarget(String id, EObject element) {
		if (element instanceof ConcreteClassifier) {
			ConcreteClassifier concreteClassifier = (ConcreteClassifier)element;
			if(id.equals(concreteClassifier.getName())) {
				return true;
			}
			// this is needed because classifiers with '$'-signs in their name are
			// allowed which interferes with the usage of '$' as separator.
			if(id.contains("$")) {
				String mainID = id.substring(id.lastIndexOf("$") + 1);
				if (mainID.equals(concreteClassifier.getName())) {
					//set the full id for reprint
					if(concreteClassifier.eIsProxy()) {
						concreteClassifier = (ConcreteClassifier) EcoreUtil.resolve(concreteClassifier, resource);
					}
					TemporalFullNameHolder.setFullName(concreteClassifier, id);
					if(!concreteClassifier.eIsProxy()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * @return true for classifier references and references that are not method calls
	 */
	public boolean canFindTargetsFor(EObject referenceContainer,
			EReference crossReference) {

		if (!(referenceContainer instanceof Commentable)) {
			return false;
		}

		resource = referenceContainer.eResource();
		if(referenceContainer instanceof ClassifierReference) {
			if (referenceContainer.eContainer().eContainer() instanceof ConcreteClassifier) {
				baseClassifier = (ConcreteClassifier) referenceContainer.eContainer().eContainer();
			}
		}

		if(referenceContainer instanceof MethodCall) {
			return false;
		}

		reference = (Commentable) referenceContainer;
		return (referenceContainer instanceof Reference ||
				referenceContainer instanceof ClassifierReference);
	}

	/**
	 * This method assumes that the namespace of the given namespace aware element
	 * is relative to the scope given by the starting point element. That is,
	 * each element of the namespace points to a classifier, where the first element
	 * points to a classifier available in the scope given by the starting point
	 * (i.e., define locally or imported).
	 *
	 * @param nsaElement
	 * @param idx
	 * @param startingPoint
	 * @param referenceContainer
	 * @param crossReference
	 * @return the classifier to which the last part of the namespace points, or null, if any
	 *         part of the namespace can not be resolved to a classifier in the given scope
	 */
	public static EObject resolveRelativeNamespace(NamespaceAwareElement nsaElement, int idx,
			EObject startingPoint,
			EObject referenceContainer,
			EReference crossReference) {

		if (idx < nsaElement.getNamespaces().size()) {
			String identifier = nsaElement.getNamespaces().get(idx);
			EObject target = null;
			if (idx == 0) {
				List<IResolutionTargetDecider> deciderList = new ArrayList<IResolutionTargetDecider>();
				deciderList.add(new ConcreteClassifierDecider());
				deciderList.add(new TypeParameterDecider());
				ScopedTreeWalker treeWalker = new ScopedTreeWalker(deciderList);
				target = treeWalker.walk(startingPoint, identifier, referenceContainer, crossReference);
			}
			else {
				for(ConcreteClassifier cand : ((ConcreteClassifier)startingPoint).getAllInnerClassifiers()) {
					if (identifier.equals(cand.getName())) {
						target = cand;
						break;
					}
				}
			}

			if (target != null) {
				if (target.eIsProxy()) {
					target = EcoreUtil.resolve(target, referenceContainer);
				}
				return resolveRelativeNamespace(nsaElement, idx + 1, target, referenceContainer, crossReference);
			}
			else {
				return null;
			}
		}

		return startingPoint;
	}

}
