/*************************************************************************************************
 * Copyright (c) 2011 Skylar Hiebert.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this 
 * software and associated documentation files (the "Software"), to deal in the Software 
 * without restriction, including without limitation the rights to use, copy, modify, 
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to 
 * permit persons to whom the Software is furnished to do so, subject to the following 
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all 
 * copies or substantial portions of the Software.
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
 *************************************************************************************************/

package org.freequiz.www.model;

/**
 * @author Skylar Hiebert
 * 
 */
public class Question {
	private int questionid;
	private Topic topic;
	private String questionText;
	private String answerText;
	private int difficulty;

	/**
	 * Class constructor specifying questionid, topicid, questionText, answerText and difficulty
	 * @param qid the questionid
	 * @param top the topic
	 * @param qt the questionText
	 * @param at the answerText
	 * @param diff the difficulty
	 */
	public Question(int qid, Topic top, String qt, String at, int diff) {
		super();
		setQuestionid(qid);
		setTopic(top);
		setQuestionText(qt);
		setAnswerText(at);
		setDifficulty(diff);
	}
	
	/**
	 * 
	 * @return the questionid
	 */
	public int getQuestionid() {
		return questionid;
	}

	/**
	 * 
	 * @param questionid the questionid to set, must be greater than 0
	 * @return <code>true</code> if the questionid was set
	 */
	public boolean setQuestionid(int questionid) {
		if(questionid > 0) {
			this.questionid = questionid;
			return true;
		} 
		else {
			System.err.println("Invalid questionid " + questionid + ", questionid must be greater than 0.");			
			return false;
		}
	}

	/**
	 * 
	 * @return the topic
	 */
	public Topic getTopic() {
		return topic;
	}
	
	/**
	 * 
	 * @param newTopic the Topic to set, must be a valid topic
	 * @return <code>true</code> if the topic was set
	 * @see Topic.isValid()
	 */
	public boolean setTopic(Topic newTopic) {
		if(newTopic.isValid()) {
			this.topic = newTopic;
			return true;
		}
		else {
			System.err.println("Invalid newTopic is not a valid Topic");	
			return false;
		}
	}
	
	/**
	 * @return the subject
	 */
	public Subject getSubject() {
		return topic.getSubject();
	}

	/**
	 * @return the gradeLevel
	 */
	public int getGradeLevel() {
		return topic.getGradeLevel();
	}
	
	/**
	 * 
	 * @return String for the current questionText
	 */
	public String getQuestionText() {
		return questionText;
	}

	/**
	 * 
	 * @param questionText the questionText to set, must not be null
	 * @return <code>true</code> if the questionText was set
	 */
	public boolean setQuestionText(String questionText) {
		if(!questionText.isEmpty()) {
			this.questionText = questionText;
			return true;
		}
		else {
			System.err.println("Question text cannot be null.");
			return false;
		}
	}

	/**
	 * 
	 * @return String for the current answerText
	 */
	public String getAnswerText() {
		return answerText;
	}

	/**
	 * 
	 * @param answerText the answerText to set, must not be null
	 * @return <code>true</code> if the answerText was set
	 */
	public boolean setAnswerText(String answerText) {
		if(!answerText.isEmpty()) {
			this.answerText = answerText;
			return true;
		}
		else {
			System.err.println("Answer text cannot be null.");
			return false;
		}
	}
	
	/**
	 * @param questionText the questionText to set, must not be null
	 * @param answerText the answerText to set, must not be null
	 * @return <code>true</code> if the questionText and answerText was set
	 */
	public boolean setQuestionAndAnswerText(String questionText, String answerText) {
		if(setQuestionText(questionText) == false)
			return false;
		if(setAnswerText(answerText) == false)
			return false;
		return true;
	}
	
	/**
	 * @param questionText the questionText to set, must not be null
	 * @param answerText the answerText to set, must not be null
	 * @param difficulty the difficulty to set, must be greater than 0
	 * @return <code>true</code> if the questionText, answerText and difficulty was set
	 */
	public boolean setQuestionAnswerDifficulty(String questionText, String answerText, int difficulty) {
		if(setQuestionText(questionText) == false)
			return false;
		if(setAnswerText(answerText) == false)
			return false;
		if(setDifficulty(difficulty) == false)
			return false;
		return true;
	}

	/**
	 * @return the difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * @param difficulty the difficulty to set, must be greater than 0
	 * @return <code>true</code> if the difficulty was set
	 */
	public boolean setDifficulty(int difficulty) {
		if(difficulty > 0) {
			this.difficulty = difficulty;
			return true;
		}
		else {
			System.err.println("Difficulty " + difficulty + " is invalid, difficulty must be greater than 0.");
			return false;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
