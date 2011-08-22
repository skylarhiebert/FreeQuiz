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
	public abstract GameDAO getGameDAO();
}
