/**
 * 
 */
package org.freequiz.www.dao;

import org.freequiz.www.dao.hibernate.HibernateDAOFactory;

/**
 * @author Skylar Hiebert
 *
 */
public abstract class AbstractDAOFactory {
	public static final Class<?> FACTORY_CLASS = HibernateDAOFactory.class;
	
	public static AbstractDAOFactory getFactory() {
		try {
			return (AbstractDAOFactory) FACTORY_CLASS.newInstance();
		}
		catch (Exception ex) {
			throw new RuntimeException("Couldn't create DaoFactory");
		}
	}
	
	public abstract TopicDAO getTopicDAO();
	public abstract SubjectDAO getSubjectDAO();
	public abstract QuestionDAO getQuestionDAO();
	public abstract RosterDAO getRosterDAO();
	public abstract StudentDAO getStudentDAO();
}
