/**
 * 
 */
package org.freequiz.www.library;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

/**
 * @author Skylar Hiebert
 *
 */
public class FQDefaultComboBoxModel extends DefaultComboBoxModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9184589669489964292L;

	/**
	 * Class Constructor
	 */
	public FQDefaultComboBoxModel() {
		super();
	}

	/**
	 * Class Constructor initialized with an array of Objects
	 * @param array
	 */
	public FQDefaultComboBoxModel(Object[] array) {
		super(array);
	}

	/**
	 * Class constructor initialized with a Vector of Objects
	 * @param vector
	 */
	public FQDefaultComboBoxModel(Vector<?> vector) {
		super(vector);
	}
	
	/**
	 * Appends all of the elements in the specified Collection to the end of this list, in the order that they are returned by the specified Collection's Iterator.
	 * @param c
	 */
	public void addAll(Collection<?> c) {
		if(c == null || c.isEmpty()) {
			System.err.println("Empty Collection\n");
			return;
		}
		Iterator<?> i = c.iterator();
		System.out.println("After Iterator\n");
		while(i.hasNext())
			addElement(i.next());
		fireContentsChanged(this, 0, getSize());
	}
	
	public void addAll(Object[] elements) {
		addAll(Arrays.asList(elements));
	}
	
	public void setElements(Collection<?> c) {
		if(c == null || c.isEmpty()) {
			System.err.println("Empty Collection\n");
			return;
		}
		if(this.getSize() > 0)
			removeAllElements();
		addAll(c);
	}
	
	public void setElements(Object[] elements) {
		if(this.getSize() > 0)
			removeAllElements();
		addAll(elements);
	}
}
