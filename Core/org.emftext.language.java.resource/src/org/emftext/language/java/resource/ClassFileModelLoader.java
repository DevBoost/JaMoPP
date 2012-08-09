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
package org.emftext.language.java.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.bcel5_2_0.classfile.Attribute;
import org.apache.bcel5_2_0.classfile.ClassParser;
import org.apache.bcel5_2_0.classfile.JavaClass;
import org.apache.bcel5_2_0.classfile.Signature;
import org.apache.bcel5_2_0.classfile.Utility;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.annotations.AnnotationsFactory;
import org.emftext.language.java.arrays.ArraysFactory;
import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.generics.GenericsFactory;
import org.emftext.language.java.generics.QualifiedTypeArgument;
import org.emftext.language.java.generics.TypeArgument;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.TypeParametrizable;
import org.emftext.language.java.generics.UnknownTypeArgument;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.EnumConstant;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElement;
import org.emftext.language.java.types.TypesFactory;

/**
 * This class constructs a Java EMF-model from a class file using the
 * Byte Code Engineering Library (BCEL).
 */
public class ClassFileModelLoader {

	protected JavaClasspath javaClasspath = null;

	protected ClassifiersFactory qualifiersFactory = ClassifiersFactory.eINSTANCE;
	protected MembersFactory     membersFactory    = MembersFactory.eINSTANCE;
	protected TypesFactory       typesFactory      = TypesFactory.eINSTANCE;
	protected ParametersFactory  parametersFactory = ParametersFactory.eINSTANCE;
	protected AnnotationsFactory annotationsFactory = AnnotationsFactory.eINSTANCE;

	public ClassFileModelLoader(JavaClasspath javaClasspath) {
		this.javaClasspath = javaClasspath;
	}

	public CompilationUnit parse(InputStream inputStream, String classFileName) throws IOException {
		try {
			ClassParser classParser = new ClassParser(inputStream, classFileName);
			JavaClass myClass = classParser.parse();
			ConcreteClassifier classifier = constructClassifier(myClass);
			CompilationUnit cu = ContainersFactory.eINSTANCE.createCompilationUnit();
			cu.setName(classFileName);
			List<String> namespace1 = Arrays.asList(myClass.getClassName().split("\\."));
			List<String> namespace2 = Arrays.asList(namespace1.get(namespace1.size() - 1).split("\\$"));
			cu.getNamespaces().addAll(namespace1.subList(0, namespace1.size() - 1));
			if (myClass.getClassName().endsWith("$")) {
				//empty class name
				cu.getNamespaces().addAll(namespace2.subList(0, namespace2.size()));
			}
			else {
				cu.getNamespaces().addAll(namespace2.subList(0, namespace2.size() - 1));
			}
			cu.getClassifiers().add(classifier);
			return cu;
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage(), e);
		}
	}

	protected ConcreteClassifier constructClassifier(JavaClass clazz) {
		ConcreteClassifier emfClassifier = null;
		if (clazz.isEnum()) { //check first, because enum is also class
			emfClassifier = qualifiersFactory.createEnumeration();
		}
		else if (clazz.isAnnotation()) {
			emfClassifier = qualifiersFactory.createAnnotation();
		}
		else if (clazz.isClass()) {
			emfClassifier = qualifiersFactory.createClass();
		}
		else if (clazz.isInterface()) {
			emfClassifier = qualifiersFactory.createInterface();
		}
		else {
			assert(false);
			return null;
		}

		String className = clazz.getClassName();
		int idx = clazz.getClassName().lastIndexOf("$");
		if (idx >= 0) {
			className = className.substring(idx + 1);
		}
		else {
			idx = clazz.getClassName().lastIndexOf(".");
			if (idx >= 0) {
				className = className.substring(idx + 1);
			}
		}

		emfClassifier.setName(className);

		for(Attribute a : clazz.getAttributes()){
			String signature = a.toString();
			if(signature.startsWith("Signature(")) {
				EList<TypeParameter> tpList = constructTypeParameters(signature);
				emfClassifier.getTypeParameters().addAll(tpList);
			}
		}

		String typeArgumentSig = "";
		for(Attribute a : clazz.getAttributes()){
			String s = a.toString();
			s = s.replaceAll("\\[", "");
			if (s.startsWith("Signature(L")) {
				typeArgumentSig = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
			}
			if (s.startsWith("Signature(<")) {
				typeArgumentSig = s.substring(s.indexOf(";>L") + 2, s.indexOf(")"));
			}
		}

		//super class
		if (clazz.isClass() && !clazz.isEnum()) {
			org.emftext.language.java.classifiers.Class emfClass =
				(Class) emfClassifier;
			if (clazz.getSuperclassName() != null) {
				emfClass.setExtends(createReferenceToClassifier(clazz.getSuperclassName()));
				typeArgumentSig = constructTypeArguments(typeArgumentSig, (ClassifierReference)emfClass.getExtends(), null, emfClassifier);
			}
		}
		else {
			//cut away the Object signature which is there also for interfaces
			typeArgumentSig = constructTypeArguments(typeArgumentSig, null, null, emfClassifier);
		}

		//interfaces
		for(String ifName : clazz.getInterfaceNames()) {
			TypeReference typeArg = createReferenceToClassifier(ifName);

			typeArgumentSig = constructTypeArguments(typeArgumentSig, (ClassifierReference)typeArg, null, emfClassifier);
			if (clazz.isEnum()) { //check first, because enum is also class
				((Enumeration)emfClassifier).getImplements().add(typeArg);
			}
			else if (clazz.isAnnotation()) {
				//
			}
			else if (clazz.isClass()) {
				((Class)emfClassifier).getImplements().add(typeArg);
			}
			else if (clazz.isInterface()) {
				((Interface)emfClassifier).getExtends().add(typeArg);
			}
		}

		for(org.apache.bcel5_2_0.classfile.Field field : clazz.getFields()) {
			if (field.isEnum() && emfClassifier instanceof Enumeration) {
				((Enumeration)emfClassifier).getConstants().add(constructEnumConstant(field));
			}
			else {
				emfClassifier.getMembers().add(constructField(field, emfClassifier));
			}
		}
		for(org.apache.bcel5_2_0.classfile.Method method : clazz.getMethods()) {
			if(!method.isSynthetic()) {
				Member emfMember = constructMethod(method, emfClassifier, false);
				//If the last parameter has an array type it could also be a variable length parameter.
				//The java compiler compiles variable length arguments down to array arguments.
				//Then the arguments are wrapped into an array. As far as I know, there is no
				//way to tell the difference from byte code. Therefore we create two versions
				//of the method: one with array argument and one with variable length
				if (emfMember instanceof Method &&
						!((Method)emfMember).getParameters().isEmpty() &&
						!((Method)emfMember).getParameters().get(
								((Method)emfMember).getParameters().size()-1).getArrayDimensionsBefore().isEmpty()) {

					Member emfMethod2 = constructMethod(method, emfClassifier, true);
					emfClassifier.getMembers().add(emfMethod2);
				}
				else {
					emfClassifier.getMembers().add(emfMember);
				}
			}

		}

		constructModifiers(emfClassifier, clazz);

		return emfClassifier;
	}

	protected Member constructMethod(org.apache.bcel5_2_0.classfile.Method method, ConcreteClassifier emfClassifier, boolean withVariableLength) {
		Method emfMethod = null;
		if(emfClassifier instanceof Annotation) {
			emfMethod = annotationsFactory.createAnnotationAttribute();
		}
		else if(emfClassifier instanceof Interface) {
			emfMethod = membersFactory.createInterfaceMethod();
		}
		else {
			emfMethod = membersFactory.createClassMethod();
		}
		emfMethod.setName(method.getName());

		String signature = method.getReturnType().getSignature();
		String plainSignature = "";

		for(Attribute a : method.getAttributes()){
			if (a instanceof Signature) {
				String s = a.toString();
				// TODO can we replace this with replace("[", "")?
				s = s.replaceAll("\\[", "");
				plainSignature = s;
				break;
			}
		}
		((TypeParametrizable) emfMethod).getTypeParameters().addAll(constructTypeParameters(plainSignature));

		TypeReference typeRef = createReferenceToType(signature);
		TypeReference typeParamRef = constructReturnTypeParameterReference(plainSignature, emfMethod, emfClassifier);
		if(typeParamRef != null) {
			((TypeParameter)((ClassifierReference)typeParamRef).getTarget()).getExtendTypes().add(typeRef);
			typeRef = typeParamRef;
		}

		emfMethod.setTypeReference(typeRef);

		int arrayDimension = getArrayDimension(signature);
        for(int i = 0; i < arrayDimension; i++) {
        	emfMethod.getArrayDimensionsBefore().add(
        			ArraysFactory.eINSTANCE.createArrayDimension());
        }

        List<String> parameterNames = extractParameterNames(method);
        
		for(int i = 0; i < method.getArgumentTypes().length; i++) {
			org.apache.bcel5_2_0.generic.Type argType = method.getArgumentTypes()[i];
			String paramName;
			if (parameterNames.size() > i) {
				paramName = parameterNames.get(i);
			} else {
				paramName = "arg" + i;
			}
			if (i == method.getArgumentTypes().length - 1 && withVariableLength) {
				emfMethod.getParameters().add(
						constructVariableLengthParameter(argType, paramName));
			} else {
				emfMethod.getParameters().add(
						constructParameter(argType, paramName));
			}
		}

		EList<TypeReference> tpList = constructMethodTypeParameterReferences(plainSignature, (Method) emfMethod, emfClassifier);
		for(int i = 0; i<tpList.size(); i++) {
			TypeReference typeParameterReference = tpList.get(i);
			if(typeParameterReference != null) {
				TypeReference typeReference = emfMethod.getParameters().get(i).getTypeReference();
				if(typeReference instanceof ClassifierReference) {
					//replace with parameter there is one
					((TypeParameter)((ClassifierReference)typeParameterReference).getTarget()).getExtendTypes().add(typeReference);
					emfMethod.getParameters().get(i).setTypeReference(typeParameterReference);
				}
			}
		}

		if(!"".equals(plainSignature) && !(emfClassifier instanceof Enumeration)) {
			if(typeRef instanceof ClassifierReference) {
				String returnSignature = plainSignature.substring(0, plainSignature.lastIndexOf(")"));
				returnSignature = returnSignature.substring(returnSignature.lastIndexOf(")") + 1);
				constructTypeArguments(returnSignature, (ClassifierReference) typeRef, (TypeParametrizable) emfMethod, emfClassifier);
			}


			String argumentSignature = plainSignature.substring(plainSignature.lastIndexOf("(") + 1, plainSignature.indexOf(")"));
			for(Parameter parameter : emfMethod.getParameters()) {
				TypeReference parameterTypeRef = parameter.getTypeReference();
				if(parameterTypeRef instanceof ClassifierReference) {
					argumentSignature = constructTypeArguments(argumentSignature, (ClassifierReference) parameterTypeRef, emfMethod, emfClassifier);
				} else {
					argumentSignature = constructTypeArguments(argumentSignature, null, emfMethod, emfClassifier);
				}
			}

		}

		if (emfMethod.getName().equals("<init>")) {
			Constructor constructor = MembersFactory.eINSTANCE.createConstructor();
			constructor.getTypeParameters().addAll(emfMethod.getTypeParameters());
			constructor.getParameters().addAll(emfMethod.getParameters());
			constructor.setName(emfClassifier.getName());
			return constructor;
		}

		constructModifiers(emfMethod, method);

		return (Member) emfMethod;
	}

	protected Parameter constructParameter(org.apache.bcel5_2_0.generic.Type attrType, String paramName) {
		Parameter emfParameter = parametersFactory.createOrdinaryParameter();
		String signature = attrType.getSignature();
		TypeReference emfTypeReference = createReferenceToType(signature);
		emfParameter.setTypeReference(emfTypeReference);
		emfParameter.setName(paramName);

        int arrayDimension = getArrayDimension(signature);
        for(int i = 0; i < arrayDimension; i++) {
        	emfParameter.getArrayDimensionsBefore().add(
        			ArraysFactory.eINSTANCE.createArrayDimension());
        }

		return emfParameter;
	}

	protected Parameter constructVariableLengthParameter(org.apache.bcel5_2_0.generic.Type attrType, String paramName) {
		Parameter emfParameter = parametersFactory.createVariableLengthParameter();
		String signature = attrType.getSignature();
		TypeReference emfTypeReference = createReferenceToType(signature);
		emfParameter.setTypeReference(emfTypeReference);
		emfParameter.setName(paramName);

        int arrayDimension = getArrayDimension(signature) - 1;
        for(int i = 0; i < arrayDimension; i++) {
        	emfParameter.getArrayDimensionsBefore().add(
        			ArraysFactory.eINSTANCE.createArrayDimension());
        }

		return emfParameter;
	}

	protected Field constructField(org.apache.bcel5_2_0.classfile.Field field, ConcreteClassifier emfClassifier) {
		Field emfField = membersFactory.createField();
		emfField.setName(field.getName());
		String signature = field.getType().getSignature();

		String plainSignature = "";
		for(Attribute a : field.getAttributes()){
			String s = a.toString();
			if(s.startsWith("Signature(")) {
				s = s.replaceAll("\\[", "");
				plainSignature = s.substring(s.indexOf("(") + 1, s.lastIndexOf(")"));
				break;
			}
		}

		TypeReference typeRef = createReferenceToType(signature);
		TypeReference typeParamRef = constructReturnTypeParameterReference(plainSignature, emfField, emfClassifier);
		if(typeParamRef != null) {
			((TypeParameter)((ClassifierReference)typeParamRef).getTarget()).getExtendTypes().add(typeRef);
			typeRef = typeParamRef;
		}

		emfField.setTypeReference(typeRef);

		int arrayDimension = getArrayDimension(signature);
        for(int i = 0; i < arrayDimension; i++) {
        	emfField.getArrayDimensionsBefore().add(
        			ArraysFactory.eINSTANCE.createArrayDimension());
        }

		if(!"".equals(plainSignature) && typeRef instanceof ClassifierReference) {
			constructTypeArguments(plainSignature, (ClassifierReference) typeRef, null, emfClassifier);
		}

		constructModifiers(emfField, field);

		return emfField;
	}

	protected void constructModifiers(AnnotableAndModifiable emfMember, org.apache.bcel5_2_0.classfile.AccessFlags member) {
		ModifiersFactory f = ModifiersFactory.eINSTANCE;
		if (member.isAbstract()) {
			emfMember.getAnnotationsAndModifiers().add(f.createAbstract());
		}
		if (member.isFinal()) {
			emfMember.getAnnotationsAndModifiers().add(f.createFinal());
		}
		if (member.isNative()) {
			emfMember.getAnnotationsAndModifiers().add(f.createNative());
		}
		if (member.isPrivate()) {
			emfMember.getAnnotationsAndModifiers().add(f.createPrivate());
		}
		if (member.isProtected()) {
			emfMember.getAnnotationsAndModifiers().add(f.createProtected());
		}
		if (member.isPublic()) {
			emfMember.getAnnotationsAndModifiers().add(f.createPublic());
		}
		if (member.isStatic()) {
			emfMember.getAnnotationsAndModifiers().add(f.createStatic());
		}
		if (member.isStrictfp()) {
			emfMember.getAnnotationsAndModifiers().add(f.createStrictfp());
		}
		if (member.isSynchronized()) {
			emfMember.getAnnotationsAndModifiers().add(f.createSynchronized());
		}
		if (member.isTransient()) {
			emfMember.getAnnotationsAndModifiers().add(f.createTransient());
		}
		if (member.isVolatile()) {
			emfMember.getAnnotationsAndModifiers().add(f.createVolatile());
		}

		if(!member.isPrivate()) {
			if (emfMember instanceof ConcreteClassifier) {
				emfMember.getAnnotationsAndModifiers().add(f.createStatic());
			}
		}
	}

	protected EnumConstant constructEnumConstant(
			org.apache.bcel5_2_0.classfile.Field field) {

		EnumConstant enumConstant = membersFactory.createEnumConstant();
		enumConstant.setName(field.getName());
		return enumConstant;
	}

	protected ClassifierReference constructTypeParameterReference(String name, TypeParametrizable method, ConcreteClassifier emfClassifier) {
		TypeParameter typeParameter =  null;
		if (method != null) {
			for(TypeParameter cand : method.getTypeParameters()) {
				if(cand.getName().equals(name)) {
					typeParameter = cand;
				}
			}
		}
		if (typeParameter == null) {
			for(TypeParameter cand : emfClassifier.getTypeParameters()) {
				if(cand.getName().equals(name)) {
					typeParameter = cand;
				}
			}
		}
		//TODO #767: implement here

		if(typeParameter == null) {
			return null;
		}

		ClassifierReference classifierReference =
			TypesFactory.eINSTANCE.createClassifierReference();
		classifierReference.setTarget(typeParameter);

		return classifierReference;
	}

	protected ClassifierReference constructReturnTypeParameterReference(String signature, TypedElement element, ConcreteClassifier emfClassifier) {
		int idx = signature.indexOf(")T");
		if(idx != -1) {
			signature = signature.substring(idx + 2);
		}
		else  {
			idx = signature.indexOf("T");
			if (idx != 0) {
				return null;
			}
			signature = signature.substring(1);
		}

		idx = signature.indexOf(";");
		if(idx == -1) {
			return null;
		}
		String name = signature.substring(0,idx);

		TypeParameter typeParameter =  null;
		if (element instanceof TypeParametrizable) { //methods
			for(TypeParameter cand : ((TypeParametrizable)element).getTypeParameters()) {
				if(cand.getName().equals(name)) {
					typeParameter = cand;
				}
			}
		}
		if (typeParameter == null) {
			for(TypeParameter cand : emfClassifier.getTypeParameters()) {
				if(cand.getName().equals(name)) {
					typeParameter = cand;
				}
			}
		}

		if(typeParameter == null) {
			return null;
		}

		ClassifierReference classifierReference =
			TypesFactory.eINSTANCE.createClassifierReference();
		classifierReference.setTarget(typeParameter);

		return classifierReference;
	}

	protected EList<TypeReference> constructMethodTypeParameterReferences(String signature, Method method, ConcreteClassifier emfClassifier) {
		EList<TypeReference> result = new BasicEList<TypeReference>();

		int idx1 = signature.indexOf("((");
		if (idx1 == -1) {
			idx1 = signature.indexOf(">(");
		}
		int idx2 = signature.indexOf(")");

		if(idx1 == -1 || idx2 == -1) {
			return result;
		}

		signature = signature.substring(idx1 + 2, idx2);

		//cut away all the inner type arguments
		while(signature.contains("<")) {
			int idx = signature.indexOf("<");
			String start = signature.substring(0, idx);
			String end = signature.substring(idx + 1);
			int bracketCount = 1;
			while (bracketCount > 0) {
				if(end.startsWith("<")) {
					bracketCount++;
				}
				if(end.startsWith(">")) {
					bracketCount--;
				}
				end = end.substring(1,end.length());
			}
			signature = start + end;
		}

		while(signature.contains(";")) {
			int idx = signature.indexOf(";");
			if (signature.startsWith("T")) {
				String name = signature.substring(1,idx);
				TypeParameter typeParameter = null;
				for(TypeParameter cand : method.getTypeParameters()) {
					if(cand.getName().equals(name)) {
						typeParameter = cand;
					}
				}
				if (typeParameter == null) {
					for(TypeParameter cand : emfClassifier.getTypeParameters()) {
						if(cand.getName().equals(name)) {
							typeParameter = cand;
						}
					}
				}
				if(typeParameter == null) {
					return result;
				}

				ClassifierReference classifierReference =
					TypesFactory.eINSTANCE.createClassifierReference();
				classifierReference.setTarget(typeParameter);

				result.add(classifierReference);
			} else {
				result.add(null);
			}

			signature = signature.substring(signature.indexOf(";") + 1);
		}

		return result;
	}

	protected EList<TypeParameter> constructTypeParameters(String signature) {
		EList<TypeParameter> result = new BasicEList<TypeParameter>();
		if(signature.contains("((") || !signature.contains("<")) {
			//method without parameter
			return result;
		}
		signature = signature.substring(signature.indexOf("<") + 1);
		int endIdx = signature.indexOf(">(");
		if (endIdx > 0) {
			signature = signature.substring(0,endIdx);
		}

		while(signature.contains(":")) {
			int idx = signature.indexOf(":");
			String name = signature.substring(0,idx);
			if (!name.equals("")) {
				TypeParameter typeParameter = GenericsFactory.eINSTANCE.createTypeParameter();
				typeParameter.setName(name);
				result.add(typeParameter);
			}
			signature = signature.substring(idx + 1);

			int sepIdx = signature.indexOf(";");
			int colonIdx = signature.indexOf(":");
			while(sepIdx < colonIdx && sepIdx > 0) {
				signature = signature.substring(sepIdx + 1);
				sepIdx = signature.indexOf(";");
				colonIdx = signature.indexOf(":");
			}


		}

		return result;
	}


	protected TypeReference createReferenceToType(String signature) {
		TypeReference emfTypeReference = null;

		while(signature.startsWith("[")) {
			signature = signature.substring(1);
		}

        switch (signature.charAt(0)) {
	        case 'B':
	            emfTypeReference = TypesFactory.eINSTANCE.createByte();
	            break;
	        case 'C':
	            emfTypeReference = TypesFactory.eINSTANCE.createChar();
	            break;
	        case 'D':
	            emfTypeReference = TypesFactory.eINSTANCE.createDouble();
	            break;
	        case 'F':
	            emfTypeReference = TypesFactory.eINSTANCE.createFloat();
	            break;
	        case 'I':
	            emfTypeReference = TypesFactory.eINSTANCE.createInt();
	            break;
	        case 'J':
	            emfTypeReference = TypesFactory.eINSTANCE.createLong();
	            break;
	        case 'L': {
	        	// Full class name
	            String fullClassName = Utility.signatureToString(signature,false);
	        	emfTypeReference = createReferenceToClassifier(fullClassName);
	        	break;
	        }
	        case 'S':
	            emfTypeReference = TypesFactory.eINSTANCE.createShort();
	            break;
	        case 'Z':
	            emfTypeReference = TypesFactory.eINSTANCE.createBoolean();
	            break;
	        case 'V':
	            emfTypeReference = TypesFactory.eINSTANCE.createVoid();
	            break;
        }

        return emfTypeReference;
	}

	protected String constructTypeArguments(String s, ClassifierReference typeRef, TypeParametrizable method, ConcreteClassifier emfClassifier) {
		if ("".equals(s) || s == null) {
			return "";
		}

		char[] charArray = s.toCharArray();
		int bracketCount = 0;
		int begin = 0, end = 0;

		if(charArray[0] != 'L' &&
				charArray[0] != 'T' &&
				charArray[0] != '+' &&
				charArray[0] != '-') {
			return s.substring(1);
		}

		for(int i = 0; i < charArray.length; i++) {
			char next = charArray[i];


			if(next == ';' && bracketCount == 0) {
				return s.substring(i + 1);
			}
			if(next == '<') {
				if (bracketCount == 0) {
					begin = i + 1;
					if(charArray[begin] == '+' || charArray[begin] == '-') {
						begin = begin + 1;
					}
				}
				bracketCount++;
			}
			if(next == '>') {
				bracketCount--;
				if (bracketCount == 0) {
					end = i - 1;
					int internalBracketCount = 0;
					int internalBegin = begin, internalEnd = end;

					for(int j = begin; j <  end + 1; j++) {
						int followUpArgumentIdx = -1;
						char internalNext = charArray[j];
						if(internalNext == '<') {
							internalBracketCount++;
							followUpArgumentIdx = j;
						}
						if(internalNext == '>') {
							internalBracketCount--;
						}
						if((internalNext == ';') && internalBracketCount == 0) {
							internalEnd = j;

							if(charArray[internalBegin] == '*' || charArray[internalBegin] == '?') {
								UnknownTypeArgument typeArgument = GenericsFactory.eINSTANCE.createUnknownTypeArgument();
								if (typeRef != null) {
									typeRef.getTypeArguments().add(typeArgument);
								}
							}
							else {
								String fullName = s.substring(internalBegin + 1, internalEnd);
								if(followUpArgumentIdx != -1) {
									fullName = s.substring(internalBegin + 1, followUpArgumentIdx);
								}

								ClassifierReference argumentType;
								if(charArray[internalBegin] == 'T') {
									argumentType = constructTypeParameterReference(
											fullName, method, emfClassifier);
								}
								else {
									//strip type arguments
									int idx = fullName.indexOf("<");
									if (idx >= 0) {
										fullName = fullName.substring(0, idx);
									}
									argumentType = (ClassifierReference) createReferenceToClassifier(fullName);
								}



								if (typeRef != null) {
									TypeArgument typeArgument = null;
									if (argumentType == null) {
										typeArgument = GenericsFactory.eINSTANCE.createUnknownTypeArgument();
									} else {
										typeArgument = GenericsFactory.eINSTANCE.createQualifiedTypeArgument();
										((QualifiedTypeArgument) typeArgument).setTypeReference(argumentType);
									}
									typeRef.getTypeArguments().add(typeArgument);
								}

								//recursive call;
								constructTypeArguments(s.substring(internalBegin, internalEnd), argumentType, method, emfClassifier);
							}
							internalBegin = j + 1;
							if(charArray[internalBegin] == '+' || charArray[internalBegin] == '-') {
								internalBegin++;
							}
						}
					}



				}
			}
		}

		return "";
	}

	protected TypeReference createReferenceToClassifier(String fullClassifierName) {
		fullClassifierName = fullClassifierName.replaceAll("/", ".");
		Classifier classifier = (Classifier) javaClasspath.getClassifier(fullClassifierName);
		ClassifierReference classifierReference =
			TypesFactory.eINSTANCE.createClassifierReference();
		classifierReference.setTarget(classifier);
		return classifierReference;
	}

	protected int getArrayDimension(String signature) {
		int arrayDimension = 0;
		while(signature.startsWith("[")) {
			signature = signature.substring(1);
			arrayDimension++;
		}
		return arrayDimension;
	}
	
	protected List<String> extractParameterNames(final org.apache.bcel5_2_0.classfile.Method method) {
		final List<String> names = new ArrayList<String>();
		if (method.getLocalVariableTable() != null) {
			final int start = method.isStatic() ? 0 : 1;
			final int stop = method.isStatic() ? method.getArgumentTypes().length
					: method.getArgumentTypes().length + 1;
			final org.apache.bcel5_2_0.classfile.LocalVariable[] variables = method
					.getLocalVariableTable().getLocalVariableTable();
			if (variables != null) {
				for (int i = start; i < stop && i < variables.length; i++) {
					names.add(variables[i].getName());
				}
			}
		}
		return names;
	}

}
