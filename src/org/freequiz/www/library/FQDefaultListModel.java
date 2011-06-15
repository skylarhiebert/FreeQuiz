/**
 * 
 */
package org.freequiz.www.library;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultListModel;

/**
 * @author Skylar Hiebert
 *
 */
public class FQDefaultListModel extends DefaultListModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4246207290328097511L;

	/**
	 * 
	 */
	public FQDefaultListModel() {
		super();
	}

	/**
	 * Class Constructor initialized with an array of Objects
	 * @param array
	 */
	public FQDefaultListModel(Object[] array) {
		super();
		addAll(array);
	}

	/**
	 * Class constructor initialized with a Vector of Objects
	 * @param vector
	 */
	public FQDefaultListModel(Vector<?> vector) {
		super();
		addAll(vector);
	}
	
	/**
	 * Appends all of the elements in the specified Collection to the end of this list, in the order that they are returned by the specified Collection's Iterator.
	 * @param c
	 */
	public void addAll(Collection<?> c) {
		Iterator<?> i = c.iterator();
		while(i.hasNext())
			addElement(i.next());
		fireContentsChanged(this, 0, getSize());
	}
	
	public void addAll(Object[] elements) {
		addAll(Arrays.asList(elements));
	}
	
	public void setElements(Collection<?> c) {
		removeAllElements();
		addAll(c);
	}
	
	public void setElements(Object[] elements) {
		removeAllElements();
		addAll(elements);
	}	
}
