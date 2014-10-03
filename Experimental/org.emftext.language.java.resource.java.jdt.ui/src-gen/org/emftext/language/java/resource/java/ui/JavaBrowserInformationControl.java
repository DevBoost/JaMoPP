/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.AbstractInformationControl;
import org.eclipse.jface.text.IDelayedInputChangeProvider;
import org.eclipse.jface.text.IInformationControlExtension2;
import org.eclipse.jface.text.IInputChangedListener;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.browser.OpenWindowListener;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;

/**
 * <p>
 * Displays HTML information in a {@link Browser} widget.
 * </p>
 * <p>
 * <p>
 * </p>
 * <p>
 * This IInformationControlExtension2 expects {@link #setInput(Object)} to be
 * called with an argument of type BrowserInformationControlInput.
 * </p>
 * <p>
 * </p>
 * </p>
 * <p>
 * <p>Moved into this package from
 * <code>org.eclipse.jface.internal.text.revisions</code>.</p>
 * </p>
 * <p>
 * <p>This class may be instantiated; it is not intended to be subclassed.</p>
 * </p>
 * <p>
 * <p>Current problems:
 * </p>
 * <p>
 * <ul>
 * </p>
 * <p>
 * 	<li>the size computation is too small</li>
 * </p>
 * <p>
 * 	<li>focusLost event is not sent - see
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=84532</li>
 * </p>
 * <p>
 * </ul>
 * </p>
 * <p>
 * </p>
 * </p>
 * 
 * @since 3.2
 */
public class JavaBrowserInformationControl extends AbstractInformationControl implements IInformationControlExtension2, IDelayedInputChangeProvider {
	
	/**
	 * <p>
	 * Tells whether the SWT Browser widget and hence this information control is
	 * available.
	 * </p>
	 * 
	 * @param parent the parent component used for checking or <code>null</code> if
	 * none
	 * 
	 * @return <code>true</code> if this control is available
	 */
	public static boolean isAvailable(Composite parent) {
		if (!fgAvailabilityChecked) {
			try {
				Browser browser= new Browser(parent, SWT.NONE);
				browser.dispose();
				fgIsAvailable= true;
				
				Slider sliderV= new Slider(parent, SWT.VERTICAL);
				Slider sliderH= new Slider(parent, SWT.HORIZONTAL);
				int width= sliderV.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
				int height= sliderH.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
				fgScrollBarSize= new Point(width, height);
				sliderV.dispose();
				sliderH.dispose();
			} catch (SWTError er) {
				fgIsAvailable= false;
			} finally {
				fgAvailabilityChecked= true;
			}
		}
		
		return fgIsAvailable;
	}
	
	
	/**
	 * Minimal size constraints.
	 */
	private static final int MIN_WIDTH = 80;
	private static final int MIN_HEIGHT = 50;
	
	
	/**
	 * Availability checking cache.
	 */
	private static boolean fgIsAvailable = false;
	private static boolean fgAvailabilityChecked = false;
	
	/**
	 * Cached scroll bar width and height
	 */
	private static Point fgScrollBarSize;
	
	/**
	 * The control's browser widget
	 */
	private Browser fBrowser;
	/**
	 * Tells whether the browser has content
	 */
	private boolean fBrowserHasContent;
	/**
	 * Text layout used to approximate size of content when rendered in browser
	 */
	private TextLayout fTextLayout;
	/**
	 * Bold text style
	 */
	private TextStyle fBoldStyle;
	
	private org.emftext.language.java.resource.java.ui.JavaDocBrowserInformationControlInput fInput;
	
	/**
	 * <code>true</code> iff the browser has completed loading of the last input set
	 * via {@link #setInformation(String)}.
	 */
	private boolean fCompleted = false;
	
	/**
	 * The listener to be notified when a delayed location changing event happened.
	 */
	private IInputChangedListener fDelayedInputChangeListener;
	
	/**
	 * The listeners to be notified when the input changed.
	 */
	private ListenerList fInputChangeListeners = new ListenerList(ListenerList.IDENTITY);
	
	/**
	 * The symbolic name of the font used for size computations, or <code>null</code>
	 * to use dialog font.
	 */
	private final String fSymbolicFontName;
	
	/**
	 * <p>
	 * Creates a browser information control with the given shell as parent.
	 * </p>
	 * 
	 * @param parent the parent shell
	 * @param symbolicFontName the symbolic name of the font used for size computations
	 * @param resizable <code>true</code> if the control should be resizable
	 */
	public JavaBrowserInformationControl(Shell parent, String symbolicFontName, boolean resizable) {
		super(parent, resizable);
		fSymbolicFontName= symbolicFontName;
		create();
	}
	
	/**
	 * <p>
	 * Creates a browser information control with the given shell as parent.
	 * </p>
	 * 
	 * @param parent the parent shell
	 * @param symbolicFontName the symbolic name of the font used for size computations
	 * @param statusFieldText the text to be used in the optional status field or
	 * <code>null</code> if the status field should be hidden
	 */
	public JavaBrowserInformationControl(Shell parent, String symbolicFontName, String statusFieldText) {
		super(parent, statusFieldText);
		fSymbolicFontName= symbolicFontName;
		create();
	}
	
	/**
	 * <p>
	 * Creates a browser information control with the given shell as parent.
	 * </p>
	 * 
	 * @param parent the parent shell
	 * @param symbolicFontName the symbolic name of the font used for size computations
	 * @param toolBarManager the manager or <code>null</code> if toolbar is not desired
	 * 
	 * @since 3.4
	 */
	public JavaBrowserInformationControl(Shell parent, String symbolicFontName, ToolBarManager toolBarManager) {
		super(parent, toolBarManager);
		fSymbolicFontName= symbolicFontName;
		create();
	}
	
	/**
	 * 
	 * @see org.eclipse.jface.text.AbstractInformationControl#createContent(Composite)
	 */
	protected void createContent(Composite parent) {
		fBrowser= new Browser(parent, SWT.NONE);
		Display display= getShell().getDisplay();
		fBrowser.setForeground(display.getSystemColor(SWT.COLOR_INFO_FOREGROUND));
		fBrowser.setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		fBrowser.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e)  {
				if (e.character == 0x1B) // ESC
				dispose(); // XXX: Just hide? Would avoid constant recreations.
			}
			
			public void keyReleased(KeyEvent e) {}
		});
		
		fBrowser.addProgressListener(new ProgressAdapter() {
			public void completed(ProgressEvent event) {
				fCompleted= true;
			}
		});
		
		fBrowser.addOpenWindowListener(new OpenWindowListener() {
			public void open(WindowEvent event) {
				event.required= true; // Cancel opening of new windows
			}
		});
		
		// Replace browser's built-in context menu with none
		fBrowser.setMenu(new Menu(getShell(), SWT.NONE));
		
		createTextLayout();
	}
	
	
	/**
	 * {@inheritDoc} This control can handle {@link String}(no handle) and
	 */
	public void setInput(Object input) {
		Assert.isLegal(input == null || input instanceof String || input instanceof org.emftext.language.java.resource.java.ui.JavaDocBrowserInformationControlInput);
		
		if (input instanceof String) {
			setInformation((String)input);
			return;
		}
		
		fInput= (org.emftext.language.java.resource.java.ui.JavaDocBrowserInformationControlInput) input;
		
		String content= null;
		if (fInput != null)		content= fInput.getHtml();
		
		fBrowserHasContent= content != null && content.length() > 0;
		
		if (!fBrowserHasContent)		content= "<html><body ></html>";
		
		boolean RTL= (getShell().getStyle() & SWT.RIGHT_TO_LEFT) != 0;
		boolean resizable= isResizable();
		
		// The default "overflow:auto" would not result in a predictable width for the
		// client area and the re-wrapping would cause visual noise
		String[] styles= null;
		if (RTL && resizable) {
			styles= new String[] { "direction:rtl;", "overflow:scroll;", "word-wrap:break-word;" };
		} else if (RTL && !resizable) {
			styles= new String[] { "direction:rtl;", "overflow:hidden;", "word-wrap:break-word;" };
		} else if (!resizable) {
			// XXX: In IE, "word-wrap: break-word;" causes bogus wrapping even in non-broken
			// words :-(see e.g. Javadoc of String). Re-check whether we really still need
			// this now that the Javadoc Hover header already sets this style.
			styles= new String[] { "overflow:hidden;"/*, "word-wrap: break-word;"*/ };
		} else {
			styles= new String[] { "overflow:scroll;" };
		}
		
		StringBuffer buffer= new StringBuffer(content);
		org.emftext.language.java.resource.java.ui.JavaHTMLPrinter.insertStyles(buffer, styles);
		content= buffer.toString();
		
		// XXX: Should add some JavaScript here that shows something like "(continued...)"
		// or "..." at the end of the visible area when the page overflowed with
		// "overflow:hidden;".
		
		fCompleted= false;
		fBrowser.setText(content);
		
		Object[] listeners= fInputChangeListeners.getListeners();
		for (int i= 0; i < listeners.length; i++) {
			((IInputChangedListener)listeners[i]).inputChanged(fInput);
		}
	}
	
	public void setVisible(boolean visible) {
		Shell shell= getShell();
		if (shell.isVisible() == visible) {
			return;
		}
		
		if (!visible) {
			super.setVisible(false);
			setInput(null);
			return;
		}
		
		// The Browser widget flickers when made visible while it is not completely
		// loaded. The fix is to delay the call to setVisible until either loading is
		// completed (see ProgressListener in constructor), or a timeout has been reached.
		final Display display = shell.getDisplay();
		
		// Make sure the display wakes from sleep after timeout:
		display.timerExec(100, new Runnable() {
			public void run() {
				fCompleted= true;
			}
		});
		
		while (!fCompleted) {
			// Drive the event loop to process the events required to load the browser
			// widget's contents:
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		shell = getShell();
		if (shell == null || shell.isDisposed()) {
			return;
		}
		
		// Avoids flickering when replacing hovers, especially on Vista in ON_CLICK mode.
		// Causes flickering on GTK. Carbon does not care.
		if ("win32".equals(SWT.getPlatform())) {
			shell.moveAbove(null);
		}
		
		super.setVisible(true);
	}
	
	/**
	 * 
	 * @see AbstractInformationControl#setSize(int, int)
	 */
	public void setSize(int width, int height) {
		fBrowser.setRedraw(false); // avoid flickering
		try {
			super.setSize(width, height);
		} finally {
			fBrowser.setRedraw(true);
		}
	}
	
	/**
	 * <p>
	 * Creates and initializes the text layout used to compute the size hint.
	 * </p>
	 * 
	 * @since 3.2
	 */
	private void createTextLayout() {
		fTextLayout= new TextLayout(fBrowser.getDisplay());
		
		// Initialize fonts
		String symbolicFontName= fSymbolicFontName == null ? JFaceResources.DIALOG_FONT : fSymbolicFontName;
		Font font = JFaceResources.getFont(symbolicFontName);
		fTextLayout.setFont(font);
		fTextLayout.setWidth(-1);
		font = JFaceResources.getFontRegistry().getBold(symbolicFontName);
		fBoldStyle = new TextStyle(font, null, null);
		
		// Compute and set tab width
		fTextLayout.setText("    ");
		int tabWidth = fTextLayout.getBounds().width;
		fTextLayout.setTabs(new int[] {tabWidth});
		
		fTextLayout.setText("");
	}
	
	public void dispose() {
		if (fTextLayout != null) {
			fTextLayout.dispose();
			fTextLayout = null;
		}
		fBrowser = null;
		
		super.dispose();
	}
	
	public Point computeSizeHint() {
		Point sizeConstraints = getSizeConstraints();
		Rectangle trim = computeTrim();
		int height = trim.height;
		
		TextPresentation presentation= new TextPresentation();
		String text;
		try {
			text = org.emftext.language.java.resource.java.ui.JavaHTMLPrinter.html2text(new StringReader(fInput.getHtml()), presentation);
		} catch (IOException e) {
			text = "";
		}
		fTextLayout.setText(text);
		fTextLayout.setWidth(sizeConstraints == null ? SWT.DEFAULT : sizeConstraints.x - trim.width);
		Iterator<?> iter= presentation.getAllStyleRangeIterator();
		while (iter.hasNext()) {
			StyleRange sr= (StyleRange)iter.next();
			if (sr.fontStyle == SWT.BOLD) {
				fTextLayout.setStyle(fBoldStyle, sr.start, sr.start + sr.length - 1);
			}
		}
		
		Rectangle bounds= fTextLayout.getBounds(); // does not return minimum width, see https://bugs.eclipse.org/bugs/show_bug.cgi?id=217446
		int lineCount= fTextLayout.getLineCount();
		int textWidth= 0;
		for (int i= 0; i < lineCount; i++) {
			Rectangle rect= fTextLayout.getLineBounds(i);
			int lineWidth= rect.x + rect.width;
			if (i == 0) {
				lineWidth += fInput.getLeadingImageWidth();
			}
			textWidth= Math.max(textWidth, lineWidth);
		}
		bounds.width= textWidth;
		fTextLayout.setText("");
		
		int minWidth= bounds.width;
		height= height + bounds.height;
		
		// Add some air to accommodate for different browser renderings
		minWidth+= 15;
		height+= 15;
		
		
		// Apply max size constraints
		if (sizeConstraints != null) {
			if (sizeConstraints.x != SWT.DEFAULT) {
				minWidth= Math.min(sizeConstraints.x, minWidth + trim.width);
			}
			if (sizeConstraints.y != SWT.DEFAULT) {
				height= Math.min(sizeConstraints.y, height);
			}
		}
		
		// Ensure minimal size
		int width= Math.max(MIN_WIDTH, minWidth);
		height= Math.max(MIN_HEIGHT, height);
		Point windowSize = new Point(width, height);
		return windowSize;
	}
	
	/**
	 * 
	 * @see org.eclipse.jface.text.IInformationControlExtension3#computeTrim()
	 */
	public Rectangle computeTrim() {
		Rectangle trim = super.computeTrim();
		if (isResizable()) {
			boolean RTL = (getShell().getStyle() & SWT.RIGHT_TO_LEFT) != 0;
			if (RTL) {
				trim.x-= fgScrollBarSize.x;
			}
			trim.width+= fgScrollBarSize.x;
			trim.height+= fgScrollBarSize.y;
		}
		return trim;
	}
	
	/**
	 * <p>
	 * Adds the listener to the collection of listeners who will be notified when the
	 * current location has changed or is about to change.
	 * </p>
	 * 
	 * @param listener the location listener
	 * 
	 * @since 3.4
	 */
	public void addLocationListener(LocationListener listener) {
		fBrowser.addLocationListener(listener);
	}
	
	public void setForegroundColor(Color foreground) {
		super.setForegroundColor(foreground);
		fBrowser.setForeground(foreground);
	}
	
	public void setBackgroundColor(Color background) {
		super.setBackgroundColor(background);
		fBrowser.setBackground(background);
	}
	
	public boolean hasContents() {
		return fBrowserHasContent;
	}
	
	/**
	 * <p>
	 * Adds a listener for input changes to this input change provider. Has no effect
	 * if an identical listener is already registered.
	 * </p>
	 * 
	 * @param inputChangeListener the listener to add
	 * 
	 * @since 3.4
	 */
	public void addInputChangeListener(IInputChangedListener inputChangeListener) {
		Assert.isNotNull(inputChangeListener);
		fInputChangeListeners.add(inputChangeListener);
	}
	
	/**
	 * <p>
	 * Removes the given input change listener from this input change provider. Has no
	 * effect if an identical listener is not registered.
	 * </p>
	 * 
	 * @param inputChangeListener the listener to remove
	 * 
	 * @since 3.4
	 */
	public void removeInputChangeListener(IInputChangedListener inputChangeListener) {
		fInputChangeListeners.remove(inputChangeListener);
	}
	
	/**
	 * 
	 * @see
	 * IDelayedInputChangeProvider#setDelayedInputChangeListener(IInputChangedListener)
	 * 
	 * @since 3.4
	 */
	public void setDelayedInputChangeListener(IInputChangedListener inputChangeListener) {
		fDelayedInputChangeListener= inputChangeListener;
	}
	
	/**
	 * <p>
	 * Tells whether a delayed input change listener is registered.
	 * </p>
	 * 
	 * @return <code>true</code> iff a delayed input change listener is currently
	 * registered
	 * 
	 * @since 3.4
	 */
	public boolean hasDelayedInputChangeListener() {
		return fDelayedInputChangeListener != null;
	}
	
	/**
	 * <p>
	 * Notifies listeners of a delayed input change.
	 * </p>
	 * 
	 * @param newInput the new input, or <code>null</code> to request cancellation
	 * 
	 * @since 3.4
	 */
	public void notifyDelayedInputChange(Object newInput) {
		if (fDelayedInputChangeListener != null)		fDelayedInputChangeListener.inputChanged(newInput);
	}
	
	/**
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 * @since 3.4
	 */
	public String toString() {
		String style= (getShell().getStyle() & SWT.RESIZE) == 0 ? "fixed" : "resizeable";
		return super.toString() + " -  style: " + style;
	}
	
	/**
	 * 
	 * @return the current browser input or <code>null</code>
	 */
	public org.emftext.language.java.resource.java.ui.JavaDocBrowserInformationControlInput getInput() {
		return fInput;
	}
	
	/**
	 * 
	 * @see
	 * org.eclipse.jface.text.IInformationControlExtension5#computeSizeConstraints(int,
	 * int)
	 */
	public Point computeSizeConstraints(int widthInChars, int heightInChars) {
		if (fSymbolicFontName == null) {
			return null;
		}
		
		GC gc= new GC(fBrowser);
		Font font= fSymbolicFontName == null ? JFaceResources.getDialogFont() : JFaceResources.getFont(fSymbolicFontName);
		gc.setFont(font);
		int width= gc.getFontMetrics().getAverageCharWidth();
		int height= gc.getFontMetrics().getHeight();
		gc.dispose();
		
		return new Point(widthInChars * width, heightInChars * height);
	}
	
}
