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
