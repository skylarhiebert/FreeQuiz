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
