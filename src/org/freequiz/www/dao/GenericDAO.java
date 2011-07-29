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
package org.freequiz.www.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author Skylar Hiebert
 *
 */
public interface GenericDAO<T, ID extends Serializable> {
	public abstract T findByPrimaryKey(ID id);
	public abstract List<T> findAll();
	public abstract List<T> findRange(int startIndex, int fetchSize);
	public abstract List<T> findByExample(T exampleInstance, String[] excludeProperty);
	public abstract List<T> findRangeByExample(int startIndex, int fetchSize, T exampleInstance, String[] excludeProperty);
	public abstract int getCount();
	public abstract int getCountByExample(T exampleInstance, String[] excludeProperty);
	
	public abstract T save(T ent);
	public abstract void delete(T ent);
	
	public abstract void beginTransaction();
	public abstract void commitTransaction();
}
