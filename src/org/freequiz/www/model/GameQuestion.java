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
package org.freequiz.www.model;

import javax.persistence.*;

/**
 * @author Skylar Hiebert
 *
 */
@Embeddable
public class GameQuestion {
	private Game game;
	private Integer gridIndex;
	private Question question;
	private Integer questionValue;
	
	public GameQuestion() {}
	
	public GameQuestion(Integer gridIndex, Question question, Integer value) {
		this.gridIndex = gridIndex;
		this.question = question;
		this.questionValue = value;
	}

	/**
	 * @return the game
	 */
	@org.hibernate.annotations.Parent // Back-pointer
	public Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the gridIndex
	 */
	@Column(name="QUESTIONINDEX", nullable=false, updatable=false)
	public Integer getGridIndex() {
		return gridIndex;
	}

	/**
	 * @param gridIndex the gridIndex to set
	 */
	public void setGridIndex(Integer gridIndex) {
		this.gridIndex = gridIndex;
	}

	/**
	 * @return the question
	 */
	@ManyToOne
	@JoinColumn(name="QUESTIONID", nullable=false, updatable=false)
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @return the questionValue
	 */
	@Column(name="QUESTIONVALUE", nullable=false, updatable=false)
	public Integer getQuestionValue() {
		return questionValue;
	}

	/**
	 * @param questionValue the questionValue to set
	 */
	public void setQuestionValue(Integer questionValue) {
		this.questionValue = questionValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Transient
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result
				+ ((questionValue == null) ? 0 : questionValue.hashCode());
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
		GameQuestion other = (GameQuestion) obj;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (questionValue == null) {
			if (other.questionValue != null)
				return false;
		} else if (!questionValue.equals(other.questionValue))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameQuestion [game=" + game + ", question=" + question
				+ ", questionValue=" + questionValue + "]";
	}
	
	
}
