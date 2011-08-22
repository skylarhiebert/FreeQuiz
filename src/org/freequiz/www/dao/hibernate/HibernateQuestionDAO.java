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
package org.freequiz.www.dao.hibernate;

import java.util.ArrayList;
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
	
	public List<Question> findRandomQuestionSetByExample(int numQuestions, Question exampleInstance) throws Exception {
		int maxTries = 5;
		int tries = 0;
		List<Question> questionList = new ArrayList<Question>();
		try {
			for(int i = 1 ; i <= numQuestions; i++) {
				Long low = Long.valueOf((i == 1) ? 1 : i - 1); // 1 is minimum low
				Long high = Long.valueOf((i == numQuestions) ? numQuestions : i + 1); // numQuestions is maximum high
				Criteria crit = HibernateUtil.getSession().createCriteria(getPersistentClass());
				Example example = Example.create(exampleInstance).ignoreCase();
				crit.add(example);
				if(exampleInstance.getTopic() != null)
					crit.add(Restrictions.eq("topic", exampleInstance.getTopic()));
				crit.add(Restrictions.ge("difficulty", low)).add(Restrictions.le("difficulty", high));
				crit.add(Restrictions.sqlRestriction("1=1 order by random()"));
				crit.setMaxResults(1).uniqueResult();
				
				Question nextQuestion = (Question) crit.list().get(0);
				if(questionList.contains(nextQuestion) && tries < maxTries) {
					tries++; // Re-run query if item already in set
					i--;
					continue;
				} else {
					questionList.add(nextQuestion);
				}
			}
		} catch (IndexOutOfBoundsException ex) {
			// TODO Display error, generate more questions
			System.err.println("No Questions for all difficulties");
			throw ex;
		} catch (Exception ex) {
			// TODO Display Error
			System.err.println("Unable to find enough questions");
			throw ex;
		}
		
		return questionList;
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
	
	@SuppressWarnings("unchecked")
	public List<String> findAllGradeLevels() {
		Criteria crit = HibernateUtil.getSession().createCriteria(getPersistentClass());
		
		crit.setProjection(Projections.distinct(Projections.property("gradeLevel")));
//		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		return crit.list();
	}
}
