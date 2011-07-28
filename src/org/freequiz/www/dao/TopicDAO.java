/**
 * 
 */
package org.freequiz.www.dao;

import java.util.List;

import org.freequiz.www.model.Subject;
import org.freequiz.www.model.Topic;

/**
 * @author Skylar Hiebert
 *
 */
public interface TopicDAO extends GenericDAO<Topic, Long> {

	public abstract List<Topic> findBySubject(Subject subject);
	public abstract int getCount(Subject subject);
}
