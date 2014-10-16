/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewerExtension5;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.custom.StyledText;

/**
 * This class finds text positions to highlight and adds them to the document.
 */
public class JavaOccurrence {
	
	private static interface ITokenScannerConstraint {
		public boolean mustStop(org.emftext.language.java.resource.java.ui.IJavaTokenScanner tokenScanner);
	}
	
	public final static String OCCURRENCE_ANNOTATION_ID = "org.emftext.language.java.resource.java.ui.occurences";
	public final static String DECLARATION_ANNOTATION_ID = "org.emftext.language.java.resource.java.ui.occurences.declaration";
	
	private final static org.emftext.language.java.resource.java.ui.JavaPositionHelper positionHelper = new org.emftext.language.java.resource.java.ui.JavaPositionHelper();
	
	/**
	 * The viewer showing the document the we search occurrences for
	 */
	private ProjectionViewer projectionViewer;
	
	/**
	 * The resource we operate on
	 */
	private org.emftext.language.java.resource.java.IJavaTextResource textResource;
	
	/**
	 * The text of the token that was located at the caret position at the time
	 * occurrence were computed last
	 */
	private String tokenText = "";
	
	/**
	 * The region of the token that was located at the caret position at the time
	 * occurrence were computed last
	 */
	private Region tokenRegion;
	
	/**
	 * <p>
	 * Creates a JavaOccurrence object to find positions to highlight.
	 * </p>
	 * 
	 * @param textResource the text resource for location
	 * @param projectionViewer the viewer for the text
	 */
	public JavaOccurrence(org.emftext.language.java.resource.java.IJavaTextResource textResource, ProjectionViewer projectionViewer) {
		super();
		this.textResource = textResource;
		this.projectionViewer = projectionViewer;
		
		resetTokenRegion();
	}
	
	protected EObject getResolvedEObject(EObject eObject) {
		return eObject.eIsProxy() ? EcoreUtil.resolve(eObject, textResource) : eObject;
	}
	
	/**
	 * <p>
	 * Tries to resolve the first proxy object in the given list.
	 * </p>
	 * 
	 * @param objects the <code>EObject</code>s located at the caret position
	 * 
	 * @return the resolved <code>EObject</code> of the first proxy
	 * <code>EObject</code> in a list. If resolving fails, <code>null</code> is
	 * returned.
	 */
	public EObject tryToResolve(List<EObject> objects) {
		for (EObject object : objects) {
			if (object.eIsProxy()) {
				return getResolvedEObject(object);
			}
		}
		return null;
	}
	
	/**
	 * Returns the EObject at the current caret position.
	 */
	public EObject getEObjectAtCurrentPosition() {
		if (textResource == null) {
			return null;
		}
		
		int caretOffset = getCaretOffset();
		
		org.emftext.language.java.resource.java.IJavaLocationMap locationMap = textResource.getLocationMap();
		List<EObject> elementsAtOffset = locationMap.getElementsAt(caretOffset);
		
		if (elementsAtOffset == null || elementsAtOffset.isEmpty()) {
			return null;
		}
		
		for (EObject candidate : elementsAtOffset) {
			if (candidate.eIsProxy()) {
				candidate = getResolvedEObject(candidate);
			}
			// Only accept elements that are actually contained in a resource. The location
			// map might reference elements that were removed by a post processor and which
			// are therefore not part of the resource anymore.
			if (candidate.eResource() != null) {
				return candidate;
			}
		}
		return null;
	}
	
	/**
	 * <p>
	 * Returns the text of the token that was found at the caret position at the time
	 * occurrence we computed last.
	 * </p>
	 * 
	 * @return the token text
	 */
	protected String getTokenText() {
		return tokenText;
	}
	
	protected int getLength(org.emftext.language.java.resource.java.IJavaLocationMap locationMap, EObject eObject) {
		return locationMap.getCharEnd(eObject) - locationMap.getCharStart(eObject) + 1;
	}
	
	/**
	 * Finds the positions of the occurrences and declarations which will be
	 * highlighted.
	 */
	public void updateOccurrenceAnnotations() {
		if (textResource == null) {
			return;
		}
		
		final int caretOffset = getCaretOffset();
		IDocument document = getSourceViewer().getDocument();
		if (caretOffset < 0 || caretOffset >= document.getLength()) {
			// The caret is outside of the document.
			removeAnnotations();
			return;
		}
		
		if (isContainedIn(tokenRegion, caretOffset)) {
			// The caret is still contained in the same token region. No need to update
			// occurrence annotations.
			return;
		}
		
		resetTokenRegion();
		org.emftext.language.java.resource.java.IJavaLocationMap locationMap = textResource.getLocationMap();
		List<EObject> elementsAtOffset = locationMap.getElementsAt(caretOffset);
		
		if (elementsAtOffset == null || elementsAtOffset.size() < 1) {
			// The document does not contain EObjects. Probably there is a syntax error.
			removeAnnotations();
			return;
		}
		
		removeAnnotations();
		EObject resolvedEObject = tryToResolve(elementsAtOffset);
		EObject referencedElement;
		EObject firstElementAtOffset = elementsAtOffset.get(0);
		if (resolvedEObject != null) {
			referencedElement = resolvedEObject;
		} else {
			referencedElement = firstElementAtOffset;
		}
		
		// Scan the region in which the referenced object is located.
		org.emftext.language.java.resource.java.ui.IJavaTokenScanner tokenScanner = scan(referencedElement, new ITokenScannerConstraint() {
			
			public boolean mustStop(org.emftext.language.java.resource.java.ui.IJavaTokenScanner tokenScanner) {
				int tokenOffset = tokenScanner.getTokenOffset();
				int tokenLength = tokenScanner.getTokenLength();
				// check whether the caret in this token
				return isContainedIn(tokenOffset, tokenLength, caretOffset);
			}
		});
		
		if (tokenScanner != null) {
			// caret is located in referenced element
			removeAnnotations();
			
			int tokenOffset = tokenScanner.getTokenOffset();
			int tokenLength = tokenScanner.getTokenLength();
			tokenText = tokenScanner.getTokenText();
			tokenRegion = new Region(tokenOffset, tokenLength);
		}
		
		// The tokenScanner must always be not null if there was no proxy at the caret
		// position, but to prevent JDT from complaining about a potential null pointer
		// access, we check both conditions here.
		if (resolvedEObject == null && tokenScanner != null) {
			// caret is within definition
			int tokenOffset = tokenScanner.getTokenOffset();
			// we pass null as 'definitionText' because we do not know whether the token at
			// the caret is actually the defining name
			addAnnotations(referencedElement, null, tokenOffset, caretOffset);
		} else {
			// caret is within reference
			int proxyOffset = locationMap.getCharStart(firstElementAtOffset);
			int proxyLength = getLength(locationMap, firstElementAtOffset);
			try {
				String proxyText = document.get(proxyOffset, proxyLength);
				int index = getIndexOf(referencedElement, proxyText);
				if (index >= 0) {
					addAnnotations(referencedElement, proxyText, index, caretOffset);
				}
			} catch (BadLocationException e) {
				// ignore
			}
		}
	}
	
	protected boolean isContainedIn(Region region, int offset) {
		int regionOffset = region.getOffset();
		int regionEnd = regionOffset + region.getLength();
		return offset >= regionOffset && offset <= regionEnd;
	}
	
	protected boolean isContainedIn(int regionOffset, int regionLength, int offset) {
		int regionEnd = regionOffset + regionLength;
		return regionOffset <= offset && offset < regionEnd;
	}
	
	protected void addAnnotations(EObject referencedElement, String definitionText, int definitionOffset, int caretOffset) {
		List<String> matchingNames = addAnnotationsForDefinition(referencedElement, definitionText, definitionOffset, caretOffset);
		addAnnotationsForReferences(referencedElement, matchingNames);
	}
	
	protected void addAnnotationsForReferences(EObject referencedElement, List<String> matchingNames) {
		
		IDocument document = getSourceViewer().getDocument();
		
		// Determine all references to the EObject
		Map<EObject, Collection<Setting>> map = EcoreUtil.UsageCrossReferencer.find(Collections.singleton(textResource));
		Collection<Setting> referencingObjects = map.get(referencedElement);
		if (referencingObjects == null) {
			// No references found
			return;
		}
		
		// Highlight the token in the text for the referencing objects
		for (Setting setting : referencingObjects) {
			EObject referencingElement = setting.getEObject();
			// Search through all tokens in the elements that reference the element at the
			// caret position
			for (String name : matchingNames) {
				int index = getIndexOf(referencingElement, name);
				if (index > 0) {
					addAnnotation(document, org.emftext.language.java.resource.java.ui.JavaPositionCategory.PROXY, name, index, name.length());
				}
			}
		}
	}
	
	protected List<String> addAnnotationsForDefinition(EObject referencedElement, String definitionText, int definitionOffset, final int caretOffset) {
		
		final IDocument document = getSourceViewer().getDocument();
		final List<String> matchingNames = new ArrayList<String>();
		if (definitionText == null) {
			// The object at the caret position is not referenced from within the resource.
			// Thus, we cannot highlight occurrences or declarations.
			final List<String> names = new org.emftext.language.java.resource.java.analysis.JavaDefaultNameProvider().getNames(referencedElement);
			scan(referencedElement, new ITokenScannerConstraint() {
				
				public boolean mustStop(org.emftext.language.java.resource.java.ui.IJavaTokenScanner tokenScanner) {
					int offset = tokenScanner.getTokenOffset();
					int length = tokenScanner.getTokenLength();
					String text = tokenScanner.getTokenText();
					if (names.contains(text) && isContainedIn(offset, length, caretOffset)) {
						matchingNames.add(text);
						addAnnotation(document, org.emftext.language.java.resource.java.ui.JavaPositionCategory.DEFINITION, text, offset, text.length());
					}
					return false;
				}
			});
		} else {
			// Highlight the token in the text for the referenced object
			addAnnotation(document, org.emftext.language.java.resource.java.ui.JavaPositionCategory.DEFINITION, definitionText, definitionOffset, definitionText.length());
			matchingNames.add(definitionText);
		}
		return matchingNames;
	}
	
	/**
	 * Returns the index of the given text within the text that corresponds to  the
	 * EObject.
	 */
	protected int getIndexOf(EObject eObject, final String text) {
		org.emftext.language.java.resource.java.ui.IJavaTokenScanner tokenScanner = scan(eObject, new ITokenScannerConstraint() {
			
			public boolean mustStop(org.emftext.language.java.resource.java.ui.IJavaTokenScanner tokenScanner) {
				String tokenText = tokenScanner.getTokenText();
				return tokenText.equals(text);
			}
		});
		
		if (tokenScanner == null) {
			return -1;
		} else {
			return tokenScanner.getTokenOffset();
		}
	}
	
	protected org.emftext.language.java.resource.java.ui.IJavaTokenScanner scan(EObject object, ITokenScannerConstraint constraint) {
		IDocument document = getSourceViewer().getDocument();
		
		org.emftext.language.java.resource.java.IJavaLocationMap locationMap = textResource.getLocationMap();
		
		org.emftext.language.java.resource.java.ui.IJavaTokenScanner tokenScanner = createTokenScanner();
		int offset = locationMap.getCharStart(object);
		int length = getLength(locationMap, object);
		
		tokenScanner.setRange(document, offset, length);
		IToken token = tokenScanner.nextToken();
		while (!token.isEOF()) {
			if (constraint.mustStop(tokenScanner)) {
				return tokenScanner;
			}
			token = tokenScanner.nextToken();
		}
		return null;
	}
	
	protected org.emftext.language.java.resource.java.ui.IJavaTokenScanner createTokenScanner() {
		return new org.emftext.language.java.resource.java.ui.JavaUIMetaInformation().createTokenScanner(null, null);
	}
	
	protected void addAnnotation(IDocument document, org.emftext.language.java.resource.java.ui.JavaPositionCategory type, String text, int offset, int length) {
		// for declarations and occurrences we do not need to add the position to the
		// document
		Position position = positionHelper.createPosition(offset, length);
		// instead, an annotation is created
		Annotation annotation = new Annotation(false);
		if (type == org.emftext.language.java.resource.java.ui.JavaPositionCategory.DEFINITION) {
			annotation.setText("Declaration of " + text);
			annotation.setType(DECLARATION_ANNOTATION_ID);
		} else {
			annotation.setText("Occurrence of " + text);
			annotation.setType(OCCURRENCE_ANNOTATION_ID);
		}
		getSourceViewer().getAnnotationModel().addAnnotation(annotation, position);
	}
	
	protected void removeAnnotations() {
		removeAnnotations(org.emftext.language.java.resource.java.ui.JavaOccurrence.OCCURRENCE_ANNOTATION_ID);
		removeAnnotations(org.emftext.language.java.resource.java.ui.JavaOccurrence.DECLARATION_ANNOTATION_ID);
	}
	
	protected void removeAnnotations(String annotationTypeID) {
		List<Annotation> annotationsToRemove = new ArrayList<Annotation>();
		IAnnotationModel annotationModel = getSourceViewer().getAnnotationModel();
		Iterator<?> annotationIterator = annotationModel.getAnnotationIterator();
		while (annotationIterator.hasNext()) {
			Object object = (Object) annotationIterator.next();
			if (object instanceof Annotation) {
				Annotation annotation = (Annotation) object;
				if (annotationTypeID.equals(annotation.getType())) {
					annotationsToRemove.add(annotation);
				}
			}
		}
		for (Annotation annotation : annotationsToRemove) {
			annotationModel.removeAnnotation(annotation);
		}
	}
	
	/**
	 * Resets the token region to enable remove highlighting if the text is changing.
	 */
	public void resetTokenRegion() {
		tokenRegion = new Region(-1, 0);
	}
	
	protected int getCaretOffset() {
		StyledText textWidget = getSourceViewer().getTextWidget();
		
		if (textWidget == null) {
			return -1;
		}
		
		int widgetOffset = textWidget.getCaretOffset();
		return getTextViewerExtension5().widgetOffset2ModelOffset(widgetOffset);
	}
	
	/**
	 * Accessor method for the field <code>projectionViewer</code>. The accessor is
	 * also used for unit testing to inject a custom source viewer by overriding this
	 * method.
	 */
	protected ISourceViewer getSourceViewer() {
		return projectionViewer;
	}
	
	/**
	 * Accessor method for the field <code>projectionViewer</code>. The accessor is
	 * also used for unit testing to inject a custom text viewer extension by
	 * overriding this method.
	 */
	protected ITextViewerExtension5 getTextViewerExtension5() {
		return projectionViewer;
	}
	
}
