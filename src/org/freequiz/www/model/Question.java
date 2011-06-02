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
	int questionid;
	int topicid;
	String questionText;
	String answerText;
	int difficulty;

	/**
	 * Class constructor specifying questionid, topicid, questionText, answerText and difficulty
	 * @param qid the questionid
	 * @param tid the topicid
	 * @param qt the questionText
	 * @param at the answerText
	 * @param diff the difficulty
	 */
	public Question(int qid, int tid, String qt, String at, int diff) {
		super();
		setQuestionid(qid);
		setTopicid(tid);
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
			System.err.println("Invalid question " + questionid + " ID, must be greater than 0.");			
			return false;
		}
	}

	/**
	 * 
	 * @return the topicid
	 */
	public int getTopicid() {
		return topicid;
	}
	
	/**
	 * 
	 * @param topicid the topicid to set, must be greater than 0
	 * @return <code>true</code> if the topic was set
	 */
	public boolean setTopicid(int topicid) {
		if(topicid > 0) {
			this.topicid = topicid;
			return true;
		}
		else {
			System.err.println("Invalid topic " + topicid + " ID, must be greater than 0.");	
			return false;
		}
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
	 */
	public void setQuestionText(String questionText) {
		if(!questionText.isEmpty())
			this.questionText = questionText;
		else
			System.err.println("Question text cannot be null.");
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
	 */
	public void setAnswerText(String answerText) {
		if(!answerText.isEmpty())
			this.answerText = answerText;
		else
			System.err.println("Answer text cannot be null.");
	}
	
	/**
	 * @param questionText the questionText to set, must not be null
	 * @param answerText the answerText to set, must not be null
	 */
	public void setQuestionAndAnswerText(String questionText, String answerText) {
		setQuestionText(questionText);
		setAnswerText(answerText);
	}
	
	/**
	 * @param questionText the questionText to set, must not be null
	 * @param answerText the answerText to set, must not be null
	 * @param difficulty the difficulty to set, must be greater than 0
	 */
	public void setQuestionAndAnswerText(String questionText, String answerText, int difficulty) {
		setQuestionText(questionText);
		setAnswerText(answerText);
		setDifficulty(difficulty);
	}

	/**
	 * @return the difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * @param difficulty the difficulty to set, must be greater than 0
	 */
	public void setDifficulty(int difficulty) {
		if(difficulty > 0)
			this.difficulty = difficulty;
		else
			System.err.println("Difficulty " + difficulty + " is invalid, difficult my be greater than 0.");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
