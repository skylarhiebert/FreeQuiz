/**
 * 
 */
package org.freequiz.www.dao.hibernate;

import java.util.List;

import org.freequiz.www.dao.QuestionDAO;
import org.freequiz.www.model.Question;
import org.freequiz.www.model.Subject;
import org.freequiz.www.model.Topic;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * @author Skylar Hiebert
 *
 */
public class HibernateQuestionDAO extends GenericHibernateDAO<Question, Long> implements QuestionDAO {
	public HibernateQuestionDAO() {
		super(Question.class);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Question> findByExample(Question exampleInstance, String[] excludeProperty) {
		Criteria crit = HibernateUtil.getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance).ignoreCase();

		if(excludeProperty != null) 
			for(String prop : excludeProperty) 
				example.excludeProperty(prop);
	
		crit.add(example);
		if(exampleInstance.getTopic() != null)
			crit.add(Restrictions.eq("topic", exampleInstance.getTopic()));

		return crit.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Question> findRangeByExample(int startIndex, int maxResults, Question exampleInstance, String[] excludeProperty) {
		Criteria crit = HibernateUtil.getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance).ignoreCase();
		
		if(excludeProperty != null) 
			for(String prop : excludeProperty) 
				example.excludeProperty(prop);

		crit.add(example);
		if(exampleInstance.getTopic() != null)
			crit.add(Restrictions.eq("topic", exampleInstance.getTopic()));
		crit.addOrder(Order.asc("topic"));
		crit.setFirstResult(startIndex);
		crit.setMaxResults(maxResults);
		
		return crit.list();		
	}
	
	@Override
	public int getCountByExample(Question exampleInstance, String[] excludeProperty) {
		Criteria crit = HibernateUtil.getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance).ignoreCase();
		
		if(excludeProperty != null) 
			for(String prop : excludeProperty)
				example.excludeProperty(prop);
		
		crit.add(example);
		if(exampleInstance.getTopic() != null)
			crit.add(Restrictions.eq("topic", exampleInstance.getTopic()));
		
		Long count = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();
		
		return count.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<Question> findByExampleWithSubject(Question exampleInstance, Subject subject, String[] excludeProperty) {
		Criteria crit = HibernateUtil.getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance).ignoreCase();
	
		if(excludeProperty != null) 
			for(String prop : excludeProperty) 
				example.excludeProperty(prop);

		crit.add(example);
		if(exampleInstance.getTopic() != null)
			crit.add(Restrictions.eq("topic", exampleInstance.getTopic()));
		crit.createAlias("topic", "topic")
			.add(Restrictions.eq("topic.subject", subject))
			.addOrder(Order.asc("topic"));
		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Question> findRangeByExampleWithSubject(int startIndex, int maxResults, Question exampleInstance, Subject subject, String[] excludeProperty) {
		Criteria crit = HibernateUtil.getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance).ignoreCase();
	
		if(excludeProperty != null) 
			for(String prop : excludeProperty) 
				example.excludeProperty(prop);

		crit.add(example);
		if(exampleInstance.getTopic() != null)
			crit.add(Restrictions.eq("topic", exampleInstance.getTopic()));
		crit.createAlias("topic", "topic")
			.add(Restrictions.eq("topic.subject", subject))
			.addOrder(Order.asc("topic"))
			.setFirstResult(startIndex)
			.setMaxResults(maxResults);
		
		return crit.list();
	}
		
	public int getCount(Topic topic) {
		Criteria crit = HibernateUtil.getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("topic", topic));
		Long count = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();
		
		return count.intValue();
	}
	
	public int getCount(Subject subject) {
		Criteria crit = HibernateUtil.getSession().createCriteria(getPersistentClass());
		crit.createAlias("topic", "topic")
			.add(Restrictions.eq("topic.subject", subject));
		Long count = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();
		
		return count.intValue();
	}
	
	public int getCountByExampleWithSubject(Question exampleInstance, Subject subject, String[] excludeProperty) {
		Criteria crit = HibernateUtil.getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance).ignoreCase();
		
		if(excludeProperty != null) 
			for(String prop : excludeProperty)
				example.excludeProperty(prop);
		
		crit.add(example);
		if(exampleInstance.getTopic() != null)
			crit.add(Restrictions.eq("topic", exampleInstance.getTopic()));
		crit.createAlias("topic", "topic")
			.add(Restrictions.eq("topic.subject", subject));
		Long count = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();
		
		return count.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<Question> findBySubject(Subject subject) {
		Criteria crit = HibernateUtil.getSession().createCriteria(getPersistentClass());
		crit.createAlias("topic", "topic")
		.add(Restrictions.eq("topic.subject", subject));
		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Question> findRangeBySubject(int startIndex, int maxResults, Subject subject) {
		Criteria crit = HibernateUtil.getSession().createCriteria(getPersistentClass());

		crit.createAlias("topic", "topic")
			.add(Restrictions.eq("topic.subject", subject))
			.addOrder(Order.asc("topic"))
			.setFirstResult(startIndex)
			.setMaxResults(maxResults);
		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Question> findByTopic(Topic topic) {
		Criteria crit = HibernateUtil.getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("topic", topic));
		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Question> findRangeByTopic(int startIndex, int maxResults, Topic topic) {
		Criteria crit = HibernateUtil.getSession().createCriteria(getPersistentClass());

		crit.add(Restrictions.eq("topic", topic))
			.setFirstResult(startIndex)
			.setMaxResults(maxResults);
		
		return crit.list();
	}
}