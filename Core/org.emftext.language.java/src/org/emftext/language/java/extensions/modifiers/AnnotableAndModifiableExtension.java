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
package org.emftext.language.java.extensions.modifiers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.literals.Self;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.AnnotationInstanceOrModifier;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Private;
import org.emftext.language.java.modifiers.Protected;
import org.emftext.language.java.modifiers.Public;
import org.emftext.language.java.modifiers.Static;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.references.SelfReference;
import org.emftext.language.java.types.Type;

public class AnnotableAndModifiableExtension {

	/**
	 * Sets the visibility of this element to <code>private</code>.
	 */
	public static void makePrivate(AnnotableAndModifiable me) {
		if (me.isPrivate()) {
			return;
		}
		me.removeModifier(Public.class);
		me.removeModifier(Protected.class);
		me.getAnnotationsAndModifiers().add(ModifiersFactory.eINSTANCE.createPrivate());
	}
	
	/**
	 * Sets the visibility of this element to <code>public</code>.
	 */
	public static void makePublic(AnnotableAndModifiable me) {
		if (me.isPublic()) {
			return;
		}
		me.removeModifier(Private.class);
		me.removeModifier(Protected.class);
		me.getAnnotationsAndModifiers().add(ModifiersFactory.eINSTANCE.createPublic());
	}
	
	/**
	 * Sets the visibility of this element to <code>protected</code>.
	 */
	public static void makeProtected(AnnotableAndModifiable me) {
		if (me.isProtected()) {
			return;
		}
		me.removeModifier(Private.class);
		me.removeModifier(Public.class);
		me.getAnnotationsAndModifiers().add(ModifiersFactory.eINSTANCE.createProtected());
	}
	
	/**
	 * Removes all modifiers from this element.
	 */
	public static void removeAllModifiers(AnnotableAndModifiable me) {
		List<Modifier> modifiers = me.getModifiers();
		EList<AnnotationInstanceOrModifier> elements = me.getAnnotationsAndModifiers();
		elements.removeAll(modifiers);
	}
	
	/**
	 * Returns an unmodifiable list of the modifiers that apply to this element.
	 */
	public static EList<Modifier> getModifiers(AnnotableAndModifiable me) {
		EList<AnnotationInstanceOrModifier> elements = me.getAnnotationsAndModifiers();
		EList<Modifier> modifiers = new BasicEList<Modifier>(); 
		for (AnnotationInstanceOrModifier next : elements) {
			if (next instanceof Modifier) {
				modifiers.add((Modifier) next);
			}
		}
		return ECollections.unmodifiableEList(modifiers);
	}
	
	/**
	 * Returns an unmodifiable list of the annotations that apply to this element.
	 */
	public static EList<AnnotationInstance> getAnnotationInstances(AnnotableAndModifiable me) {
		EList<AnnotationInstanceOrModifier> elements = me.getAnnotationsAndModifiers();
		EList<AnnotationInstance> annotations = new BasicEList<AnnotationInstance>(); 
		for (AnnotationInstanceOrModifier next : elements) {
			if (next instanceof AnnotationInstance) {
				annotations.add((AnnotationInstance) next);
			}
		}
		return ECollections.unmodifiableEList(annotations);
	}
	
	/**
	 * Adds the given type of modifier to this element. This method does not
	 * check for duplicate modifiers!
	 * 
	 * @param newModifier the modifier to add
	 */
	public static void addModifier(AnnotableAndModifiable me, Modifier newModifier) {
		me.getAnnotationsAndModifiers().add(newModifier);
	}

	/**
	 * Removes the given type of modifier from this element.
	 * 
	 * @param modifierType
	 */
	public static void removeModifier(AnnotableAndModifiable me, Class<?> modifierType) {
		List<AnnotationInstanceOrModifier> modifiers = me.getAnnotationsAndModifiers();
		List<AnnotationInstanceOrModifier> modifiersToRemove = new ArrayList<AnnotationInstanceOrModifier>();
		for (AnnotationInstanceOrModifier modifier : modifiers) {
			if (modifierType.isInstance(modifier)) {
				modifiersToRemove.add(modifier);
			}
		}
		modifiers.removeAll(modifiersToRemove);
	}
	
	public static boolean isPublic(AnnotableAndModifiable me) {
		return me.hasModifier(Public.class);
	}
	
	public static boolean isPrivate(AnnotableAndModifiable me) {
		return me.hasModifier(Private.class);
	}
	
	public static boolean isProtected(AnnotableAndModifiable me) {
		return me.hasModifier(Protected.class);
	}
	
	/**
	 * Checks whether this element has an modifier of the given type.
	 * @param type 
	 */
	public static boolean hasModifier(AnnotableAndModifiable me, Class<?> type) {
		List<AnnotationInstanceOrModifier> modifiers = me.getAnnotationsAndModifiers();
		for (AnnotationInstanceOrModifier modifier : modifiers) {
			if (type.isInstance(modifier)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns <code>true</code> if this element is static (either by an
	 * explicit modifier <code>static</code> or because this element is part of
	 * an interface).
	 */
	public static boolean isStatic(AnnotableAndModifiable me) {
		//all members of an interface are static by default
		if (me.eContainer() instanceof Interface) {
			return true;
		}
		
		for (AnnotationInstanceOrModifier modifier : me.getAnnotationsAndModifiers()) {
			if (modifier instanceof Static) {
				return true;
			}
		}
		return false;
	}

	public static boolean isHidden(AnnotableAndModifiable me, Commentable context) {
		Commentable	lContext = context;
		if (me.eIsProxy()) {
			return false;
		}
		//all members of an interface are public by default
		if (me.eContainer() instanceof Interface) {
			return false;
		}
		
		if (lContext.eIsProxy()) {
			lContext = (Commentable) EcoreUtil.resolve(lContext, me);
		}
		
		ConcreteClassifier lContextClassifier = lContext.getContainingConcreteClassifier(); 
		if (!(me.eContainer() instanceof Commentable)) {
			return true;
		}
		ConcreteClassifier myClassifier = ((Commentable) me.eContainer()).getParentConcreteClassifier();
		//special case: self reference to outer instance
		if (lContext instanceof Reference) {
			if (((Reference)lContext).getPrevious() instanceof SelfReference) {
				SelfReference selfReference = (SelfReference) ((Reference)lContext).getPrevious();
				if (selfReference.getSelf() instanceof Self) {
					if (selfReference.getPrevious() != null) {
						Type type = selfReference.getPrevious().getReferencedType();
						if (type instanceof ConcreteClassifier) {
							lContextClassifier = (ConcreteClassifier) type;
						}
					}
				}
			}
		}
		
		for (AnnotationInstanceOrModifier modifier : me.getAnnotationsAndModifiers()) {
			if (modifier instanceof Private) {
				if (myClassifier.equalsType(0, lContextClassifier, 0)) {
					return false;
				}
				return true;
			}
			if(modifier instanceof Public) {
				return false;
			}
			if(modifier instanceof Protected) {
				//package visibility
				if (me.getContainingPackageName() != null && 
						me.getContainingPackageName().equals(lContext.getContainingPackageName())) {
					return false;
				}
				//try outer classifiers as well 
				while(lContextClassifier instanceof Classifier) {
					if (lContextClassifier.isSuperType(0, myClassifier, null)) {
						return false;
					}
					
					EObject container = lContextClassifier.eContainer();
					if (container instanceof Commentable) {
						lContextClassifier = ((Commentable) container).getParentConcreteClassifier();
					} else {
						lContextClassifier = null;
					}
					
					if (lContextClassifier != null && !lContextClassifier.eIsProxy() && 
							lContextClassifier.isSuperType(0, myClassifier, null)) {
						return false;
					}
				}
				//visibility through anonymous subclass
				AnonymousClass anonymousClass = lContext.getContainingAnonymousClass();
				while (anonymousClass != null) {
					lContextClassifier = anonymousClass.getSuperClassifier();
					if (lContextClassifier == null) {
						return true;
					}
					if (lContextClassifier.isSuperType(0, myClassifier, null)) {
						return false;
					}
					
					EObject container = anonymousClass.eContainer();
					if (container instanceof Commentable) {
						anonymousClass = ((Commentable) container).getContainingAnonymousClass();
					} else {
						anonymousClass = null;
					}
				}
				return true;
			}
		}
		//package visibility?
		if (me.getContainingPackageName() != null && 
				me.getContainingPackageName().equals(lContext.getContainingPackageName())) {
			return false;
		}

		return true;
	}
}

