/**
 * 
 */
package org.freequiz.www.dao.hibernate;

import java.io.Serializable;
import java.util.List;
import java.lang.reflect.ParameterizedType;

import org.freequiz.www.dao.GenericDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;

/**
 * @author Skylar Hiebert
 *
 */
public abstract class GenericHibernateDAO <T, ID extends Serializable> implements GenericDAO<T, ID> {
	private Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public GenericHibernateDAO(Class<T> c) {
		//this.persistentClass =  c;
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	@SuppressWarnings("unchecked")
	public T findByPrimaryKey(ID id) {
		T load = (T) HibernateUtil.getSession().load(getPersistentClass(), id);
		return load;
	}
	
	public List<T> findAll() {
		return findByCriteria();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> findRange(int startIndex, int maxResults) {
		Criteria criteria = HibernateUtil.getSession().createCriteria(getPersistentClass());
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findRangeByExample(int startIndex, int maxResults, T exampleInstance, String[] excludeProperty) {
		Criteria criteria = HibernateUtil.getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance).ignoreCase();
		
		if(excludeProperty != null) {
			for(String prop : excludeProperty) {
				example.excludeProperty(prop);
			}
		}
		
		criteria.add(example);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(maxResults);
		
		return criteria.list();		
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
		Criteria criteria = HibernateUtil.getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance).ignoreCase();

		if(excludeProperty != null) {
			for(String prop : excludeProperty) {
				example.excludeProperty(prop);
			}
		}
		
		criteria.add(example);

		return criteria.list();
	}
	
	public int getCount() {
		Criteria criteria = HibernateUtil.getSession().createCriteria(getPersistentClass());
		Long count = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return count.intValue();
	}
	
	public int getCountByExample(T exampleInstance, String[] excludeProperty) {
		Criteria criteria = HibernateUtil.getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance).ignoreCase();

		if(excludeProperty != null) {
			for(String prop : excludeProperty) {
				example.excludeProperty(prop);
			}
		}
		
		criteria.add(example);		
		Long count = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return count.intValue();
	}
	
    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = HibernateUtil.getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }

        return crit.list();
   }
		
	public T save(T ent) {
		HibernateUtil.getSession().saveOrUpdate(ent);
		return ent;
	}
	
	public void delete(T ent) {
		HibernateUtil.getSession().delete(ent);
	}
	
	public void beginTransaction() {
		HibernateUtil.beginTransaction();
	}
	
	public void commitTransaction() {
		HibernateUtil.commitTransaction();
	}
}
