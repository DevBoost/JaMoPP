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
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportingElement;
import org.emftext.language.java.imports.StaticClassifierImport;
import org.emftext.language.java.imports.StaticMemberImport;
import org.emftext.language.java.members.EnumConstant;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.Switch;
import org.emftext.language.java.statements.SwitchCase;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.util.TemporalCompositeClassifier;
import org.emftext.language.java.variables.LocalVariable;

/**
 * A decider that looks for enumeration constants.
 */
public class EnumConstantDecider extends AbstractDecider {

	private EObject reference = null;

	public boolean isPossibleTarget(String id, EObject element) {
		if (element instanceof EnumConstant) {
			NamedElement ne = (NamedElement) element;
			return id.equals(ne.getName());
		}
		return false;
	}

	public EList<? extends EObject> getAdditionalCandidates(String identifier, EObject container) {
		if (container instanceof Switch &&
				reference.eContainmentFeature().equals(StatementsPackage.Literals.CONDITIONAL__CONDITION) &&
				reference.eContainer() instanceof SwitchCase) {
			Switch aSwitch = (Switch) container;
			Type variableType = aSwitch.getVariable().getType();
			if (variableType instanceof Enumeration) {
				return ((Enumeration)variableType).getConstants();
			}
			if (variableType instanceof TemporalCompositeClassifier) {
				for(EObject superType : ((TemporalCompositeClassifier)variableType).getSuperTypes()) {
					if (superType instanceof Enumeration) {
						return ((Enumeration)superType).getConstants();
					}
				}
			}
		}
		if (container instanceof AssignmentExpression) {
			AssignmentExpression assignmentExpression = (AssignmentExpression) container;
			Type assignmentExpressionType = assignmentExpression.getType();
			if (assignmentExpressionType instanceof Enumeration) {
				return ((Enumeration)assignmentExpressionType).getConstants();
			}
		}
		if (container instanceof LocalVariable) {
			LocalVariable localVariable = (LocalVariable) container;
			Type assignmentExpressionType = localVariable.getTypeReference().getTarget();
			if (assignmentExpressionType instanceof Enumeration) {
				return ((Enumeration)assignmentExpressionType).getConstants();
			}
		}

		EList<EObject> resultList = addImports(container);

		return resultList;
	}

	private EList<EObject> addImports(EObject container) {
		if(container instanceof ImportingElement) {
			EList<EObject> resultList = new BasicEList<EObject>();
			for(Import aImport : ((ImportingElement)container).getImports()) {
				if (aImport instanceof StaticMemberImport) {
					resultList.addAll(((StaticMemberImport)aImport).getStaticMembers());
				}
				else if (aImport instanceof StaticClassifierImport) {
					resultList.addAll(aImport.getImportedMembers());
				}
				else if (aImport instanceof ClassifierImport) {
					for (EObject member : ((ClassifierImport)aImport).getClassifier().getMembers()) {
						if (member instanceof AnnotableAndModifiable) {
							if(((AnnotableAndModifiable)member).isStatic()) {
								resultList.add(member);
							}
						}
					}
				}
			}
			return resultList;
		}
		return null;
	}

	public boolean containsCandidates(EObject container, EReference containingReference) {
		if (ClassifiersPackage.Literals.ENUMERATION__CONSTANTS.equals(containingReference)) {
			return true;
		}
		return false;
	}


	public boolean canFindTargetsFor(EObject referenceContainer,
			EReference containingReference) {
		reference = referenceContainer;
		return referenceContainer instanceof Reference && !(referenceContainer instanceof MethodCall);
	}

}
