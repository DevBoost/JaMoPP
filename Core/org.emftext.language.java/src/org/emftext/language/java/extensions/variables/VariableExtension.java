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
package org.emftext.language.java.extensions.variables;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.parameters.VariableLengthParameter;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.variables.Variable;

public class VariableExtension {

	public static long getArrayDimension(Variable me) {
		long size = me.getArrayDimensionsBefore().size() + me.getArrayDimensionsAfter().size();
		if (me instanceof VariableLengthParameter) {
			size++;
		}
		return size;
	}
	
	/**
	 * Creates a statement that calls the method with the given name on this
	 * variable. If the variable's type does not offer such a method, null is
	 * returned.
	 * 
	 * @param methodName 
	 * @param arguments 
	 */
	public static ExpressionStatement createMethodCallStatement(Variable me, String methodName, EList<Expression> arguments) {
		
		ExpressionStatement callStatement = StatementsFactory.eINSTANCE.createExpressionStatement();
		callStatement.setExpression(me.createMethodCall(methodName, arguments));
		return callStatement;
	}

	/**
	 * Creates an expression that calls the method with the given name on this
	 * variable. If the variable's type does not offer such a method, null is
	 * returned.
	 * 
	 * @param methodName 
	 * @param arguments 
	 */
	public static IdentifierReference createMethodCall(Variable me, String methodName, EList<Expression> arguments) {
		
		IdentifierReference thisRef = ReferencesFactory.eINSTANCE.createIdentifierReference();
		thisRef.setTarget(me);
		MethodCall methodCall = ReferencesFactory.eINSTANCE.createMethodCall();
		Type thisType = me.getTypeReference().getTarget();
		if (thisType instanceof MemberContainer) {
			MemberContainer castedType = (MemberContainer) thisType;
			Method method = castedType.getContainedMethod(methodName);
			if (method == null) {
				return null;
			}
			methodCall.setTarget(method);
			// add arguments
			methodCall.getArguments().addAll(arguments);
			thisRef.setNext(methodCall);
			return thisRef;
		} else {
			return null;
		}
	}
}
