/*******************************************************************************
 * Copyright (c) 2011 Skylar Hiebert.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and 
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of 
 * the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY 
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
 * SOFTWARE.
 * 
 * Contributors:
 *     Skylar Hiebert - initial API and implementation
 ******************************************************************************/
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
