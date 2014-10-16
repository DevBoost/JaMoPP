/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

/**
 * The NewFileWizardPage allows setting the container for the new file, as well as
 * the file name. The page will only accept file names without extension OR with
 * an extension that matches the expected one.
 */
public class JavaNewFileWizardPage extends WizardPage {
	
	private final String fileExtension;
	private Text containerText;
	private Text fileText;
	private ISelection selection;
	
	/**
	 * Constructor for the NewFileWizardPage.
	 */
	public JavaNewFileWizardPage(ISelection selection, String fileExtension) {
		super("wizardPage");
		setTitle(org.emftext.language.java.resource.java.ui.JavaUIResourceBundle.NEW_FILE_WIZARD_PAGE_TITLE);
		setDescription(org.emftext.language.java.resource.java.ui.JavaUIResourceBundle.NEW_FILE_WIZARD_DESCRIPTION);
		this.selection = selection;
		this.fileExtension = fileExtension;
	}
	
	/**
	 * 
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		Label label = new Label(container, SWT.NULL);
		label.setText("&Container:");
		
		containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		containerText.setLayoutData(gd);
		containerText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		Button button = new Button(container, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});
		label = new Label(container, SWT.NULL);
		label.setText("&File name:");
		
		fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		fileText.setLayoutData(gd);
		fileText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		initialize();
		dialogChanged();
		setControl(container);
	}
	
	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */
	private void initialize() {
		String name = org.emftext.language.java.resource.java.ui.JavaUIResourceBundle.NEW_FILE_WIZARD_FILE_NAME;
		if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1) {
				return;
			}
			Object obj = ssel.getFirstElement();
			// test for IAdaptable
			if ((! (obj instanceof IResource)) && (obj instanceof IAdaptable)) {
				obj = (IResource) ((IAdaptable) obj).getAdapter(IResource.class);
			}
			if (obj instanceof IResource) {
				IContainer container;
				if (obj instanceof IContainer) {
					container = (IContainer) obj;
				} else {
					IResource resource = (IResource) obj;
					container = resource.getParent();
					// we use the name of the currently selected file instead of 'new_file'.
					name = resource.getFullPath().removeFileExtension().lastSegment();
					name = name + "." + fileExtension;
				}
				IPath fullPath = container.getFullPath();
				containerText.setText(fullPath.toString());
			}
		}
		
		// Select default name for new file
		fileText.setText(name);
		
		// Select file name without extension
		int indexOfDot = name.lastIndexOf(".");
		if (indexOfDot > 0) {
			fileText.setSelection(0, indexOfDot);
		}
	}
	
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			fileText.setFocus();
		}
	}
	
	/**
	 * Uses the standard container selection dialog to choose the new value for the
	 * container field.
	 */
	private void handleBrowse() {
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(getShell(), ResourcesPlugin.getWorkspace().getRoot(), false, "Select new file container");
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				containerText.setText(((Path) result[0]).toString());
			}
		}
	}
	
	/**
	 * Ensures that both text fields are set.
	 */
	private void dialogChanged() {
		IResource container = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(getContainerName()));
		String fileName = getFileName();
		
		if (getContainerName().length() == 0) {
			updateStatus("File container must be specified");
			return;
		}
		if (container == null || (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
			updateStatus("File container must exist");
			return;
		}
		if (!container.isAccessible()) {
			updateStatus("Project must be writable");
			return;
		}
		if (fileName.length() == 0) {
			updateStatus("File name must be specified");
			return;
		}
		if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
			updateStatus("File name must be valid");
			return;
		}
		if (!fileName.endsWith("." + fileExtension)) {
			updateStatus("File extension must be \"" + fileExtension + "\"");
			return;
		}
		updateStatus(null);
	}
	
	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}
	
	public String getContainerName() {
		return containerText.getText();
	}
	
	public String getFileName() {
		return fileText.getText();
	}
	
}
