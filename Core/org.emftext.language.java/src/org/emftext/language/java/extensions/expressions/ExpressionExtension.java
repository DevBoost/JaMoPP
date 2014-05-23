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
package org.emftext.language.java.extensions.expressions;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.arrays.ArrayInstantiationBySize;
import org.emftext.language.java.arrays.ArrayTypeable;
import org.emftext.language.java.expressions.AdditiveExpression;
import org.emftext.language.java.expressions.AndExpression;
import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.expressions.CastExpression;
import org.emftext.language.java.expressions.ConditionalAndExpression;
import org.emftext.language.java.expressions.ConditionalExpression;
import org.emftext.language.java.expressions.ConditionalOrExpression;
import org.emftext.language.java.expressions.EqualityExpression;
import org.emftext.language.java.expressions.ExclusiveOrExpression;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.InclusiveOrExpression;
import org.emftext.language.java.expressions.InstanceOfExpression;
import org.emftext.language.java.expressions.MultiplicativeExpression;
import org.emftext.language.java.expressions.NestedExpression;
import org.emftext.language.java.expressions.PrimaryExpression;
import org.emftext.language.java.expressions.RelationExpression;
import org.emftext.language.java.expressions.ShiftExpression;
import org.emftext.language.java.expressions.UnaryExpression;
import org.emftext.language.java.literals.Literal;
import org.emftext.language.java.members.AdditionalField;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.parameters.VariableLengthParameter;
import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;

public class ExpressionExtension {

	/**
	 * Returns the type of the expression considering all concrete subtypes of
	 * the Expression.
	 * 
	 * @return type of expression
	 */
	public static Type getType(Expression me) {
		return me.getOneType(false);
	}
	
	public static Type getAlternativeType(Expression me) {
		return me.getOneType(true);
	}
	
	public static Type getOneType(Expression me, boolean alternative) {
		org.emftext.language.java.classifiers.Class stringClass = me.getStringClass();
		
		Type type = null;

		if (me instanceof Reference) {
			Reference reference = (Reference) me;
			//navigate down references
			while(reference.getNext() != null) {
				reference = reference.getNext();
			}
			type = reference.getReferencedType();
		}
		else if (me instanceof Literal) {
			type = ((Literal) me).getType();
		}
		else if (me instanceof CastExpression) {
			type = ((CastExpression) me).getTypeReference().getTarget();
		}
		else if (me instanceof AssignmentExpression) {
			type = ((AssignmentExpression) me).getChild().getOneType(alternative);
		}
		else if (me instanceof ConditionalExpression &&
				((ConditionalExpression)me).getExpressionIf() != null) {
			if (alternative) {
				type = ((ConditionalExpression)me).getExpressionElse().getOneType(alternative);
			}
			else {
				type = ((ConditionalExpression)me).getExpressionIf().getOneType(alternative);
			}
			
		}
		else if (me instanceof EqualityExpression ||
				me instanceof RelationExpression ||
				me instanceof ConditionalOrExpression ||
				me instanceof ConditionalAndExpression ||
				me instanceof InstanceOfExpression) {
			type = me.getLibClass("Boolean");
		}
		else if (me instanceof AdditiveExpression ||
				me instanceof MultiplicativeExpression ||
				me instanceof InclusiveOrExpression ||
				me instanceof ExclusiveOrExpression ||
				me instanceof AndExpression ||
				me instanceof ShiftExpression) {
			
			if (me instanceof AdditiveExpression) {
				AdditiveExpression additiveExpression = (AdditiveExpression) me;
				for(Expression subExp : additiveExpression.getChildren()) {
					if (stringClass.equals(subExp.getOneType(alternative))) {
						//special case: string concatenation
						return stringClass;
					}
				}
			}
			
			@SuppressWarnings("unchecked")
			Expression subExp = ((EList<Expression>) 
					me.eGet(me.eClass().getEStructuralFeature("children"))).get(0);
			
			return subExp.getOneType(alternative);
		}
		else if (me instanceof UnaryExpression) {
			Expression subExp = ((UnaryExpression) me).getChild();
			
			return subExp.getOneType(alternative);
		}
		else for(TreeIterator<EObject> i = me.eAllContents(); i.hasNext(); ) {
			EObject next = i.next();
			Type nextType = null;

			if (next instanceof PrimaryExpression) {

				if (next instanceof Reference) {
					Reference ref = (Reference) next;
					//navigate down references
					while(ref.getNext() != null) {
						ref = ref.getNext();
					}
					next = ref;
				}
				if (next instanceof Literal) {
					nextType = ((Literal) next).getType();
				}
				else if (next instanceof CastExpression) {
					nextType = ((CastExpression)next).getTypeReference().getTarget();
				}
				else {
					nextType = ((Reference) next).getReferencedType();
				}
				i.prune();

			}
			if (nextType != null) {
				type = nextType;
				//in the special case that this is an expression with
				//some string included, everything is converted to string
				if (stringClass.equals(type)) {
					break;
				}
			}
		}
		//type can be null in cases of unresolved/unresolvable proxies
		return type;
	}
	
	public static long getArrayDimension(Expression me) {
		long size = 0;
		ArrayTypeable arrayType = null;
		if (me instanceof NestedExpression && 
				((NestedExpression)me).getNext() == null) {
			return ((NestedExpression) me).getExpression().getArrayDimension()
				- ((NestedExpression) me).getArraySelectors().size();
		}
		if (me instanceof ConditionalExpression &&
				((ConditionalExpression)me).getExpressionIf() != null) {		
			return ((ConditionalExpression)me).getExpressionIf().getArrayDimension();
		}
		if (me instanceof AssignmentExpression) {
			Expression value = ((AssignmentExpression) me).getValue();
			if (value == null) {
				return 0;
			}
			return value.getArrayDimension();
		}
		if (me instanceof InstanceOfExpression) {
			return 0;
		}
		if (me instanceof Reference) {
			Reference reference = (Reference) me;
			while (reference.getNext() != null) {
				reference = reference.getNext();
			}
			//an array clone? -> dimension defined by cloned array
			if (reference instanceof ElementReference && 
					reference.getPrevious() != null) {
				ReferenceableElement target = ((ElementReference)reference).getTarget();
				if (target instanceof Method) {
					if("clone".equals(((Method)target).getName())) {
						reference = (Reference) reference.eContainer();
					}
				}
			}
			
			if (reference instanceof ElementReference) {
				ElementReference elementReference = (ElementReference) reference;
				if (elementReference.getTarget() instanceof ArrayTypeable) {
					arrayType = (ArrayTypeable) elementReference.getTarget();
				}
				if (elementReference.getTarget() instanceof AdditionalLocalVariable) {
					AdditionalLocalVariable additionalLocalVariable = (AdditionalLocalVariable) elementReference.getTarget();
					arrayType = (LocalVariable) additionalLocalVariable.eContainer();
					size += additionalLocalVariable.getArrayDimensionsAfter().size();
					size -= arrayType.getArrayDimensionsAfter().size();
				}
				if (elementReference.getTarget() instanceof AdditionalField) {
					AdditionalField additionalField = (AdditionalField) elementReference.getTarget();
					arrayType = (Field) additionalField.eContainer();
					size += additionalField.getArrayDimensionsAfter().size();
					size -= arrayType.getArrayDimensionsAfter().size();
				}
			}
			else if (me instanceof ArrayTypeable) {
				size += ((ArrayTypeable) me).getArrayDimensionsBefore().size() + ((ArrayTypeable) me).getArrayDimensionsAfter().size();
				if (me instanceof VariableLengthParameter) {
					size++;
				}
			}
			size -= reference.getArraySelectors().size();
		}
		else if (me instanceof ArrayTypeable) {
			size += ((ArrayTypeable) me).getArrayDimensionsBefore().size() + ((ArrayTypeable) me).getArrayDimensionsAfter().size();
			if (me instanceof VariableLengthParameter) {
				size++;
			}
		}
		
		if (me instanceof ArrayInstantiationBySize) {
			size += ((ArrayInstantiationBySize) me).getSizes().size();
		}
		
		if(arrayType != null) {
			size += arrayType.getArrayDimension();
		}
		
		return size;
	}
}
