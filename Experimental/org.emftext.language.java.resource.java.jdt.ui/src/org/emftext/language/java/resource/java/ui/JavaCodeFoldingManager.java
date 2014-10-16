/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotationModel;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.XMLMemento;
import org.osgi.framework.Bundle;

/**
 * This manager adds new projection annotations for the code folding and deletes
 * old projection annotations with lines < 3. It is needed to hold the toggle
 * states. It provides the ability to restore the toggle states between Eclipse
 * sessions and after closing, opening as well.
 */
public class JavaCodeFoldingManager {
	
	private class EditorOnCloseListener implements IPartListener2 {
		
		private org.emftext.language.java.resource.java.ui.JavaEditor editor;
		
		public EditorOnCloseListener(org.emftext.language.java.resource.java.ui.JavaEditor editor) {
			this.editor = editor;
		}
		
		public void partActivated(IWorkbenchPartReference partRef) {
		}
		
		public void partBroughtToTop(IWorkbenchPartReference partRef) {
		}
		
		public void partClosed(IWorkbenchPartReference partRef) {
			if (partRef.isDirty()) {
				return;
			}
			IWorkbenchPart workbenchPart = partRef.getPart(false);
			if (workbenchPart instanceof org.emftext.language.java.resource.java.ui.JavaEditor) {
				org.emftext.language.java.resource.java.ui.JavaEditor editor = (org.emftext.language.java.resource.java.ui.JavaEditor) workbenchPart;
				Resource editorResource = editor.getResource();
				if (editorResource == null) {
					return;
				}
				String uri = editorResource.getURI().toString();
				Resource thisEditorResource = this.editor.getResource();
				URI thisEditorResourceURI = thisEditorResource.getURI();
				if (uri.equals(thisEditorResourceURI.toString())) {
					saveCodeFoldingStateFile(uri);
					editor.getSite().getPage().removePartListener(this);
				}
			}
		}
		
		public void partDeactivated(IWorkbenchPartReference partRef) {
		}
		
		public void partHidden(IWorkbenchPartReference partRef) {
		}
		
		public void partInputChanged(IWorkbenchPartReference partRef) {
		}
		
		public void partOpened(IWorkbenchPartReference partRef) {
		}
		
		public void partVisible(IWorkbenchPartReference partRef) {
		}
		
	}
	
	private static final String VERIFY_KEY = "verify_key";
	private static final String ANNOTATION = "ANNOTATION";
	private static final String IS_COLLAPSED = "IS_COLLAPSED";
	private static final String OFFSET = "OFFSET";
	private static final String LENGTH = "LENGTH";
	private static final String MODEL = "MODEL";
	protected List<ProjectionAnnotation> oldAnnotations = new ArrayList<ProjectionAnnotation>();
	protected Map<ProjectionAnnotation, Position> additions = new LinkedHashMap<ProjectionAnnotation, Position>();
	protected ProjectionAnnotationModel projectionAnnotationModel;
	protected ProjectionViewer sourceViewer;
	protected org.emftext.language.java.resource.java.ui.JavaEditor editor;
	
	/**
	 * <p>
	 * Creates a code folding manager to handle the <code>ProjectionAnnotation</code>.
	 * </p>
	 * 
	 * @param sourceViewer the source viewer to calculate the element lines
	 */
	public JavaCodeFoldingManager(ProjectionViewer sourceViewer,org.emftext.language.java.resource.java.ui.JavaEditor textEditor) {
		this.projectionAnnotationModel = sourceViewer.getProjectionAnnotationModel();
		this.sourceViewer = sourceViewer;
		this.editor = textEditor;
		addCloseListener(textEditor);
		try {
			restoreCodeFoldingStateFromFile(editor.getResource().getURI().toString());
		} catch (Exception e) {
			calculatePositions();
		}
	}
	
	private void addCloseListener(final org.emftext.language.java.resource.java.ui.JavaEditor editor) {
		editor.getSite().getPage().addPartListener(new EditorOnCloseListener(editor));
	}
	
	/**
	 * <p>
	 * Checks whether the given positions are in the
	 * <code>ProjectionAnnotationModel</code> or in the addition set. If not it tries
	 * to add into <code>additions</code>. Deletes old ProjectionAnnotation with line
	 * count less than 2.
	 * </p>
	 * 
	 * @param positions a list of available foldable positions
	 */
	public void updateCodefolding(List<Position> positions) {
		IDocument document = sourceViewer.getDocument();
		if (document == null) {
			return;
		}
		oldAnnotations.clear();
		Iterator<?> annotationIterator = projectionAnnotationModel.getAnnotationIterator();
		while (annotationIterator.hasNext()) {
			oldAnnotations.add((ProjectionAnnotation) annotationIterator.next());
		}
		// Add new Position with a unique line offset
		for (Position position : positions) {
			if (!isInAdditions(position)) {
				addPosition(position);
			}
		}
		projectionAnnotationModel.modifyAnnotations(oldAnnotations.toArray(new Annotation[0]), additions, null);
		additions.clear();
	}
	
	/**
	 * <p>
	 * Checks the offset of the given <code>Position</code> against the
	 * <code>Position</code>s in <code>additions</code> to determine the existence
	 * whether the given position is contained in the additions set.
	 * </p>
	 * 
	 * @param position the position to check
	 * 
	 * @return <code>true</code> if it is in the <code>additions</code>
	 */
	private boolean isInAdditions(Position position) {
		for (Annotation addition : additions.keySet()) {
			Position additionPosition = additions.get(addition);
			if (position.offset == additionPosition.offset && position.length == additionPosition.length) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <p>
	 * Tries to add this position into the model. Only positions with more than 3
	 * lines can be taken in. If multiple positions exist on the same line, the
	 * longest will be chosen. The shorter ones will be deleted.
	 * </p>
	 * 
	 * @param position the position to be added.
	 */
	private void addPosition(Position position) {
		IDocument document = sourceViewer.getDocument();
		int lines = 0;
		try {
			lines = document.getNumberOfLines(position.offset, position.length);
		} catch (BadLocationException e) {
			e.printStackTrace();
			return;
		}
		if (lines < 3) {
			return;
		}
		
		// if a position to add existed on the same line, the longest one will be chosen
		try {
			for (ProjectionAnnotation annotationToAdd : additions.keySet()) {
				Position positionToAdd = additions.get(annotationToAdd);
				if (document.getLineOfOffset(position.offset) == document.getLineOfOffset(positionToAdd.offset)) {
					if (positionToAdd.length < position.length) {
						additions.remove(annotationToAdd);
					} else {
						return;
					}
				}
			}
		} catch (BadLocationException e) {
			return;
		}
		for (ProjectionAnnotation annotationInModel : oldAnnotations) {
			Position positionInModel = projectionAnnotationModel.getPosition(annotationInModel);
			if (position.offset == positionInModel.offset && position.length == positionInModel.length) {
				oldAnnotations.remove(annotationInModel);
				return;
			}
		}
		
		additions.put(new ProjectionAnnotation(), position);
	}
	
	/**
	 * Saves the code folding state into the given memento.
	 */
	public void saveCodeFolding(IMemento memento) {
		// The annotation model might be null if the editor opened an storage input
		// instead of a file input.
		if (projectionAnnotationModel == null) {
			return;
		}
		Iterator<?> annotationIt = projectionAnnotationModel.getAnnotationIterator();
		while (annotationIt.hasNext()) {
			ProjectionAnnotation annotation = (ProjectionAnnotation) annotationIt.next();
			IMemento annotationMemento = memento.createChild(ANNOTATION);
			Position position = projectionAnnotationModel.getPosition(annotation);
			annotationMemento.putBoolean(IS_COLLAPSED, annotation.isCollapsed());
			annotationMemento.putInteger(OFFSET, position.offset);
			annotationMemento.putInteger(LENGTH, position.length);
		}
	}
	
	/**
	 * Restores the code folding state information from the given memento.
	 */
	public void restoreCodeFolding(IMemento memento) {
		if (memento == null) {
			return;
		}
		IMemento[] annotationMementos = memento.getChildren(ANNOTATION);
		if (annotationMementos == null) {
			return;
		}
		Map<ProjectionAnnotation, Boolean> collapsedStates = new LinkedHashMap<ProjectionAnnotation, Boolean>();
		for (IMemento annotationMemento : annotationMementos) {
			ProjectionAnnotation annotation = new ProjectionAnnotation();
			collapsedStates.put(annotation, annotationMemento.getBoolean(IS_COLLAPSED));
			int offset = annotationMemento.getInteger(OFFSET);
			int length = annotationMemento.getInteger(LENGTH);
			Position position = new Position(offset, length);
			projectionAnnotationModel.addAnnotation(annotation, position);
		}
		// postset collapse state to prevent wrong displaying folding code.
		for (ProjectionAnnotation annotation : collapsedStates.keySet()) {
			Boolean isCollapsed = collapsedStates.get(annotation);
			if (isCollapsed != null && isCollapsed.booleanValue()) {
				projectionAnnotationModel.collapse(annotation);
			}
		}
	}
	
	/**
	 * <p>
	 * Restores the code folding state from a XML file in the state location.
	 * </p>
	 * 
	 * @param uriString the key to determine the file to load the state from
	 */
	public void restoreCodeFoldingStateFromFile(String uriString) {
		final File stateFile = getCodeFoldingStateFile(uriString);
		if (stateFile == null || !stateFile.exists()) {
			calculatePositions();
			return;
		}
		SafeRunner.run(new SafeRunnable("Unable to read code folding state. The state will be reset.") {
			public void run() throws Exception {
				FileInputStream input = new FileInputStream(stateFile);
				BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf-8"));
				IMemento memento = XMLMemento.createReadRoot(reader);
				reader.close();
				String sourceText = sourceViewer.getDocument().get();
				if (memento.getString(VERIFY_KEY).equals(makeMD5(sourceText))) {
					restoreCodeFolding(memento);
				} else {
					calculatePositions();
				}
			}
		});
	}
	
	/**
	 * <p>
	 * Saves the code folding state to a XML file in the state location.
	 * </p>
	 * 
	 * @param uriString the key to determine the file to save to
	 */
	public void saveCodeFoldingStateFile(String uriString) {
		IDocument document = sourceViewer.getDocument();
		if (document == null) {
			return;
		}
		XMLMemento codeFoldingMemento = XMLMemento.createWriteRoot(MODEL);
		codeFoldingMemento.putString(VERIFY_KEY, makeMD5(document.get()));
		saveCodeFolding(codeFoldingMemento);
		File stateFile = getCodeFoldingStateFile(uriString);
		if (stateFile == null) {
			return;
		}
		try {
			FileOutputStream stream = new FileOutputStream(stateFile);
			OutputStreamWriter writer = new OutputStreamWriter(stream, "utf-8");
			codeFoldingMemento.save(writer);
			writer.close();
		} catch (IOException e) {
			stateFile.delete();
			MessageDialog.openError((Shell) null, "Saving Problems", "Unable to save code folding state.");
		}
	}
	
	private File getCodeFoldingStateFile(String uriString) {
		Bundle bundle = Platform.getBundle(org.emftext.language.java.resource.java.ui.JavaUIPlugin.PLUGIN_ID);
		IPath path = Platform.getStateLocation(bundle);
		if (path == null) {
			return null;
		}
		path = path.append(makeMD5(uriString) + ".xml");
		return path.toFile();
	}
	
	private String makeMD5(String text) {
		MessageDigest md = null;
		byte[] encryptMsg = null;
		try {
			md = MessageDigest.getInstance("MD5");
			encryptMsg = md.digest(text.getBytes());
		} catch (NoSuchAlgorithmException e) {
			org.emftext.language.java.resource.java.ui.JavaUIPlugin.logError("NoSuchAlgorithmException while creating MD5 checksum.", e);
			return "";
		}
		String swap = "";
		String byteStr = "";
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i <= encryptMsg.length - 1; i++) {
			byteStr = Integer.toHexString(encryptMsg[i]);
			switch (byteStr.length()) {
				case 1:				// if hex-number length is 1, add a '0' before
				swap = "0" + Integer.toHexString(encryptMsg[i]);
				break;
				case 2:				// correct hex-letter
				swap = Integer.toHexString(encryptMsg[i]);
				break;
				case 8:				// get the correct substring
				swap = (Integer.toHexString(encryptMsg[i])).substring(6, 8);
				break;
			}
			strBuf.append(swap);
			// appending swap to get complete hash-key
		}
		return strBuf.toString();
	}
	
	protected void calculatePositions() {
		org.emftext.language.java.resource.java.mopp.JavaResource textResource = (org.emftext.language.java.resource.java.mopp.JavaResource) editor.getResource();
		IDocument document = sourceViewer.getDocument();
		if (textResource == null || document == null) {
			return;
		}
		if (textResource.hasErrors()) {
			return;
		}
		final List<Position> positions = new ArrayList<Position>();
		org.emftext.language.java.resource.java.IJavaLocationMap locationMap = textResource.getLocationMap();
		EClass[] foldableClasses = textResource.getMetaInformation().getFoldableClasses();
		if (foldableClasses == null) {
			return;
		}
		if (foldableClasses.length < 1) {
			return;
		}
		List<EObject> contents = textResource.getContents();
		EObject[] contentArray = contents.toArray(new EObject[0]);
		List<EObject> allContents = getAllContents(contentArray);
		for (EObject nextObject : allContents) {
			boolean isFoldable = false;
			for (EClass eClass : foldableClasses) {
				if (nextObject.eClass().equals(eClass)) {
					isFoldable = true;
					break;
				}
			}
			if (!isFoldable) {
				continue;
			}
			int offset = locationMap.getCharStart(nextObject);
			int length = locationMap.getCharEnd(nextObject) + 1 - offset;
			try {
				int lines = document.getNumberOfLines(offset, length);
				if (lines < 2) {
					continue;
				}
			} catch (BadLocationException e) {
				continue;
			}
			length = getOffsetOfNextLine(document, length + offset) - offset;
			if (offset >= 0 && length > 0) {
				positions.add(new Position(offset, length));
			}
		}
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				updateCodefolding(positions);
			}
		});
	}
	
	private List<EObject> getAllContents(EObject[] contentArray) {
		List<EObject> result = new ArrayList<EObject>();
		for (EObject eObject : contentArray) {
			if (eObject == null) {
				continue;
			}
			result.add(eObject);
			List<EObject> contents = eObject.eContents();
			if (contents == null) {
				continue;
			}
			result.addAll(getAllContents(contents.toArray(new EObject[0])));
		}
		return result;
	}
	
	private int getOffsetOfNextLine(IDocument document, int offset) {
		int end = document.getLength();
		int nextLineOffset = offset;
		if (offset < 0 || offset > end) {
			return -1;
		}
		while (nextLineOffset < end) {
			String charAtOffset = "";
			try {
				charAtOffset += document.getChar(nextLineOffset);
			} catch (BadLocationException e) {
				return -1;
			}
			if (charAtOffset.matches("\\S")) {
				return nextLineOffset;
			}
			if (charAtOffset.equals("\n")) {
				return nextLineOffset + 1;
			}
			nextLineOffset++;
		}
		return offset;
	}
	
}
