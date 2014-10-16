/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

public class JavaOutlinePageTreeViewerComparator extends ViewerComparator {
	
	private static Map<EPackage, Integer> ePackageMap = new LinkedHashMap<EPackage, Integer>();
	private static int nextPackageID;
	
	private Comparator<Object> comparator = new Comparator<Object>() {
		
		public int compare(Object o1, Object o2) {
			if (!sortLexically) {
				return 0;
			}
			if (o1 instanceof String && o2 instanceof String) {
				String s1 = (String) o1;
				String s2 = (String) o2;
				return s1.compareTo(s2);
			}
			return 0;
		}
	};
	private boolean groupTypes;
	private boolean sortLexically;
	
	public void setGroupTypes(boolean on) {
		this.groupTypes = on;
	}
	
	public void setSortLexically(boolean on) {
		this.sortLexically = on;
	}
	
	@Override
	public int category(Object element) {
		if (!groupTypes) {
			return 0;
		}
		if (element instanceof EObject) {
			EObject eObject = (EObject) element;
			EClass eClass = eObject.eClass();
			EPackage ePackage = eClass.getEPackage();
			int packageID = getEPackageID(ePackage);
			int classifierID = eClass.getClassifierID();
			return packageID + classifierID;
		} else {
			return super.category(element);
		}
	}
	
	private int getEPackageID(EPackage ePackage) {
		Integer packageID = ePackageMap.get(ePackage);
		if (packageID == null) {
			packageID = nextPackageID;
			// we assumed that packages do contain at most 1000 classifiers
			nextPackageID += 1000;
			ePackageMap.put(ePackage, nextPackageID);
		}
		return packageID;
	}
	
	public Comparator<?> getComparator() {
		return this.comparator;
	}
	
	public int compare(Viewer viewer, Object o1, Object o2) {
		// first check categories
		int cat1 = category(o1);
		int cat2 = category(o2);
		if (cat1 != cat2) {
			return cat1 - cat2;
		}
		// then try to compare the names
		if (sortLexically && o1 instanceof EObject && o2 instanceof EObject) {
			EObject e1 = (EObject) o1;
			EObject e2 = (EObject) o2;
			org.emftext.language.java.resource.java.IJavaNameProvider nameProvider = new org.emftext.language.java.resource.java.mopp.JavaMetaInformation().createNameProvider();
			List<String> names1 = nameProvider.getNames(e1);
			List<String> names2 = nameProvider.getNames(e2);
			if (names1 != null && !names1.isEmpty() && names2 != null && !names2.isEmpty()) {
				String name1 = names1.get(0);
				String name2 = names2.get(0);
				return name1.compareTo(name2);
			}
		}
		return super.compare(viewer, o1, o2);
	}
	
}
