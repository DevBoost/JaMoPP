/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import java.net.URL;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.osgi.framework.Bundle;

/**
 * <p>
 * This class is based on:
 * <i>org.eclipse.gef.examples.ui.pde.internal.wizards.ProjectUnzipperNewWizard</i>
 * .
 * </p>
 * <p>
 * It is responsible for offering an example project via the new dialog of Eclipse.
 * </p>
 */
public class JavaNewProjectWizard extends Wizard implements INewWizard, IExecutableExtension {
	
	/**
	 * The name of the ZIP file that is used as content for the new project (relative
	 * to the root of the resource UI plug-in).
	 */
	public final static String NEW_PROJECT_ZIP_FILE_NAME = org.emftext.language.java.resource.java.ui.JavaUIResourceBundle.NEW_PROJECT_ZIP_FILE_NAME;
	
	/**
	 * The single page provided by this base implementation. It provides all the
	 * functionality required to capture the name and location of the target project.
	 */
	protected WizardNewProjectCreationPage wizardNewProjectCreationPage;
	
	/**
	 * The name of the project creation page
	 */
	protected String pageName = org.emftext.language.java.resource.java.ui.JavaUIResourceBundle.NEW_PROJECT_WIZARD_PAGE_NAME;
	
	/**
	 * The title of the project creation page
	 */
	protected String pageTitle = pageName;
	
	/**
	 * The description of the project creation page
	 */
	protected String pageDescription = org.emftext.language.java.resource.java.ui.JavaUIResourceBundle.NEW_PROJECT_WIZARD_PAGE_DESCRIPTION;
	
	/**
	 * The name of the project in the project creation page
	 */
	protected String pageProjectName = org.emftext.language.java.resource.java.ui.JavaUIResourceBundle.NEW_PROJECT_WIZARD_PROJECT_NAME;
	
	/**
	 * The configuration element associated with this new project wizard
	 */
	protected IConfigurationElement config;
	
	/**
	 * Creates the example project by delegating the work to
	 * org.emftext.language.java.resource.java.ui.JavaNewProjectWizardLogic.
	 */
	public boolean performFinish() {
		
		try {
			IRunnableWithProgress operation = new WorkspaceModifyOperation() {
				
				public void execute(IProgressMonitor monitor) throws InterruptedException {
					try {
						doPerformFinish(monitor);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			};
			
			getContainer().run(false, true, operation);
			
			// Set perspective
			BasicNewProjectResourceWizard.updatePerspective(config);
		} catch (InterruptedException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	protected void doPerformFinish(IProgressMonitor monitor) throws Exception {
		new org.emftext.language.java.resource.java.ui.JavaNewProjectWizardLogic().createExampleProject(monitor, wizardNewProjectCreationPage.getLocationPath(), wizardNewProjectCreationPage.getProjectName(), org.emftext.language.java.resource.java.ui.JavaUIPlugin.PLUGIN_ID, NEW_PROJECT_ZIP_FILE_NAME);
	}
	
	/**
	 * <p>
	 * Creates the sole wizard page contributed by this base implementation; the
	 * standard Eclipse WizardNewProjectCreationPage.
	 * </p>
	 * 
	 * @see WizardNewProjectCreationPage#WizardNewProjectCreationPage(String)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// Set default image for all wizard pages
		IPath path = new Path(org.emftext.language.java.resource.java.ui.JavaUIResourceBundle.NEW_PROJECT_WIZARD_PAGE_ICON);
		Bundle bundle = org.emftext.language.java.resource.java.ui.JavaUIPlugin.getDefault().getBundle();
		URL url = FileLocator.find(bundle, path, null);
		ImageDescriptor descriptor = ImageDescriptor.createFromURL(url);
		setDefaultPageImageDescriptor(descriptor);
		
		wizardNewProjectCreationPage = new WizardNewProjectCreationPage(pageName);
		wizardNewProjectCreationPage.setTitle(pageTitle);
		wizardNewProjectCreationPage.setDescription(pageDescription);
		wizardNewProjectCreationPage.setInitialProjectName(pageProjectName);
		
		this.addPage(wizardNewProjectCreationPage);
		setWindowTitle(org.emftext.language.java.resource.java.ui.JavaUIResourceBundle.NEW_PROJECT_WIZARD_WINDOW_TITLE);
	}
	
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		this.config = config;
	}
	
}
