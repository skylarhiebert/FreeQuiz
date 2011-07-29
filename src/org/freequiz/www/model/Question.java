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
package org.freequiz.www.model;

import javax.persistence.*;

/**
 * @author Skylar Hiebert
 * 
 */
@Entity
@Table(name = "QUESTIONS")
public class Question {
	
	private Long questionid;
	private Topic topic;
	private String questionText;
	private String answerText;
	private String gradeLevel;
	private Long difficulty;

	public Question() {
	}

	/**
	 * Class constructor specifying questionid, topicid, questionText, answerText and difficulty
	 * @param qid the questionid
	 * @param top the topic
	 * @param qt the questionText
	 * @param at the answerText
	 * @param diff the difficulty
	 */
	public Question(Long qid, Topic top, String qt, String at, String gl, Long diff) {
		super();
		setQuestionid(qid);
		setTopic(top);
		setQuestionText(qt);
		setAnswerText(at);
		setGradeLevel(gl);
		setDifficulty(diff);
	}
	
	/**
	 * 
	 * @return the questionid
	 */
	@Id
	@GeneratedValue
	@Column(name="QUESTIONID")
	public Long getQuestionid() {
		return questionid;
	}

	/**
	 * 
	 * @param questionid the questionid to set, must be greater than 0
	 * @return <code>true</code> if the questionid was set
	 */
	public void setQuestionid(Long questionid) {
			this.questionid = questionid;
	}

	/**
	 * 
	 * @return the topic
	 */
	@ManyToOne
	@JoinColumn(name="TOPICID")
	public Topic getTopic() {
		return topic;
	}
	
	/**
	 * 
	 * @param newTopic the Topic to set, must be a valid topic
	 * @return <code>true</code> if the topic was set
	 * @see Topic.isValid()
	 */
	public void setTopic(Topic topic) {
			this.topic = topic;
	}
	
	/**
	 * 
	 * @return String for the current questionText
	 */
	@Column(name="QUESTIONTEXT", nullable=false)
	public String getQuestionText() {
		return questionText;
	}

	/**
	 * 
	 * @param questionText the questionText to set, must not be null
	 * @return <code>true</code> if the questionText was set
	 */
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	/**
	 * 
	 * @return String for the current answerText
	 */
	@Column(name="ANSWERTEXT", nullable=false)
	public String getAnswerText() {
		return answerText;
	}

	/**
	 * 
	 * @param answerText the answerText to set, must not be null
	 * @return <code>true</code> if the answerText was set
	 */
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	
	/**
	 * @return the difficulty
	 */
	@Column(name="DIFFICULTY", nullable=false)
	public Long getDifficulty() {
		return difficulty;
	}

	/**
	 * @param difficulty the difficulty to set, must be greater than 0
	 * @return <code>true</code> if the difficulty was set
	 */
	public void setDifficulty(Long difficulty) {
		this.difficulty = difficulty;
	}
	
	/**
	 * @return the gradeLevel
	 */
	@Column(name="GRADELEVEL", nullable=false)
	public String getGradeLevel() {
		return gradeLevel;
	}

	/**
	 * @param gradeLevel the gradeLevel to set, must be greater than 0
	 * @return <code>true</code> if the gradeLevel was set
	 */
	public void setGradeLevel(String gradeLevel) {
			this.gradeLevel = gradeLevel;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Transient
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((answerText == null) ? 0 : answerText.hashCode());
		result = prime * result
				+ ((difficulty == null) ? 0 : difficulty.hashCode());
		result = prime * result
				+ ((gradeLevel == null) ? 0 : gradeLevel.hashCode());
		result = prime * result
				+ ((questionText == null) ? 0 : questionText.hashCode());
		result = prime * result
				+ ((questionid == null) ? 0 : questionid.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Transient
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (answerText == null) {
			if (other.answerText != null)
				return false;
		} else if (!answerText.equals(other.answerText))
			return false;
		if (difficulty == null) {
			if (other.difficulty != null)
				return false;
		} else if (!difficulty.equals(other.difficulty))
			return false;
		if (gradeLevel == null) {
			if (other.gradeLevel != null)
				return false;
		} else if (!gradeLevel.equals(other.gradeLevel))
			return false;
		if (questionText == null) {
			if (other.questionText != null)
				return false;
		} else if (!questionText.equals(other.questionText))
			return false;
		if (questionid == null) {
			if (other.questionid != null)
				return false;
		} else if (!questionid.equals(other.questionid))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		return true;
	}

	@Transient
	public String toString() {
		return getQuestionText();
	}

}
