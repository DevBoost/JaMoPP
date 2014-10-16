/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * A class for RGB-based color objects.
 */
public class JavaColorManager {
	
	protected Map<RGB, Color> fColorTable = new LinkedHashMap<RGB, Color>(10);
	
	/**
	 * Disposes all colors in the cache.
	 */
	public void dispose() {
		Iterator<Color> e = fColorTable.values().iterator();
		while (e.hasNext()) {
			e.next().dispose();
		}
	}
	
	/**
	 * <p>
	 * Constructs and caches the given color.
	 * </p>
	 * 
	 * @param rgb The color as RGB
	 * 
	 * @return The color (from cache or newly constructed)
	 */
	public Color getColor(RGB rgb) {
		Color color = fColorTable.get(rgb);
		if (color == null) {
			color = new Color(Display.getCurrent(), rgb);
			fColorTable.put(rgb, color);
		}
		return color;
	}
}
