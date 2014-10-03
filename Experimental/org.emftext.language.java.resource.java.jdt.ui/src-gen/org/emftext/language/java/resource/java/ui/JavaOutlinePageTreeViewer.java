/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import java.util.LinkedHashSet;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil.EqualityHelper;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.jface.viewers.IElementComparer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;

/**
 * This custom implementation of a TreeViewer expands the tree automatically up to
 * a specified depth.
 */
public class JavaOutlinePageTreeViewer extends TreeViewer {
	
	public class TypeFilter extends ViewerFilter {
		
		private Set<EClass> filteredTypes = new LinkedHashSet<EClass>();
		
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof EObject) {
				EObject eObject = (EObject) element;
				for (EClass filteredType : filteredTypes) {
					if (filteredType.isInstance(eObject)) {
						return false;
					}
				}
			}
			return true;
		}
		
		public Set<EClass> getFilteredTypes() {
			return filteredTypes;
		}
	}
	
	private static class FlatEObjectComparer extends EqualityHelper {
		
		private static final long serialVersionUID = 1L;
		
		@Override
		protected boolean haveEqualReference(EObject eObject1, EObject eObject2, EReference reference) {
			return true;
		}
		
		@Override
		protected boolean equalFeatureMaps(FeatureMap featureMap1, FeatureMap featureMap2) {
			return true;
		}
		
	}
	
	private boolean suppressNotifications = false;
	
	private boolean linkWithEditor = false;
	
	private boolean autoExpand = false;
	
	private TypeFilter typeFilter = new TypeFilter();
	
	public JavaOutlinePageTreeViewer(Composite parent, int style) {
		super(parent, style);
		addFilter(typeFilter);
		setComparer(new IElementComparer() {
			
			public int hashCode(Object element) {
				String s = toString(element);
				if (s != null) {
					return s.hashCode();
				}
				return element.hashCode();
			}
			
			public boolean equals(Object o1, Object o2) {
				if (o1 instanceof EObject && o2 instanceof EObject) {
					return new FlatEObjectComparer().equals((EObject) o1, (EObject) o2);
				}
				String s1 = toString(o1);
				String s2 = toString(o2);
				if (s1 != null) {
					return s1.equals(s2);
				}
				return o1.equals(o2);
			}
			
			private String toString(Object o) {
				if (o instanceof String) {
					return (String) o;
				}
				if (o instanceof Resource) {
					return ((Resource) o).getURI().toString();
				}
				return null;
			}
			
		});
		
	}
	
	public void setSelection(ISelection selection, boolean reveal) {
		if (!linkWithEditor) {
			return;
		}
		if (selection instanceof org.emftext.language.java.resource.java.ui.JavaEObjectSelection) {
			suppressNotifications = true;
			super.setSelection(selection, reveal);
			suppressNotifications = false;
		}
		else {
			super.setSelection(selection, reveal);
		}
	}
	
	protected void handleSelect(SelectionEvent event) {
		if (event.item == null) {
			// In the cases of an invalid document, the tree widget in the outline might fire
			// an event (with item == null) without user interaction. We do not want to react
			// to that event.
		} else {
			super.handleSelect(event);
		}
	}
	
	protected void handleInvalidSelection(ISelection selection, ISelection newSelection) {
		// this may not fire a selection changed event to avoid cyclic events between
		// editor and outline
	}
	
	public void refresh(Object element, boolean updateLabels) {
		super.refresh(element, updateLabels);
		doAutoExpand();
	}
	
	public void refresh(Object element) {
		super.refresh(element);
		doAutoExpand();
	}
	
	public void refresh() {
		super.refresh();
		doAutoExpand();
	}
	
	public void refresh(boolean updateLabels) {
		super.refresh(updateLabels);
		doAutoExpand();
	}
	
	public void setAutoExpand(boolean autoExpand) {
		this.autoExpand = autoExpand;
	}
	
	public void expandToLevel(int level) {
		// we need to catch exceptions here, because refreshing the outline does sometimes
		// cause the LabelProviders to throw exceptions, if the model is in some
		// inconsistent state.
		try {
			super.expandToLevel(level);
		} catch (Exception e) {
			org.emftext.language.java.resource.java.mopp.JavaPlugin.logError("Exception while refreshing outline view", e);
		}
	}
	
	protected void fireSelectionChanged(SelectionChangedEvent event) {
		if (suppressNotifications) {
			return;
		}
		super.fireSelectionChanged(event);
	}
	
	public void setLinkWithEditor(boolean on) {
		this.linkWithEditor = on;
	}
	
	private void doAutoExpand() {
		if (!autoExpand) {
			return;
		}
		expandToLevel(getAutoExpandLevel());
	}
	
	public void addTypeToFilter(EClass typeToFilter) {
		typeFilter.getFilteredTypes().add(typeToFilter);
	}
	
	public void removeTypeToFilter(EClass typeToNotFilter) {
		typeFilter.getFilteredTypes().remove(typeToNotFilter);
	}
	
	public void setSuppressNotifications(boolean suppressNotifications) {
		this.suppressNotifications = suppressNotifications;
	}
	
}
