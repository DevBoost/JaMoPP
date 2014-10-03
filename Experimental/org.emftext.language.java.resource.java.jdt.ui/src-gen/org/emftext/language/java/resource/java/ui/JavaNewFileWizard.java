/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class JavaNewFileWizard extends Wizard implements INewWizard {
	
	protected String categoryId = null;
	protected org.emftext.language.java.resource.java.ui.JavaNewFileWizardPage page;
	protected ISelection selection;
	protected String newName = null;
	
	public JavaNewFileWizard() {
		super();
		setNeedsProgressMonitor(true);
		setWindowTitle(org.emftext.language.java.resource.java.ui.JavaUIResourceBundle.NEW_FILE_WIZARD_WINDOW_TITLE);
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(String id) {
		categoryId = id;
	}
	
	/**
	 * Adds the pages to the wizard.
	 */
	public void addPages() {
		page = new org.emftext.language.java.resource.java.ui.JavaNewFileWizardPage(selection, getFileExtension());
		addPage(page);
	}
	
	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We will
	 * create an operation and run it using the wizard as execution context.
	 */
	public boolean performFinish() {
		final String containerName = page.getContainerName();
		final String fileName = page.getFileName();
		this.newName = fileName;
		int seperatorIdx = newName.indexOf('.');
		if (seperatorIdx != -1) {
			newName = newName.substring(0, seperatorIdx);
		}
		final IFile file;
		try {
			file = getFile(fileName, containerName);
		} catch (CoreException e1) {
			org.emftext.language.java.resource.java.ui.JavaUIPlugin.logError("Exception while initializing new file", e1);
			return false;
		}
		
		if (file.exists()) {
			// ask for confirmation
			MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_QUESTION			| SWT.YES | SWT.NO);
			messageBox.setMessage("File \"" + fileName + "\" already exists. Do you want to override it?");
			messageBox.setText("File exists");
			int response = messageBox.open();
			if (response == SWT.NO) {
				return true;
			}
		}
		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(containerName, fileName, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			org.emftext.language.java.resource.java.ui.JavaUIPlugin.logError("Exception while initializing new file", e);
			return false;
		}
		return true;
	}
	
	/**
	 * The worker method. It will find the container, create the file if missing or
	 * just replace its contents, and open the editor on the newly created file.
	 */
	protected void doFinish(String containerName, String fileName, IProgressMonitor monitor) throws CoreException {
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2);
		final IFile file = getFile(fileName, containerName);
		try {
			InputStream stream = openContentStream();
			if (file.exists()) {
				file.setContents(stream, true, true, monitor);
			} else {
				file.create(stream, true, monitor);
			}
			stream.close();
		} catch (IOException e) {
		}
		monitor.worked(1);
		monitor.setTaskName("Opening file for editing...");
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, file, true);
				} catch (PartInitException e) {
				}
			}
		});
		monitor.worked(1);
	}
	
	protected IFile getFile(String fileName, String containerName) throws CoreException {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName + "\" does not exist.");
		}
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(fileName));
		return file;
	}
	
	/**
	 * Initializes file contents with a sample text.
	 */
	protected InputStream openContentStream() {
		return new ByteArrayInputStream(new org.emftext.language.java.resource.java.mopp.JavaMetaInformation().getNewFileContentProvider().getNewFileContent(newName).getBytes());
	}
	
	protected void throwCoreException(String message) throws CoreException {
		IStatus status = new Status(IStatus.ERROR, "NewFileContentPrinter", IStatus.OK, message, null);
		throw new CoreException(status);
	}
	
	/**
	 * <p>
	 * We will accept the selection in the workbench to see if we can initialize from
	 * it.
	 * </p>
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
	
	public String getFileExtension() {
		return new org.emftext.language.java.resource.java.mopp.JavaMetaInformation().getSyntaxName();
	}
	
	public org.emftext.language.java.resource.java.IJavaMetaInformation getMetaInformation() {
		return new org.emftext.language.java.resource.java.mopp.JavaMetaInformation();
	}
	
}
