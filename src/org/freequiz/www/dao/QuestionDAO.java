/**
 * 
 */
package org.freequiz.www.dao;

import java.util.List;

import org.freequiz.www.model.Subject;
import org.freequiz.www.model.Topic;
import org.freequiz.www.model.Question;

/**
 * @author Skylar Hiebert
 *
 */
public interface QuestionDAO extends GenericDAO<Question, Long> {
	
	public abstract List<Question> findBySubject(Subject subject);
	public abstract List<Question> findRangeBySubject(int startIndex, int maxResults, Subject subject);
	public abstract List<Question> findByTopic(Topic topic);
	public abstract List<Question> findRangeByTopic(int startIndex, int maxResults, Topic topic);
	public abstract List<Question> findByExampleWithSubject(Question exampleInstance, Subject subject, String[] excludeProperty);
	public abstract List<Question> findRangeByExampleWithSubject(int startIndex, int maxResults, Question exampleInstance, Subject subject, String[] excludeProperty);
	public abstract int getCount(Topic topic);
	public abstract int getCount(Subject subject);
	public abstract int getCountByExampleWithSubject(Question exampleInstance, Subject subject, String[] excludeProperty);
}
