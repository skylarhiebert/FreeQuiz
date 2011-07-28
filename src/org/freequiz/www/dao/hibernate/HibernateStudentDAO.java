/**
 * 
 */
package org.freequiz.www.dao.hibernate;

import org.freequiz.www.dao.StudentDAO;
import org.freequiz.www.model.Student;

/**
 * @author Skylar Hiebert
 *
 */
public class HibernateStudentDAO extends GenericHibernateDAO<Student, Long> implements StudentDAO {
	public HibernateStudentDAO() {
		super(Student.class);
	}

}
