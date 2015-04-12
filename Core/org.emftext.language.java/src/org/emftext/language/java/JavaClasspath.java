/*******************************************************************************
 * Copyright (c) 2006-2015
 * Software Technology Group, Dresden University of Technology
 * DevBoost GmbH, Dresden, Amtsgericht Dresden, HRB 34001
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Software Technology Group - TU Dresden, Germany;
 *   DevBoost GmbH - Dresden, Germany
 *      - initial API and implementation
 ******************************************************************************/
package org.emftext.language.java;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;

/**
 * This class is responsible for managing and retrieving Java resources to establish inter-model references between
 * different Java classes represented as EMF-models.
 */
public class JavaClasspath extends AdapterImpl {

	/**
	 * Initializers can be registered to initialize each newly created classpath. An initializer may be used to connect
	 * JaMoPP with other Java tooling (e.g., the Eclipse JDT IDE) by reading the classpath from the other tool.
	 * <p>
	 * Initializers can be registered at the classpath via {@link JavaClasspath#getInitializers()}. Inside Eclipse, the
	 * extension point <i>org.emftext.language.java.java_classpath_initializer</i> may also be used for this.
	 */
	public static interface Initializer {

		/**
		 * Initializes the classpath. It is called as soon as the first resource of the resource set with which the
		 * classpath is associated accesses the classpath.
		 * 
		 * @param resource
		 *            One resource of the associated resource set that gives context for initializing the classpath
		 *            (e.g., the URI of the resource can be analyzed).
		 */
		void initialize(Resource resource);

		/**
		 * @return Should be <code>true</code>, if the classpath depends on the resource that is passed to the
		 *         {@link Initializer#initialize(Resource)} method. If one of the registered initializers returns
		 *         <code>true</code>, it enforces the usage an individual classpath for each resource set. The
		 *         {@link JavaClasspath#OPTION_USE_LOCAL_CLASSPATH} option can still be used to override this.
		 */
		boolean requiresLocalClasspath();

		/**
		 * @return <code>false</code>, if the standard lib is provided by the initializer itself and should therefore
		 *         not be registered based on the currently running JVM. If only one of the registered initializers
		 *         returns <code>false</code>, the JVM's standard lib is not registered. The
		 *         {@link JavaClasspath#OPTION_REGISTER_STD_LIB} option can still be used to override this.
		 */
		boolean requiresStdLib();
	}

	private static class InitializerExtensionPointReader {

		private static void read() {
			if (!Platform.isRunning()) {
				return;
			}

			IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
			IConfigurationElement configurationElements[] = extensionRegistry
					.getConfigurationElementsFor(EP_JAVA_CLASSPATH_INITIALIZER);
			for (IConfigurationElement element : configurationElements) {
				try {
					String type = element.getAttribute("class");
					if (type == null) {
						continue;
					}
					
					Initializer initializer = (Initializer) element.createExecutableExtension("class");
					initializers.add(initializer);
				} catch (CoreException ce) {
					String contributingPluginID = element.getDeclaringExtension().getContributor().getName();
					ILog log = Platform.getLog(Platform.getBundle(contributingPluginID));
					IStatus status = new Status(IStatus.ERROR, contributingPluginID, 0,
							"Error instantiating Java classpath initializer", ce);
					log.log(status);
				}
			}
		}
	}

	public static final String EP_JAVA_CLASSPATH_INITIALIZER = "org.emftext.language.java.java_classpath_initializer";

	private static Set<Initializer> initializers = null;

	public static Set<Initializer> getInitializers() {
		if (initializers == null) {
			initializers = new LinkedHashSet<Initializer>();
			readInitializersExtensionPoint();
		}
		return initializers;
	}

	private static void readInitializersExtensionPoint() {
		try {
			java.lang.Class.forName("org.eclipse.core.runtime.Platform");
		} catch (ClassNotFoundException e) {
			// running outside Eclipse
			return;
		}
		InitializerExtensionPointReader.read();
	}

	private static void initialize(Resource resource) {
		for (Initializer initializer : getInitializers()) {
			initializer.initialize(resource);
		}
	}

	/**
	 * If this option is set to <code>true</code> in a resource set, each classifier loaded is registered in the URI map
	 * of the resource set's <code>URIConverter</code>.
	 * <p>
	 * If the option is set to <code>false</code> (default) or not set at all, each classifier loaded is registered in
	 * the global <code>URIConverter.URI_MAP</code>.
	 */
	public static final String OPTION_USE_LOCAL_CLASSPATH = "OPTION_USE_LOCAL_CLASSPATH";

	/**
	 * If this option is set to <code>true</code> (default) in a resource set, the Java standard library (i.e., rt.jar
	 * or classes.jar) is registered automatically based on <code>System.getProperty("sun.boot.class.path")</code>.
	 */
	public static final String OPTION_REGISTER_STD_LIB = "OPTION_REGISTER_STD_LIB";

	/**
	 * If this option is set to <code>true</code> in a resource set, all names in a Java resource will be printed as
	 * full-qualified names when the resource is saved. If this option is used, imports do not need to be updated when
	 * Java resources are modified. This option is set to <code>false</code> by default.
	 */
	public static final String OPTION_ALWAYS_USE_FULLY_QUALIFIED_NAMES = "OPTION_ALWAYS_USE_FULLY_QUALIFIED_NAMES";

	private boolean initialized = false;

	/**
	 * Singleton instance.
	 */
	private static JavaClasspath globalClasspath = null;

	private JavaClasspath(URIConverter uriConverter) {
		this.uriConverter = uriConverter;
	}

	private JavaClasspath(Resource resource) {
		ResourceSet resourceSet = resource.getResourceSet();
		this.uriConverter = resourceSet.getURIConverter();
	}

	public static JavaClasspath get() {
		getInitializers();
		if (globalClasspath == null) {
			globalClasspath = new JavaClasspath(URIConverter.INSTANCE);
			if (registerStdLibDefault()) {
				globalClasspath.registerStdLib();
			}
		}
		return globalClasspath;
	}

	public static JavaClasspath get(EObject objectContext) {
		getInitializers();
		if (objectContext == null) {
			return get();
		} else {
			return get(objectContext.eResource());
		}
	}

	public static JavaClasspath get(Resource resource) {
		getInitializers();
		if (resource == null) {
			return get();
		} else {
			ResourceSet resourceSet = resource.getResourceSet();
			JavaClasspath resourceSetClasspath = get(resourceSet);
			if (!resourceSetClasspath.initialized) {
				// Set to true before calling initializers, since the initializers most likely call this method again to
				// obtain the class path
				resourceSetClasspath.initialized = true;
				initialize(resource);
			}
			return resourceSetClasspath;
		}
	}

	/**
	 * Returns the class path for the given {@link ResourceSet}. Make sure to set the load options
	 * {@link #OPTION_USE_LOCAL_CLASSPATH} and {@link #OPTION_REGISTER_STD_LIB} to control whether a global class path
	 * is used for the resource set and whether the standard library shall be registered.
	 */
	public static JavaClasspath get(ResourceSet resourceSet) {
		return get(resourceSet, getInitializers());
	}

	public static JavaClasspath get(ResourceSet resourceSet, Set<Initializer> initalizers) {
		if (resourceSet == null) {
			return get();
		}

		boolean useLocalClasspath = useLocalClasspath(resourceSet, initalizers);
		if (useLocalClasspath) {
			for (Adapter adapter : resourceSet.eAdapters()) {
				if (adapter instanceof JavaClasspath) {
					JavaClasspath javaClasspath = (JavaClasspath) adapter;
					URIConverter newURIConverter = resourceSet.getURIConverter();
					URIConverter javaClasspathUriConverter = javaClasspath.uriConverter;
					if (javaClasspathUriConverter != newURIConverter) {
						// The URI converter has been replaced, the URI map needs to be transferred.
						Map<URI, URI> oldUriMap = javaClasspathUriConverter.getURIMap();
						Map<URI, URI> newUriMap = newURIConverter.getURIMap();
						for (Entry<URI, URI> oldEntry : oldUriMap.entrySet()) {
							URI oldKey = oldEntry.getKey();
							String oldKeyString = oldKey.toString();
							if (oldKeyString.startsWith(JavaUniquePathConstructor.JAVA_CLASSIFIER_PATHMAP)) {
								URI oldValue = oldEntry.getValue();
								newUriMap.put(oldKey, oldValue);
							}
						}

						javaClasspath.uriConverter = newURIConverter;
					}

					return javaClasspath;
				}
			}

			// If no class path was found, a new one (local class path) is created ...
			JavaClasspath newClasspath = new JavaClasspath(resourceSet.getURIConverter());
			// ... and attached to the resource set
			resourceSet.eAdapters().add(newClasspath);

			boolean registerStdLib = registerStdLib(resourceSet, initalizers);
			if (registerStdLib) {
				newClasspath.registerStdLib();
			}

			return newClasspath;
		}

		return get();
	}

	private static boolean registerStdLib(ResourceSet resourceSet, Set<Initializer> initalizers) {
		Object registerStdLibOption = resourceSet.getLoadOptions().get(OPTION_REGISTER_STD_LIB);
		if (registerStdLibOption == null) {
			registerStdLibOption = Boolean.valueOf(registerStdLibDefault(initalizers));
		}
		boolean registerStdLib = Boolean.TRUE.equals(registerStdLibOption);
		return registerStdLib;
	}

	private static boolean useLocalClasspath(ResourceSet resourceSet, Set<Initializer> initalizers) {
		Object localClasspathOption = resourceSet.getLoadOptions().get(OPTION_USE_LOCAL_CLASSPATH);
		if (localClasspathOption == null) {
			localClasspathOption = Boolean.valueOf(useLocalClasspathDefault(initalizers));
		}
		boolean useLocalClasspath = Boolean.TRUE.equals(localClasspathOption);
		return useLocalClasspath;
	}

	public static void reset() {
		globalClasspath = null;
	}

	protected static boolean useLocalClasspathDefault() {
		return useLocalClasspathDefault(getInitializers());
	}

	protected static boolean useLocalClasspathDefault(Set<Initializer> initializers) {
		boolean useLocalClasspathDefault = false;
		for (Initializer initializer : initializers) {
			// if one initializer requires a local classpath, a local classpath is used by default
			useLocalClasspathDefault = useLocalClasspathDefault || initializer.requiresLocalClasspath();
		}
		return useLocalClasspathDefault;
	}

	protected static boolean registerStdLibDefault() {
		return registerStdLibDefault(getInitializers());
	}

	protected static boolean registerStdLibDefault(Set<Initializer> initializers) {
		boolean registerStdLibDefault = true;
		for (Initializer initializer : initializers) {
			// if one initializer does not require the std. lib, we assume it provides one
			registerStdLibDefault = registerStdLibDefault && initializer.requiresStdLib();
		}
		return registerStdLibDefault;
	}

	// FIXME It might be better to use a set of strings here instead of a list of string to increase lookup speed
	protected Map<String, List<String>> packageClassifierMap = new LinkedHashMap<String, List<String>>();

	protected void registerPackage(String packageName, String className) {
		List<String> classesInPackage = packageClassifierMap.get(packageName);
		if (classesInPackage == null) {
			classesInPackage = new UniqueEList<String>();
			packageClassifierMap.put(packageName, classesInPackage);
		}

		if (!classesInPackage.contains(className)) {
			classesInPackage.add(className);
		}
	}

	protected void unRegisterPackage(String packageName, String className) {
		if (packageClassifierMap.containsKey(packageName)) {
			packageClassifierMap.get(packageName).remove(className);
		}
	}

	protected List<String> getPackageContents(String packageName) {
		List<String> content = new UniqueEList<String>();
		if (packageClassifierMap.containsKey(packageName)) {
			content.addAll(packageClassifierMap.get(packageName));
		}

		// Delegate to global map
		Map<String, List<String>> globalPackageClassifierMap = get().packageClassifierMap;
		if (globalPackageClassifierMap.containsKey(packageName)) {
			content.addAll(globalPackageClassifierMap.get(packageName));
		}
		return content;
	}

	public boolean existsPackage(String packageName) {
		if (packageClassifierMap.containsKey(packageName)) {
			return true;
		}

		// Delegate to global map
		Map<String, List<String>> globalPackageClassifierMap = get().packageClassifierMap;
		return globalPackageClassifierMap.containsKey(packageName);
	}

	protected URIConverter uriConverter = null;

	public Map<URI, URI> getURIMap() {
		if (uriConverter == URIConverter.INSTANCE) {
			return URIConverter.URI_MAP;
		}
		return uriConverter.getURIMap();
	}

	/**
	 * Registers all classes of the Java standard library (<code>classes.jar</code> or <code>rt.jar</code>) located at
	 * <code>System.getProperty("sun.boot.class.path")</code>.
	 */
	public void registerStdLib() {
		String classpath = System.getProperty("sun.boot.class.path");
		String[] classpathEntries = classpath.split(File.pathSeparator);

		String classesJarSuffix = File.separator + "classes.jar";
		String rtJarSuffix = File.separator + "rt.jar";

		for (int idx = 0; idx < classpathEntries.length; idx++) {
			String classpathEntry = classpathEntries[idx];
			if (classpathEntry.endsWith(classesJarSuffix) || classpathEntry.endsWith(rtJarSuffix)) {
				URI uri = URI.createFileURI(classpathEntry);
				registerClassifierJar(uri);
			}
		}
	}

	/**
	 * Registers all class files contained in the jar file located at the given URI.
	 *
	 * @param jarURI
	 *            the URI of JAR file to get class files from
	 */
	public void registerClassifierJar(URI jarURI) {
		registerClassifierJar(jarURI, "");
	}

	public void registerClassifierJar(URI jarURI, String prefix) {
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(jarURI.toFileString());
		} catch (IOException e) {
			System.out.println("Error in opening zip file: " + jarURI.toFileString());
			return;
		}

		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();

			String entryName = entry.getName();
			if (entryName.endsWith(JavaUniquePathConstructor.JAVA_CLASS_FILE_EXTENSION) && entryName.startsWith(prefix)) {
				String uri = "archive:" + jarURI.toString() + "!/" + entryName;

				String fullName = entryName.substring(prefix.length());
				fullName = fullName.replace("/", ".");

				String packageName = "";
				String className = "";

				int idx = fullName.lastIndexOf(".");
				idx = fullName.substring(0, idx).lastIndexOf(".");
				if (idx >= 0) {
					packageName = fullName.substring(0, idx);
					className = fullName.substring(idx + 1, fullName.lastIndexOf("."));
				} else {
					className = fullName.substring(0, fullName.lastIndexOf("."));
				}
				registerClassifier(packageName, className, URI.createURI(uri));
			}
		}
	}

	public void registerSourceOrClassFileFolder(URI folderURI) {
		if (!folderURI.isFile()) {
			return;
		}

		File sourceFolder = new File(folderURI.toFileString());
		if (sourceFolder.exists()) {
			internalRegisterSourceOrClassFileFolder(sourceFolder, "");
		}
	}

	private void internalRegisterSourceOrClassFileFolder(File folder, String packageName) {
		for (File child : folder.listFiles()) {
			if (!child.getName().startsWith(".")) { // no hidden files
				if (child.isDirectory()) {
					internalRegisterSourceOrClassFileFolder(child, packageName + child.getName()
							+ JavaUniquePathConstructor.PACKAGE_SEPARATOR);
				} else {
					if (child.getName().endsWith(JavaUniquePathConstructor.JAVA_FILE_EXTENSION)
							|| child.getName().endsWith(JavaUniquePathConstructor.JAVA_CLASS_FILE_EXTENSION)) {
						String classifierName = child.getName().substring(0, child.getName().lastIndexOf('.'));
						URI uri = URI.createFileURI(child.getAbsolutePath());
						registerClassifier(packageName, classifierName, uri);
					}
				}
			}
		}
	}

	/**
	 * Registers all classes defined in the given compilation unit.
	 *
	 * @param compilationUnit
	 */
	public void registerClassifierSource(CompilationUnit compilationUnit, URI uri) {
		String packageName = JavaUniquePathConstructor.packageName(compilationUnit);
		for (ConcreteClassifier classifier : compilationUnit.getClassifiers()) {
			registerClassifier(packageName, classifier.getName(), uri);
			registerInnerClassifiers(classifier, packageName, classifier.getName(), uri);
		}
	}

	/**
	 * Registers the classifier with the given name and package that is physically located at the given URI. If there is
	 * already a classifier registered for the given class name, the old one is replaced with the new one.
	 * 
	 * @param packageName
	 *            the name of the package that contains the classifier
	 * @param classifierName
	 *            the simple name of the classifier
	 * @param physicalURI
	 *            the URI where the classifier can be found (class or source file)
	 */
	public void registerClassifier(String packageName, String classifierName, URI physicalURI) {
		if (classifierName == null || physicalURI == null) {
			return;
		}
		if (!packageName.endsWith(".") && !packageName.endsWith("$")) {
			packageName = packageName + ".";
		}

		String innerName = classifierName;
		String outerName = "";
		String qualifiedName = packageName;

		int idx = classifierName.lastIndexOf(JavaUniquePathConstructor.CLASSIFIER_SEPARATOR);
		if (idx >= 0) {
			// The classifier name contains a "$"
			int indexPlusOne = idx + 1;
			innerName = classifierName.substring(indexPlusOne);
			outerName = classifierName.substring(0, indexPlusOne);
			if (".".equals(packageName)) {
				qualifiedName = outerName;
			} else {
				qualifiedName = packageName + outerName;
			}
		}

		synchronized (this) {
			registerPackage(qualifiedName, innerName);

			final String qualifiedClassifierName;
			if (".".equals(packageName)) {
				qualifiedClassifierName = classifierName;
			} else {
				qualifiedClassifierName = packageName + classifierName;
			}

			URI logicalURI = JavaUniquePathConstructor.getJavaFileResourceURI(qualifiedClassifierName);

			URI existingMapping = getURIMap().get(logicalURI);

			if (existingMapping != null && !physicalURI.equals(existingMapping)) {
				// Do nothing: Silently replace old with new version.
			}

			getURIMap().put(logicalURI, physicalURI);

			String outerPackage = qualifiedName;
			while (outerPackage.endsWith("$")) {
				// Make sure outer classes are registered;
				// This is required when class names contain $ symbols.
				outerPackage = outerPackage.substring(0, outerPackage.length() - 1);
				idx = outerPackage.lastIndexOf("$");
				if (idx == -1) {
					idx = outerPackage.lastIndexOf(".");
				}

				int indexPlusOne = idx + 1;
				String outerClassifier = outerPackage.substring(indexPlusOne);
				outerPackage = outerPackage.substring(0, indexPlusOne);
				if ("".equals(outerPackage)) {
					outerPackage = ".";
				}

				registerPackage(outerPackage, outerClassifier);
			}
		}
	}

	private void registerInnerClassifiers(ConcreteClassifier classifier, String packageName, String className, URI uri) {
		for (Member innerCand : ((MemberContainer) classifier).getMembers()) {
			if (innerCand instanceof ConcreteClassifier) {
				String newClassName = className + JavaUniquePathConstructor.CLASSIFIER_SEPARATOR + innerCand.getName();
				registerClassifier(packageName, newClassName, uri);
				registerInnerClassifiers((ConcreteClassifier) innerCand, packageName, newClassName, uri);
			}
		}
	}

	/**
	 * Removes the classifier identified by its package and name from the class path.
	 *
	 * @param packageName
	 * @param classifierName
	 */
	public void unRegisterClassifier(String packageName, String classifierName) {
		if (classifierName == null || classifierName.equals("")) {
			return;
		}

		if (!packageName.endsWith(".")) {
			packageName = packageName + ".";
		}

		String innerName = classifierName;
		String outerName = "";
		String qualifiedName = packageName;

		int idx = classifierName.lastIndexOf(JavaUniquePathConstructor.CLASSIFIER_SEPARATOR);
		if (idx >= 0) {
			innerName = classifierName.substring(idx + 1);
			outerName = classifierName.substring(0, idx + 1);
			if (".".equals(packageName)) {
				qualifiedName = outerName;
			} else {
				qualifiedName = packageName + outerName;
			}
		}

		synchronized (this) {
			unRegisterPackage(qualifiedName, innerName);

			String fullName = null;
			if (".".equals(packageName)) {
				fullName = classifierName;
			} else {
				fullName = packageName + classifierName;
			}

			URI logicalUri = JavaUniquePathConstructor.getJavaFileResourceURI(fullName);

			getURIMap().remove(logicalUri);
		}
	}

	public boolean isRegistered(String fullQualifiedName) {
		int idx = fullQualifiedName.lastIndexOf(JavaUniquePathConstructor.CLASSIFIER_SEPARATOR);
		if (idx == -1) {
			idx = fullQualifiedName.lastIndexOf(JavaUniquePathConstructor.PACKAGE_SEPARATOR);
		}
		if (idx == -1) {
			idx = -1;
		}
		String containerName = fullQualifiedName.substring(0, idx + 1);
		String classifierName = fullQualifiedName.substring(idx + 1);
		List<String> containerContent = getPackageContents(containerName);
		if (containerContent == null) {
			return false;
		}
		return containerContent.contains(classifierName);
	}

	// This method is only for testing purpose!
	public Map<String, List<String>> getPackageClassifierMap() {
		return packageClassifierMap;
	}

	/**
	 * Constructs a proxy pointing at the classifier identified by its fully qualified name.
	 * 
	 * @param fullQualifiedName
	 *            a qualified class name the proxy shall point to
	 * @return proxy element
	 */
	public EObject getClassifier(String fullQualifiedName) {
		InternalEObject classifierProxy = (InternalEObject) ClassifiersFactory.eINSTANCE.createClass();
		URI proxyURI = JavaUniquePathConstructor.getClassifierURI(fullQualifiedName);
		classifierProxy.eSetProxyURI(proxyURI);
		// set also the name to reason about it without resolving the proxy
		((Class) classifierProxy).setName(JavaUniquePathConstructor.getSimpleClassName(fullQualifiedName));
		return classifierProxy;
	}

	/**
	 * Constructs a list of proxies that point at all classifiers of the given package present in the class path.
	 * <p>
	 * Each proxy will have the <code>name</code> attribute set correctly such that name comparison can be done without
	 * resolving the proxy.
	 *
	 * @param packageName
	 * @return list of proxies
	 */
	public EList<EObject> getClassifiers(String packageName, String classifierQuery) {
		int idx = classifierQuery.lastIndexOf("$");
		if (idx >= 0) {
			packageName = packageName + classifierQuery.substring(0, idx + 1);
			classifierQuery = classifierQuery.substring(idx + 1);
		}

		if (!packageName.endsWith(JavaUniquePathConstructor.PACKAGE_SEPARATOR)
				&& !packageName.endsWith(JavaUniquePathConstructor.CLASSIFIER_SEPARATOR)) {
			packageName = packageName + JavaUniquePathConstructor.PACKAGE_SEPARATOR;
		}

		EList<EObject> resultList = new UniqueEList<EObject>();

		synchronized (this) {
			for (String classifierName : getPackageContents(packageName)) {
				if (classifierQuery.equals("*") || classifierQuery.equals(classifierName)) {
					InternalEObject classifierProxy = (InternalEObject) ClassifiersFactory.eINSTANCE.createClass();
					String fullQualifiedName = null;
					if ("".equals(packageName) || ".".equals(packageName)) {
						fullQualifiedName = classifierName;
					} else {
						fullQualifiedName = packageName + classifierName;
					}
					classifierProxy.eSetProxyURI(JavaUniquePathConstructor.getClassifierURI(fullQualifiedName));
					// set also the name to reason about it without resolving the proxy
					((Class) classifierProxy).setName(JavaUniquePathConstructor.getSimpleClassName(fullQualifiedName));
					resultList.add(classifierProxy);
				}
			}
		}
		return resultList;
	}

	private EList<EObject> javaLangPackage = null;

	/**
	 * Returns a list of proxies for all classes <code>java.lang.*</code>.
	 *
	 * @return list of proxies
	 */
	public EList<EObject> getDefaultImports() {
		EList<EObject> resultList = new UniqueEList<EObject>();

		// java.lang package
		if (javaLangPackage == null) {
			javaLangPackage = new UniqueEList<EObject>();
			javaLangPackage.addAll(getClassifiers("java.lang.", "*"));
		}

		resultList.addAll(javaLangPackage);

		return resultList;
	}

	public void registerClassifier(java.lang.Class<?> clazz) {
		URL resource = clazz.getResource(clazz.getSimpleName() + ".class");
		URI classURI = URI.createFileURI(resource.getFile());
		String packageName = clazz.getPackage().getName();
		String classifierName = clazz.getSimpleName();
		registerClassifier(packageName, classifierName, classURI);
	}

	// This cache holds all class from the <code>java.lang</code> package. It
	// maps their simple names to the respective Class objects.
	// private Map<String, Class> javaLangClassCache = new LinkedHashMap<String, Class>();

	public Class getJavaLangClass(EObject commentable, String simpleName) {
		// This was supposed to optimize performance by reusing Class objects for types from the 'java.lang' package,
		// but it turned out to cause trouble because JaMoPP uses equals() to compare these Class objects at many
		// places. For some unknown reason, these comparisons were expecting different (i.e., non-reused( Class objects.
		//
		// Turning the cache on led to test failures, which is why we do not use it currently. Once it is clarified why
		// equals() is used and why reusing the Class objects causes trouble, the cache can be turned on again.

		// if (!javaLangClassCache.containsKey(simpleName)) {
		String qualifiedName = "java.lang." + simpleName;
		Class classifier = (Class) getClassifier(qualifiedName);
		EObject resolved = (ConcreteClassifier) EcoreUtil.resolve(classifier, commentable);
		Class returnValue = null;
		if (resolved instanceof Class) {
			returnValue = (Class) resolved;
		}
		// javaLangClassCache.put(simpleName, returnValue);
		return returnValue;
		// }

		// return javaLangClassCache.get(simpleName);
	}
}
