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
public final class Modifiers {
	
	native synchronized void method1();
	synchronized strictfp void method2() {
	}
	
	transient volatile Modifiers field;

	private class PrivateClass {
	}
	private final class PrivateFinalClass {
	}
	final private class FinalPrivateClass {
	}
	private abstract class PrivateAbstractClass {
	}
	abstract private class AbstractPrivateClass {
	}
	private static class PrivateStaticClass {
	}
	static private class StaticPrivateClass {
	}
	
	{
		// some instantiations to avoid warnings
		new PrivateClass();
		new PrivateFinalClass();
		new FinalPrivateClass();
		new PrivateAbstractClass() {};
		new AbstractPrivateClass() {};
		new PrivateStaticClass();
		new StaticPrivateClass();
	}

	protected class ProtectedClass {
	}
	protected final class ProtectedFinalClass {
	}
	final protected class FinalProtectedClass {
	}
	protected abstract class ProtectedAbstractClass {
	}
	abstract protected class AbstractProtectedClass {
	}
	protected static class ProtectedStaticClass {
	}
	static protected class StaticProtectedClass {
	}

	class DefaultClass {
	}
	final class DefaultFinalClass {
	}
	abstract class DefaultAbstractClass {
	}
	static class DefaultStaticClass {
	}

	public class PublicClass {
	}
	public final class PublicFinalClass {
	}
	final public class FinalPublicClass {
	}
	public abstract class PublicAbstractClass {
	}
	abstract public class AbstractPublicClass {
	}
	public static class PublicStaticClass {
	}
	static public class StaticPublicClass {
	}
}
