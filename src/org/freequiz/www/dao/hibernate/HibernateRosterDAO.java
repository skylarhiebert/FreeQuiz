/**
 * 
 */
package org.freequiz.www.dao.hibernate;

import org.freequiz.www.dao.RosterDAO;
import org.freequiz.www.model.Roster;

/**
 * @author Skylar Hiebert
 *
 */
public class HibernateRosterDAO extends GenericHibernateDAO<Roster, Long> implements RosterDAO {
	public HibernateRosterDAO() {
		super(Roster.class);
	}
}
