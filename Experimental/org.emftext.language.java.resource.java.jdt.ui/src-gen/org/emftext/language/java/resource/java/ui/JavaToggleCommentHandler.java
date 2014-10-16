/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import java.util.LinkedHashMap;
import java.util.Map;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

public class JavaToggleCommentHandler extends AbstractHandler {
	
	public static String[] COMMENT_PREFIXES = new String[] { "//" };
	
	private IDocument document;
	private ITextOperationTarget operationTarget;
	private Map<String, String[]> prefixesMap;
	
	@Override
	
	public boolean isEnabled() {
		IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (activeEditor instanceof org.emftext.language.java.resource.java.ui.JavaEditor) {
			ITextOperationTarget operationTarget = (ITextOperationTarget) activeEditor.getAdapter(ITextOperationTarget.class);
			return (operationTarget != null && operationTarget.canDoOperation(ITextOperationTarget.PREFIX) && operationTarget.canDoOperation(ITextOperationTarget.STRIP_PREFIX));
		}
		return false;
	}
	
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
		org.emftext.language.java.resource.java.ui.JavaEditor editor = null;
		
		if (editorPart instanceof org.emftext.language.java.resource.java.ui.JavaEditor) {
			editor = (org.emftext.language.java.resource.java.ui.JavaEditor) editorPart;
			operationTarget = (ITextOperationTarget) editorPart.getAdapter(ITextOperationTarget.class);
			document = editor.getDocumentProvider().getDocument(editor.getEditorInput());
		}
		if (editor == null || operationTarget == null || document == null) {
			return null;
		}
		
		prefixesMap = new LinkedHashMap<String, String[]>();
		prefixesMap.put(IDocument.DEFAULT_CONTENT_TYPE, COMMENT_PREFIXES);
		
		ISelection currentSelection = editor.getSelectionProvider().getSelection();
		final int operationCode;
		if (isSelectionCommented(currentSelection)) {
			operationCode = ITextOperationTarget.STRIP_PREFIX;
		} else {
			operationCode = ITextOperationTarget.PREFIX;
		}
		
		if (!operationTarget.canDoOperation(operationCode)) {
			return null;
		}
		
		Shell shell = editorPart.getSite().getShell();
		Display display = null;
		if (shell != null && !shell.isDisposed()) {
			display = shell.getDisplay();
		}
		
		BusyIndicator.showWhile(display, new  Runnable() {
			public void run() {
				operationTarget.doOperation(operationCode);
			}
		});
		
		return null;
	}
	
	// Parts of the implementation below have been copied from
	// org.eclipse.jdt.internal.ui.javaeditor.ToggleCommentAction.
	private boolean isSelectionCommented(ISelection selection) {
		if (!(selection instanceof ITextSelection)) {
			return false;
		}
		ITextSelection textSelection = (ITextSelection) selection;
		if (textSelection.getStartLine() < 0 || textSelection.getEndLine() < 0) {
			return false;
		}
		try {
			IRegion block = getTextBlockFromSelection(textSelection, document);
			ITypedRegion[] regions = TextUtilities.computePartitioning(document, IDocumentExtension3.DEFAULT_PARTITIONING, block.getOffset(), block.getLength(), false);
			int[] lines = new int[regions.length * 2]; // [startline, endline, startline, endline, ...]
			for (int i = 0, j = 0; i < regions.length; i++, j+= 2) {
				// start line of region
				lines[j] = getFirstCompleteLineOfRegion(regions[i], document);
				// end line of region
				int length = regions[i].getLength();
				int offset = regions[i].getOffset() + length;
				if (length > 0) {
					offset--;
				}
				lines[j + 1] = (lines[j] == -1 ? -1 : document.getLineOfOffset(offset));
			}
			// Perform the check
			for (int i = 0, j = 0; i < regions.length; i++, j += 2) {
				String[] prefixes = prefixesMap.get(regions[i].getType());
				if (prefixes != null && prefixes.length > 0 && lines[j] >= 0 && lines[j + 1] >= 0) {
					if (!isBlockCommented(lines[j], lines[j + 1], prefixes, document)) {
						return false;
					}
				}
			}
			return true;
		} catch (BadLocationException x) {
			// should not happen
		}
		return false;
	}
	
	private IRegion getTextBlockFromSelection(ITextSelection selection, IDocument document) {
		try {
			IRegion line = document.getLineInformationOfOffset(selection.getOffset());
			int length = selection.getLength() == 0 ? line.getLength() : selection.getLength() + (selection.getOffset() - line.getOffset());
			return new Region(line.getOffset(), length);
		} catch (BadLocationException x) {
			// should not happen
		}
		return null;
	}
	
	private int getFirstCompleteLineOfRegion(IRegion region, IDocument document) {
		try {
			final int startLine = document.getLineOfOffset(region.getOffset());
			int offset = document.getLineOffset(startLine);
			if (offset >= region.getOffset()) {
				return startLine;
			}
			final int nextLine = startLine + 1;
			if (nextLine == document.getNumberOfLines()) {
				return -1;
			}
			offset = document.getLineOffset(nextLine);
			return (offset > region.getOffset() + region.getLength() ? -1 : nextLine);
		} catch (BadLocationException x) {
			// should not happen
		}
		return -1;
	}
	
	private boolean isBlockCommented(int startLine, int endLine, String[] prefixes, IDocument document) {
		try {
			// check for occurrences of prefixes in the given lines
			for (int i = startLine; i <= endLine; i++) {
				IRegion line = document.getLineInformation(i);
				String text = document.get(line.getOffset(), line.getLength());
				int[] found = TextUtilities.indexOf(prefixes, text, 0);
				if (found[0] == -1) {
					// found a line which is not commented
					return false;
				}
				String s = document.get(line.getOffset(), found[0]);
				s = s.trim();
				if (s.length() != 0) {
					// found a line which is not commented
					return false;
				}
			}
			return true;
		} catch (BadLocationException x) {
			// should not happen
		}
		return false;
	}
	
}
