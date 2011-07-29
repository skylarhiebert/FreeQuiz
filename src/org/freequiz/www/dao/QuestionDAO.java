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
