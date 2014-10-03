/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * A manager class for the highlighting of occurrences and brackets.
 */
public class JavaHighlighting implements ISelectionProvider, ISelectionChangedListener {
	
	private final static org.emftext.language.java.resource.java.ui.JavaPositionHelper positionHelper = new org.emftext.language.java.resource.java.ui.JavaPositionHelper();
	
	private List<ISelectionChangedListener> selectionChangedListeners = new ArrayList<ISelectionChangedListener>();
	private ISelection selection = null;
	private boolean isHighlightBrackets = true;
	private org.emftext.language.java.resource.java.ui.JavaColorManager colorManager;
	private Color bracketColor;
	private Color black;
	private StyledText textWidget;
	private IPreferenceStore preferenceStore;
	private org.emftext.language.java.resource.java.ui.JavaEditor editor;
	private ProjectionViewer projectionViewer;
	private org.emftext.language.java.resource.java.ui.JavaOccurrence occurrence;
	private org.emftext.language.java.resource.java.ui.JavaBracketSet bracketSet;
	private Display display;
	
	/**
	 * A key and mouse listener for the highlighting. It removes the highlighting
	 * before documents change. No highlighting is set after document changes to
	 * increase the performance. Occurrences are not searched if the caret is still in
	 * the same token to increase the performance.
	 */
	private final class UpdateHighlightingListener implements KeyListener, VerifyListener, MouseListener, org.emftext.language.java.resource.java.IJavaBackgroundParsingListener {
		
		private boolean changed = false;
		private int caret = -1;
		
		public void keyPressed(KeyEvent e) {
		}
		
		public void keyReleased(KeyEvent e) {
			if (changed) {
				changed = false;
				return;
			}
			refreshHighlighting();
		}
		
		private void refreshHighlighting() {
			if (textWidget.isDisposed()) {
				return;
			}
			int textCaret = textWidget.getCaretOffset();
			if (textCaret < 0 || textCaret > textWidget.getCharCount()) {
				return;
			}
			if (textCaret != caret) {
				caret = textCaret;
				removeHighlighting();
				setHighlighting();
				updateEObjectSelection();
			}
		}
		
		public void verifyText(VerifyEvent e) {
			occurrence.resetTokenRegion();
			removeHighlighting();
			changed = true;
		}
		
		public void mouseDoubleClick(MouseEvent e) {
		}
		
		public void mouseDown(MouseEvent e) {
		}
		
		// 1-left click, 2-middle click,
		public void mouseUp(MouseEvent e) {
			// 3-right click
			if (e.button != 1) {
				return;
			}
			refreshHighlighting();
		}
		
		public void parsingCompleted(Resource resource) {
			display.asyncExec(new Runnable() {
				
				public void run() {
					refreshHighlighting();
				}
			});
		}
	}
	
	/**
	 * <p>
	 * Creates the highlighting manager class.
	 * </p>
	 * 
	 * @param textResource the text resource to be provided to other classes
	 * @param sourceviewer the source viewer converts offset between master and slave
	 * documents
	 * @param colorManager the color manager provides highlighting colors
	 * @param editor the editor that uses this highlighting object
	 */
	public JavaHighlighting(org.emftext.language.java.resource.java.IJavaTextResource textResource, ProjectionViewer projectionViewer, org.emftext.language.java.resource.java.ui.JavaColorManager colorManager, org.emftext.language.java.resource.java.ui.JavaEditor editor) {
		this.display = Display.getCurrent();
		projectionViewer.getSelectionProvider();
		this.preferenceStore = org.emftext.language.java.resource.java.ui.JavaUIPlugin.getDefault().getPreferenceStore();
		this.editor = editor;
		this.textWidget = projectionViewer.getTextWidget();
		this.projectionViewer = projectionViewer;
		this.occurrence = new org.emftext.language.java.resource.java.ui.JavaOccurrence(textResource, projectionViewer);
		this.bracketSet = new org.emftext.language.java.resource.java.ui.JavaBracketSet();
		this.colorManager = colorManager;
		this.isHighlightBrackets = preferenceStore.getBoolean(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_MATCHING_BRACKETS_CHECKBOX);
		this.bracketColor = colorManager.getColor(PreferenceConverter.getColor(preferenceStore, org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_MATCHING_BRACKETS_COLOR));
		this.black = colorManager.getColor(new RGB(0, 0, 0));
		
		addListeners(editor);
	}
	
	private void addListeners(org.emftext.language.java.resource.java.ui.JavaEditor editor) {
		UpdateHighlightingListener hl = new UpdateHighlightingListener();
		textWidget.addKeyListener(hl);
		textWidget.addVerifyListener(hl);
		textWidget.addMouseListener(hl);
	}
	
	private void setHighlighting() {
		IDocument document = projectionViewer.getDocument();
		if (isHighlightBrackets) {
			int offset = bracketSet.getCaretOffset((ISourceViewer) editor.getViewer(), textWidget);
			bracketSet.findAndHighlightMatchingBrackets(document, offset);
		}
		occurrence.updateOccurrenceAnnotations();
		setBracketHighlighting(document);
	}
	
	private void setBracketHighlighting(IDocument document) {
		StyleRange styleRange = null;
		Position[] positions = positionHelper.getPositions(document, org.emftext.language.java.resource.java.ui.JavaPositionCategory.BRACKET.toString());
		
		for (Position position : positions) {
			Position tmpPosition = convertToWidgetPosition(position);
			if (tmpPosition != null) {
				styleRange = getStyleRangeAtPosition(tmpPosition);
				styleRange.borderStyle = SWT.BORDER_SOLID;
				styleRange.borderColor = bracketColor;
				if (styleRange.foreground == null) {
					styleRange.foreground = black;
				}
				textWidget.setStyleRange(styleRange);
			}
		}
	}
	
	private void removeHighlighting() {
		IDocument document = projectionViewer.getDocument();
		// remove highlighted matching brackets
		removeHighlightingCategory(document, org.emftext.language.java.resource.java.ui.JavaPositionCategory.BRACKET.toString());
	}
	
	private void removeHighlightingCategory(IDocument document, String category) {
		Position[] positions = positionHelper.getPositions(document, category);
		if (category.equals(org.emftext.language.java.resource.java.ui.JavaPositionCategory.BRACKET.toString())) {
			StyleRange styleRange;
			for (Position position : positions) {
				Position tmpPosition = convertToWidgetPosition(position);
				if (tmpPosition != null) {
					styleRange = getStyleRangeAtPosition(tmpPosition);
					styleRange.borderStyle = SWT.NONE;
					styleRange.borderColor = null;
					styleRange.background = null;
					textWidget.setStyleRange(styleRange);
				}
			}
		}
		positionHelper.removePositions(document, category);
	}
	
	/**
	 * Updates the currently selected EObject and notifies registered selection
	 * listeners (e.g., the outline page) about this asynchronously.
	 */
	public void updateEObjectSelection() {
		display.asyncExec(new Runnable() {
			public void run() {
				EObject selectedEObject = occurrence.getEObjectAtCurrentPosition();
				if (selectedEObject != null) {
					setSelection(new org.emftext.language.java.resource.java.ui.JavaEObjectSelection(selectedEObject));
				}
			}
		});
	}
	
	/**
	 * Resets the changed values after setting the preference pages.
	 */
	public void resetValues() {
		isHighlightBrackets = preferenceStore.getBoolean(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_MATCHING_BRACKETS_CHECKBOX);
		bracketColor = colorManager.getColor(PreferenceConverter.getColor(preferenceStore, org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_MATCHING_BRACKETS_COLOR));
		bracketSet.resetBrackets(preferenceStore);
	}
	
	private Position convertToWidgetPosition(Position position) {
		if (position == null) {
			return null;
		}
		int startOffset = projectionViewer.modelOffset2WidgetOffset(position.offset);
		int endOffset = projectionViewer.modelOffset2WidgetOffset(position.offset + position.length);
		if (endOffset - startOffset != position.length || startOffset == -1 || textWidget.getCharCount() < endOffset) {
			return null;
		}
		return new Position(startOffset, endOffset - startOffset);
	}
	
	private StyleRange getStyleRangeAtPosition(Position position) {
		StyleRange styleRange = null;
		try {
			styleRange = textWidget.getStyleRangeAtOffset(position.offset);
		} catch (IllegalArgumentException iae) {
		}
		if (styleRange == null) {
			styleRange = new StyleRange(position.offset, position.length, black, null);
		} else {
			styleRange.length = position.length;
		}
		return styleRange;
	}
	
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}
	
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}
	
	/**
	 * Updates the current selection and notifies registered selection listeners
	 * (e.g., the outline page) about this.
	 */
	public void setSelection(ISelection selection) {
		this.selection = selection;
		SelectionChangedEvent event = new SelectionChangedEvent(this, selection);
		for (ISelectionChangedListener listener : selectionChangedListeners) {
			listener.selectionChanged(event);
		}
	}
	
	public ISelection getSelection() {
		return selection;
	}
	
	/**
	 * This method is called by the outline page if its selection was changed. This is
	 * accomplished by adding this class as selection change listener to the outline
	 * page, which is performed by the editor.
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSelection() instanceof TreeSelection) {
			handleContentOutlineSelection(event.getSelection());
		}
	}
	
	/**
	 * Notifies the editor that the selection in the outline page has changed. This
	 * method assumes that the origin of the selection is the outline page or its tree
	 * viewer.
	 */
	private void handleContentOutlineSelection(ISelection selection) {
		if (selection.isEmpty()) {
			// Ignore empty selections
			return;
		}
	}
	
}
