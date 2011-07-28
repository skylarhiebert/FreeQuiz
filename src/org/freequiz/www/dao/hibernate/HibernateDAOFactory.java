/**
 * 
 */
package org.freequiz.www.dao.hibernate;

import org.freequiz.www.dao.AbstractDAOFactory;
import org.freequiz.www.dao.QuestionDAO;
import org.freequiz.www.dao.RosterDAO;
import org.freequiz.www.dao.StudentDAO;
import org.freequiz.www.dao.SubjectDAO;
import org.freequiz.www.dao.TopicDAO;

/**
 * @author Skylar Hiebert
 *
 */
public class HibernateDAOFactory extends AbstractDAOFactory {
	public TopicDAO getTopicDAO() {
		return new HibernateTopicDAO();
	}
	
	public SubjectDAO getSubjectDAO() {
		return new HibernateSubjectDAO();
	}
	
	public QuestionDAO getQuestionDAO() {
		return new HibernateQuestionDAO();
	}
	
	public StudentDAO getStudentDAO() {
		return new HibernateStudentDAO();
	}
	
	public RosterDAO getRosterDAO() {
		return new HibernateRosterDAO();
	}
}
