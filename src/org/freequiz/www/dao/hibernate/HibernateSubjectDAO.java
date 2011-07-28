/**
 * 
 */
package org.freequiz.www.dao.hibernate;

import org.freequiz.www.dao.SubjectDAO;
import org.freequiz.www.model.Subject;

/**
 * @author Skylar Hiebert
 *
 */
public class HibernateSubjectDAO extends GenericHibernateDAO<Subject, Long> implements SubjectDAO {
	public HibernateSubjectDAO() {
		super(Subject.class);
	}
}
