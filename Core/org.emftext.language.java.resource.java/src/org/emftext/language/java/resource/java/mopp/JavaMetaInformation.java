/**
 * <copyright>
 * </copyright>
 *
 *
 */
package org.emftext.language.java.resource.java.mopp;

import org.emftext.language.java.resource.java.IJavaNameProvider;
import org.emftext.language.java.resource.java.JavaReferenceResolverSwitchFactory;

public class JavaMetaInformation implements org.emftext.language.java.resource.java.IJavaMetaInformation {

    /** The name provider to be always returned by this {@link JavaMetaInformation} instance. */
    private IJavaNameProvider nameProvider = new org.emftext.language.java.resource.java.analysis.JavaDefaultNameProvider();

	public String getSyntaxName() {
		return "java";
	}

	public String getURI() {
		return "http://www.emftext.org/java";
	}

	public org.emftext.language.java.resource.java.IJavaTextScanner createLexer() {
		return new org.emftext.language.java.resource.java.mopp.JavaAntlrScanner(new org.emftext.language.java.resource.java.mopp.JavaLexer());
	}

	public org.emftext.language.java.resource.java.IJavaTextParser createParser(java.io.InputStream inputStream, String encoding) {
		return new org.emftext.language.java.resource.java.mopp.JavaParser().createInstance(inputStream, encoding);
	}

	public org.emftext.language.java.resource.java.IJavaTextPrinter createPrinter(java.io.OutputStream outputStream, org.emftext.language.java.resource.java.IJavaTextResource resource) {
		return new org.emftext.language.java.resource.java.mopp.JavaPrinter2(outputStream, resource);
	}

	public org.eclipse.emf.ecore.EClass[] getClassesWithSyntax() {
		return new org.emftext.language.java.resource.java.mopp.JavaSyntaxCoverageInformationProvider().getClassesWithSyntax();
	}

	public org.eclipse.emf.ecore.EClass[] getStartSymbols() {
		return new org.emftext.language.java.resource.java.mopp.JavaSyntaxCoverageInformationProvider().getStartSymbols();
	}

	public org.emftext.language.java.resource.java.IJavaReferenceResolverSwitch getReferenceResolverSwitch() {
		return JavaReferenceResolverSwitchFactory.getSwitch();
	}

	public org.emftext.language.java.resource.java.IJavaTokenResolverFactory getTokenResolverFactory() {
		return new org.emftext.language.java.resource.java.mopp.JavaTokenResolverFactory();
	}

	public String getPathToCSDefinition() {
		return "org.emftext.language.java/metamodel/java.cs";
	}

	public String[] getTokenNames() {
		return org.emftext.language.java.resource.java.mopp.JavaParser.tokenNames;
	}

	public org.emftext.language.java.resource.java.IJavaTokenStyle getDefaultTokenStyle(String tokenName) {
		return new org.emftext.language.java.resource.java.mopp.JavaTokenStyleInformationProvider().getDefaultTokenStyle(tokenName);
	}

	public java.util.Collection<org.emftext.language.java.resource.java.IJavaBracketPair> getBracketPairs() {
		return new org.emftext.language.java.resource.java.mopp.JavaBracketInformationProvider().getBracketPairs();
	}

	public org.eclipse.emf.ecore.EClass[] getFoldableClasses() {
		return new org.emftext.language.java.resource.java.mopp.JavaFoldingInformationProvider().getFoldableClasses();
	}

	public org.eclipse.emf.ecore.resource.Resource.Factory createResourceFactory() {
		return new org.emftext.language.java.resource.java.mopp.JavaResourceFactory();
	}

	public org.emftext.language.java.resource.java.mopp.JavaNewFileContentProvider getNewFileContentProvider() {
		return new org.emftext.language.java.resource.java.mopp.JavaNewFileContentProvider();
	}

	public void registerResourceFactory() {
		org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(getSyntaxName(), new org.emftext.language.java.resource.java.mopp.JavaResourceFactory());
	}

	/**
	 * Returns the key of the option that can be used to register a preprocessor that
	 * is used as a pipe when loading resources. This key is language-specific. To
	 * register one preprocessor for multiple resource types, it must be registered
	 * individually using all keys.
	 */
	public String getInputStreamPreprocessorProviderOptionKey() {
		return getSyntaxName() + "_" + "INPUT_STREAM_PREPROCESSOR_PROVIDER";
	}

	/**
	 * Returns the key of the option that can be used to register a post-processors
	 * that are invoked after loading resources. This key is language-specific. To
	 * register one post-processor for multiple resource types, it must be registered
	 * individually using all keys.
	 */
	public String getResourcePostProcessorProviderOptionKey() {
		return getSyntaxName() + "_" + "RESOURCE_POSTPROCESSOR_PROVIDER";
	}

	public String getLaunchConfigurationType() {
		return "org.emftext.language.java.resource.java.ui.launchConfigurationType";
	}

	public org.emftext.language.java.resource.java.IJavaNameProvider createNameProvider() {
		return nameProvider;
	}

	public String[] getSyntaxHighlightableTokenNames() {
		org.emftext.language.java.resource.java.mopp.JavaAntlrTokenHelper tokenHelper = new org.emftext.language.java.resource.java.mopp.JavaAntlrTokenHelper();
		java.util.List<String> highlightableTokens = new java.util.ArrayList<String>();
		String[] parserTokenNames = getTokenNames();
		for (int i = 0; i < parserTokenNames.length; i++) {
			// If ANTLR is used we need to normalize the token names
			if (!tokenHelper.canBeUsedForSyntaxHighlighting(i)) {
				continue;
			}
			String tokenName = tokenHelper.getTokenName(parserTokenNames, i);
			if (tokenName == null) {
				continue;
			}
			highlightableTokens.add(tokenName);
		}
		highlightableTokens.add(org.emftext.language.java.resource.java.mopp.JavaTokenStyleInformationProvider.TASK_ITEM_TOKEN_NAME);
		return highlightableTokens.toArray(new String[highlightableTokens.size()]);
	}

}
