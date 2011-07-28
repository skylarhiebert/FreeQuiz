/**
 * 
 */
package org.freequiz.www.dao.hibernate;

import java.util.List;

import org.freequiz.www.dao.TopicDAO;
import org.freequiz.www.model.Subject;
import org.freequiz.www.model.Topic;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * @author Skylar Hiebert
 *
 */
public class HibernateTopicDAO extends GenericHibernateDAO<Topic, Long> implements TopicDAO {
	public HibernateTopicDAO() {
		super(Topic.class);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Topic> findByExample(Topic exampleInstance, String[] excludeProperty) {
		Criteria criteria = HibernateUtil.getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance).ignoreCase();

		if(excludeProperty != null) {
			for(String prop : excludeProperty) {
				example.excludeProperty(prop);
			}
		}
		
		criteria.add(example);
		if(exampleInstance.getSubject() != null)
			criteria.add(Restrictions.eq("subject", exampleInstance.getSubject()));

		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Topic> findRangeByExample(int startIndex, int maxResults, Topic exampleInstance, String[] excludeProperty) {
		Criteria criteria = HibernateUtil.getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance).ignoreCase();
		
		if(excludeProperty != null) {
			for(String prop : excludeProperty) {
				example.excludeProperty(prop);
			}
		}
		
		criteria.add(example);
		if(exampleInstance.getSubject() != null)
			criteria.add(Restrictions.eq("subject", exampleInstance.getSubject()));
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(maxResults);
		
		return criteria.list();		
	}
	
	@Override
	public int getCountByExample(Topic exampleInstance, String[] excludeProperty) {
		Long count;
		Criteria criteria = HibernateUtil.getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance).ignoreCase();

		if(excludeProperty != null) {
			for(String prop : excludeProperty) {
				example.excludeProperty(prop);
			}
		}
		
		criteria.add(example);	
		if(exampleInstance.getSubject() != null)
			criteria.add(Restrictions.eq("subject", exampleInstance.getSubject()));

		count = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return count.intValue();
	}
	
	public int getCount(Subject subject) {
		Criteria crit = HibernateUtil.getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("subject", subject));
		Long count = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();
		return count.intValue();
	}
	
	public List<Topic> findBySubject(Subject subject) {
		Topic example = new Topic();
		example.setSubject(subject);
		if(subject.getSubjectid() == null)
			return null;
		else
			return findByExample(example, null);
	}
}