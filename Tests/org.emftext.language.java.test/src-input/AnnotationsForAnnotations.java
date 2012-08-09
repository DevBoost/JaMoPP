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
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AnnotationsForAnnotations {
	@Target({ElementType.FIELD,ElementType.METHOD, ElementType.ANNOTATION_TYPE})
	@Retention(RetentionPolicy.CLASS)
	@MyAnnotation(something=true)
	@MyAnnotation2(something=true)
	@SuppressWarnings("unchecked")
	public @interface MyAnnotation {
		@MyAnnotation(something=true)
		boolean something();
	}
	
	public @interface MyAnnotation2 {
		@MyAnnotation(something=true)
		boolean something();
	}
}
