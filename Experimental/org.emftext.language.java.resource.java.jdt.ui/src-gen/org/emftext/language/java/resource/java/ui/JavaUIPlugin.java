/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * A singleton class for the text resource UI plug-in.
 */
public class JavaUIPlugin extends AbstractUIPlugin {
	
	public static final String PLUGIN_ID = "org.emftext.language.java.resource.java.ui";
	public static final String EDITOR_ID = "org.emftext.language.java.resource.java.ui.JavaEditor";
	public static final String EMFTEXT_SDK_VERSION = "1.4.1";
	public static final String EP_DEFAULT_LOAD_OPTIONS_ID = PLUGIN_ID + ".default_load_options";
	public static final String EP_ADDITIONAL_EXTENSION_PARSER_ID = PLUGIN_ID + ".additional_extension_parser";
	
	private static JavaUIPlugin plugin;
	
	public JavaUIPlugin() {
		super();
	}
	
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}
	
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}
	
	public static JavaUIPlugin getDefault() {
		return plugin;
	}
	
	public static void showErrorDialog(final String title, final String message) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				Shell parent = new Shell();
				MessageDialog dialog = new MessageDialog(parent, title, null, message, MessageDialog.ERROR, new String[] { IDialogConstants.OK_LABEL }, 0) {
				};
				dialog.open();
			}
		});
	}
	
	/**
	 * <p>
	 * Helper method for error logging.
	 * </p>
	 * 
	 * @param message the error message to log
	 * @param throwable the exception that describes the error in detail (can be null)
	 * 
	 * @return the status object describing the error
	 */
	public static IStatus logError(String message, Throwable throwable) {
		return log(IStatus.ERROR, message, throwable);
	}
	
	/**
	 * <p>
	 * Helper method for logging warnings.
	 * </p>
	 * 
	 * @param message the warning message to log
	 * @param throwable the exception that describes the warning in detail (can be
	 * null)
	 * 
	 * @return the status object describing the warning
	 */
	public static IStatus logWarning(String message, Throwable throwable) {
		return log(IStatus.WARNING, message, throwable);
	}
	
	/**
	 * <p>
	 * Helper method for logging infos.
	 * </p>
	 * 
	 * @param message the info message to log
	 * @param throwable the exception that describes the info in detail (can be null)
	 * 
	 * @return the status object describing the info
	 */
	public static IStatus logInfo(String message, Throwable throwable) {
		return log(IStatus.INFO, message, throwable);
	}
	
	/**
	 * <p>
	 * Helper method for logging.
	 * </p>
	 * 
	 * @param type the type of the message to log
	 * @param message the message to log
	 * @param throwable the exception that describes the error in detail (can be null)
	 * 
	 * @return the status object describing the error
	 */
	protected static IStatus log(int type, String message, Throwable throwable) {
		IStatus status;
		if (throwable != null) {
			status = new Status(type, JavaUIPlugin.PLUGIN_ID, 0, message, throwable);
		} else {
			status = new Status(type, JavaUIPlugin.PLUGIN_ID, message);
		}
		final JavaUIPlugin pluginInstance = JavaUIPlugin.getDefault();
		if (pluginInstance == null) {
			System.err.println(message);
			if (throwable != null) {
				throwable.printStackTrace();
			}
		} else {
			pluginInstance.getLog().log(status);
		}
		return status;
	}
	
}
