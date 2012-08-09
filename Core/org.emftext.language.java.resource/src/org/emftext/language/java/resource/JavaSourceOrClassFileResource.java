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
import java.io.OutputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.JavaUniquePathConstructor;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.EmptyModel;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.MembersPackage;
import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.resource.java.IJavaContextDependentURIFragment;
import org.emftext.language.java.resource.java.IJavaContextDependentURIFragmentFactory;
import org.emftext.language.java.resource.java.IJavaInputStreamProcessorProvider;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.emftext.language.java.resource.java.IJavaReferenceResolverSwitch;
import org.emftext.language.java.resource.java.IJavaResourcePostProcessor;
import org.emftext.language.java.resource.java.IJavaTextPrinter;
import org.emftext.language.java.resource.java.mopp.JavaContextDependentURIFragmentFactory;
import org.emftext.language.java.resource.java.mopp.JavaInputStreamProcessor;
import org.emftext.language.java.resource.java.mopp.JavaReferenceResolverSwitch;
import org.emftext.language.java.resource.java.mopp.JavaResource;
import org.emftext.language.java.resource.java.util.JavaLayoutUtil;
import org.emftext.language.java.resource.java.util.JavaUnicodeConverter;
import org.emftext.language.java.util.JavaModelCompletion;
import org.emftext.language.java.util.JavaModelRepairer;

/**
 * A resource that uses either the generated 
 * {@link org.emftext.language.java.resource.java.mopp.JavaParser} 
 * or the {@link ClassFileModelLoader} for loading depending on 
 * the file extension of the resource's URI.
 */
public class JavaSourceOrClassFileResource extends JavaResource {

	public JavaSourceOrClassFileResource(URI uri) {
		super(uri);
	}

	private JavaLayoutUtil layoutUtil = new JavaLayoutUtil();
	
	protected boolean isClassFile() {
		if (uri == null) {
			return false;
		}
		//is there a physical source file behind this URI?
		URI normalizedURI = getURIConverter().normalize(uri);

		if(normalizedURI.fileExtension().equals("class"))  {
			return true;
		}
		return false;
	}
	
	protected boolean isPackage() {
		if (getContentsInternal().isEmpty()) {
			return false;
		}
		return getContentsInternal().get(0) instanceof Package;
	}
	
	protected boolean hasJavaClassifierURI() {
		if (uri == null) {
			return false;
		}
		return uri.toString().startsWith(
				JavaUniquePathConstructor.JAVA_CLASSIFIER_PATHMAP);
	}

	@Override
	protected void doLoad(java.io.InputStream inputStream, java.util.Map<?,?> options) throws java.io.IOException {
		if (isClassFile()) {
			JavaClasspath javaClasspath = JavaClasspath.get(this);
			ClassFileModelLoader classFileParser = new ClassFileModelLoader(javaClasspath);
			CompilationUnit cu = classFileParser.parse(inputStream, getURI().lastSegment());
			getContentsInternal().add(cu);
			JavaModelCompletion.complete(this);
		} else {
			Map<Object, Object> optionsWithUnicodeConverter = new LinkedHashMap<Object, Object>();
			if (options != null) {
				optionsWithUnicodeConverter.putAll(options);
			}
			if (!optionsWithUnicodeConverter.containsKey(IJavaOptions.INPUT_STREAM_PREPROCESSOR_PROVIDER)) {
				optionsWithUnicodeConverter.put(
						IJavaOptions.INPUT_STREAM_PREPROCESSOR_PROVIDER, 
						new IJavaInputStreamProcessorProvider() {
							
							public JavaInputStreamProcessor getInputStreamProcessor(InputStream inputStream) {
								return new JavaUnicodeConverter(inputStream);	
							}
				});
			}
			super.doLoad(inputStream, optionsWithUnicodeConverter);
			if (getContentsInternal().isEmpty() && getErrors().isEmpty()) {
				contents.add(ContainersFactory.eINSTANCE.createEmptyModel());
			}
		}
	}

	@Override
	public void load(Map<?, ?> options) throws IOException {
    	URIConverter uriConverter = getURIConverter();
    	URI normalizedURI = uriConverter.normalize(uri);
		if (normalizedURI.toString().startsWith(JavaUniquePathConstructor.JAVA_PACKAGE_PATHMAP)) {
			if (!isLoaded) {
				loadPackageFromClasspath();
			}
		} else if (normalizedURI.toString().startsWith(JavaUniquePathConstructor.JAVA_CLASSIFIER_PATHMAP)) {
			//classes should have a physical resource
			//System.out.println("[JaMoPP] Warning: " + uri.lastSegment() + " not registered in class path");
		} else {
			super.load(options);
		}
		register();
	}

	@Override
	protected void doUnload() {
		if (!getContentsInternal().isEmpty()) {
			if(getContentsInternal().get(0) instanceof Package) {
				getContentsInternal().clear();
			}
			else {
				super.doUnload();
			}
		}
	}

	/**
	 * We override this method to enhance the created proxy objects by setting
	 * the 'name' attribute. This is needed to ask proxy objects for their name
	 * without resolving them.
	 */
	public <ContainerType extends EObject, ReferenceType extends EObject> void registerContextDependentProxy(IJavaContextDependentURIFragmentFactory<ContainerType, ReferenceType> factory, ContainerType container, EReference reference, String id, EObject proxyElement, int position) {
		super.registerContextDependentProxy(factory, container, reference, id, proxyElement, position);
		if (proxyElement instanceof NamedElement) {
			NamedElement namedElement = (NamedElement) proxyElement;
			namedElement.setName(id);
		}
	}

	@Override
	public EObject getEObject(String id) {
		EObject result = null;
		
		// check whether proxy resolving is turned off
		Object disableProxyResolvingValue = getResourceSet().getLoadOptions().get(IExtendedJavaOptions.DISABLE_ON_DEMAND_PROXY_RESOLVING);
		if (Boolean.TRUE.equals(disableProxyResolvingValue)) {
			return null;
		}
		
		if ((isClassFile() || isPackage()) &&
				id.startsWith("//" + JavaUniquePathConstructor.CLASSIFIERS_ROOT_PATH_PREFIX)) {
			if (!getContentsInternal().isEmpty()) {
				//in a class file, there is always only one classifier as root element:
				//id path can be ignored
				CompilationUnit cu =  (CompilationUnit) contents.get(0);
				return cu.getClassifiers().get(0);
			} else {
				//if this happens, there was a problem during class file loading
				return null;
			}
		}
		else {
			try {
				result = super.getEObject(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(!id.startsWith(IJavaContextDependentURIFragment.INTERNAL_URI_FRAGMENT_PREFIX)) {
				if(result != null && !(result instanceof ConcreteClassifier)) {
					//may happen if members of same name exist
					if(result.eContainingFeature() != null
							&& result.eContainingFeature().equals(MembersPackage.Literals.MEMBER_CONTAINER__MEMBERS)
							&& result instanceof NamedElement) {
						String memberName = ((NamedElement)result).getName();
						for(Member m : ((MemberContainer)result.eContainer()).getMembers()) {
							if(memberName.equals(m.getName()) && m instanceof ConcreteClassifier) {
								result = m;
								return result;
							}
						}
					}
				}
			}
		}
		return result;
	}

	@Override
	protected EObject getEObject(List<String> uriFragmentPath) {
		int size = uriFragmentPath.size();
		EObject eObject = getEObjectForURIFragmentRootSegment(size == 0 ? ""
				: uriFragmentPath.get(0));
		for (int i = 1; i < size && eObject != null; ++i) {
			String uriFragment = uriFragmentPath.get(i);
			if (eObject instanceof MemberContainer && uriFragment.startsWith(
				JavaUniquePathConstructor.CLASSIFIERS_SUB_PATH_PREFIX)) {

				MemberContainer memberContainer = (MemberContainer) eObject;
				String name = uriFragment.substring(
						JavaUniquePathConstructor.CLASSIFIERS_SUB_PATH_PREFIX.length(),
						uriFragment.length() - 2);
				eObject = memberContainer.getContainedClassifier(name);
			}
			else if (eObject instanceof CompilationUnit && uriFragment.startsWith(
					JavaUniquePathConstructor.CLASSIFIERS_ROOT_PATH_PREFIX)){

				CompilationUnit compilationUnit = (CompilationUnit)eObject;
				String name = uriFragment.substring(
						JavaUniquePathConstructor.CLASSIFIERS_ROOT_PATH_PREFIX.length(),
						uriFragment.length() - 2);
				eObject = compilationUnit.getContainedClassifier(name);
				int j = i + 1;
				while (j < size && eObject == null) {
					// this is required for classifiers with '$' in their names
					String subUriFragment = uriFragmentPath.get(j);
					name = name + "$" + subUriFragment.substring(
							JavaUniquePathConstructor.CLASSIFIERS_SUB_PATH_PREFIX.length(),
							subUriFragment.length() - 2);
					eObject = compilationUnit.getContainedClassifier(name);
					if (eObject != null) {
						i = j;
					} else {
						j++;
					}
				}

			} else {
				 eObject = ((InternalEObject)eObject).eObjectForURIFragmentSegment(uriFragmentPath.get(i));
			}
		}

		return eObject;
	}

	protected void loadPackageFromClasspath() {
		Package thePackage = ContainersFactory.eINSTANCE.createPackage();
		String packageName = getURI().trimFileExtension().toString().substring(
				JavaUniquePathConstructor.JAVA_PACKAGE_PATHMAP.length());
		String[] packageNaemParts = packageName.split("\\.");
		for(int i = 0; i < packageNaemParts.length; i++) {
			if(i < packageNaemParts.length - 1) {
				thePackage.getNamespaces().add(packageNaemParts[i]);
			}
			else {
				thePackage.setName(packageNaemParts[i]);
			}
		}
		populatePackage(thePackage);
		getContentsInternal().add(thePackage);
	}

	protected void register() throws IOException {
		URI myURI = getURI();

		if (!getContentsInternal().isEmpty()) {
			EObject root = getContentsInternal().get(0);
			if(root instanceof CompilationUnit) {
				CompilationUnit cu = (CompilationUnit) root;
				setCompilationUnitName(cu);
			}
			
			//only for physical URIs
			if(hasJavaClassifierURI()) {
				return;
			}
			
			//could also be a package-info.java without CU
			if(root instanceof CompilationUnit) {
				CompilationUnit cu = (CompilationUnit) root;
				JavaClasspath.get(this).registerClassifierSource(cu, myURI);
			} else if (root instanceof Package) {
				//package-info.java
				Package p = (Package) root;
				populatePackage(p);
			} else if (root instanceof EmptyModel) {
				((EmptyModel) root).setName(myURI.trimFileExtension().lastSegment());
			}
		}
	}

	protected void setCompilationUnitName(CompilationUnit cu) {
		String packageName = "";
		if(!hasJavaClassifierURI()) {
			//physical URIs do not include the package name
			//so we construct it from the cu's namespaces
			packageName = JavaUniquePathConstructor.packageName(cu);
		}
		String fileName = getURI().lastSegment();
		if (!"".equals(packageName)) {
			cu.setName(packageName + "." + fileName);
		} else {
			cu.setName(fileName);
		}
	}

	protected void populatePackage(Package p) {
		String fullPackageName = JavaUniquePathConstructor.packageName(p) + "." + p.getName();;
		for (EObject classifier : JavaClasspath.get(this).getClassifiers(
				fullPackageName, "*")) {

			classifier = (ConcreteClassifier) EcoreUtil.resolve(classifier, this.getResourceSet());
			if (!classifier.eIsProxy()) {
				CompilationUnit cu = (CompilationUnit)classifier.eContainer();
				if (cu != null) {
					p.getCompilationUnits().add(cu);
				}
			}
		}
	}

	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options)
			throws IOException {
		if (isClassFile()) {
			//save not supported
			return;
		}

		ResourceSet resourceSetForSave = getResourceSet();
		if (resourceSetForSave == null) {
			resourceSetForSave = new ResourceSetImpl();
		}

		if (containsMultipleCompilationUnits()) {
			for (EObject eObject : new BasicEList<EObject>(getContentsInternal())) {
				if (eObject instanceof CompilationUnit) {
					CompilationUnit cu = (CompilationUnit) eObject;
					if (cu.getClassifiers().isEmpty()) {
						continue;
					}

					String[] folder = cu.getNamespaces().toArray(
							new String[cu.getNamespaces().size()]);
					String   file   = cu.getClassifiers().get(0).getName();

					URI normalizedURI = resourceSetForSave.getURIConverter().normalize(getURI());

					URI subResourcURI = normalizedURI.trimFileExtension().trimFileExtension();

					if (normalizedURI.segmentCount() >= folder.length + 1 &&
						normalizedURI.segmentsList().subList(
								normalizedURI.segmentCount() - 1 - folder.length,
								normalizedURI.segmentCount() - 1).equals(Arrays.asList(folder))) {
						subResourcURI = subResourcURI.trimSegments(1);
					} else {
						subResourcURI = subResourcURI.appendSegments(folder);
					}
					subResourcURI = subResourcURI.appendSegment(file);
					subResourcURI = subResourcURI.appendFileExtension("java");

					Resource subResource =
						resourceSetForSave.createResource(subResourcURI);

					addPackageDeclaration(cu);

					subResource.getContents().add(cu);
					subResource.save(options);
				} else {
					//nothing
				}
			}
		} else {
			if (!getContentsInternal().isEmpty()) {
				if (getContentsInternal().get(0) instanceof CompilationUnit) {
					CompilationUnit cu = (CompilationUnit) getContentsInternal().get(0) ;
					addPackageDeclaration(cu);
				}
				//super.doSave(outputStream, options);
				IJavaTextPrinter printer = getMetaInformation().createPrinter(outputStream, this);
				IJavaReferenceResolverSwitch referenceResolverSwitch = getReferenceResolverSwitch();
				printer.setEncoding(getEncoding(options));
				referenceResolverSwitch.setOptions(options);
				EObject root = getContentsInternal().get(0); //only print the single CU or Package
				if (isLayoutInformationRecordingEnabled()) {
					layoutUtil.transferAllLayoutInformationFromModel(root);
				}
				printer.print(root);
				if (isLayoutInformationRecordingEnabled()) {
					layoutUtil.transferAllLayoutInformationToModel(root);
				}
			}
		}
	}

	protected boolean containsMultipleCompilationUnits() {
		boolean foundOne = false;
		for(EObject eObject : getContentsInternal()) {
			if (eObject instanceof CompilationUnit) {
				if (foundOne) {
					return true;
				}
				foundOne = true;
			}
		}
		return false;
	}
	
	/**
	 * This method adds a package declaration (namespaces) to the given compilation unit
	 * if none is defined and this resource has a logical URI. The segments of the logical
	 * URI are assumed as package name.
	 *
	 * @param cu
	 */
	protected void addPackageDeclaration(CompilationUnit cu) {
		if (cu.getNamespaces().isEmpty() && !getURI().isFile() && !getURI().isPlatform()) {
			//if there is no package and this is a logical URI, guess the package based on the URI
			String[] fullName = getURI().lastSegment().split("\\.");
			for (int i = 0; i < fullName.length - 2; i++) { 
				cu.getNamespaces().add(fullName[i]);
			}
		}
	}

	@Override
	protected void runPostProcessor(IJavaResourcePostProcessor postProcessor) {
		//do the repair and complete at post processing time (after parsing, before validation)
		repairAndComplete();
	}
	
	protected void repairAndComplete() {
		new JavaModelRepairer() {
			protected void registerContextDependentProxy(
					Resource resource,
					IdentifierReference mainIdReference, EReference targetReference,
					String id, EObject proxy) {
				assert !targetReference.isMany();

				((JavaResource)resource).registerContextDependentProxy(
						new JavaContextDependentURIFragmentFactory<ElementReference, ReferenceableElement>(
								new JavaReferenceResolverSwitch().getElementReferenceTargetReferenceResolver()),
						mainIdReference,
						targetReference,
						id,
						proxy,
						-1);
			}
		}.repair(this);

		JavaModelCompletion.complete(this);
	}
}
