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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * @author Skylar Hiebert
 *
 */
@Entity
@Table(name="GAMES")
public class Game {
	private Long gameid;
	private Roster gameRoster;
	private Date datePlayed = new Date();
	private List<GameQuestion> questionsAsked = new ArrayList<GameQuestion>();
//	private List<StudentGame> gamesPlayed = new ArrayList<StudentGame>();
	// Transient properties
	private List<Topic> topicList = new ArrayList<Topic>();
	private Set<StudentGame> studentSet = new HashSet<StudentGame>();
	private List<Question> questionList = new ArrayList<Question>();
	
	public Game() {}
	
	public Game(Long id, Roster roster, Date date, List<GameQuestion> questions) {
		setGameid(id);
		setGameRoster(roster);
		setDatePlayed(date);
		setQuestionsAsked(questions);
	}
	
	/**
	 * @return the gameid
	 */
	@Id
	@GeneratedValue
	@Column(name="GAMEID")
	public Long getGameid() {
		return gameid;
	}
	/**
	 * @param gameid the gameid to set
	 */
	private void setGameid(Long gameid) {
		this.gameid = gameid;
	}
	
	/**
	 * @return the gameRoster
	 */
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="ROSTERID", nullable=false)
	public Roster getGameRoster() {
		return gameRoster;
	}
	
	/**
	 * @param gameRoster the gameRoster to set
	 */
	public void setGameRoster(Roster gameRoster) {
		this.gameRoster = gameRoster;
		studentSet.clear();
		System.err.println(gameRoster);
		for(Student s : gameRoster.getStudentList()) {
			StudentGame sg = s.getStudentGame(this);
			if(sg == null) {
				sg = new StudentGame(this, s);
				s.addGamePlayed(sg);
			}
			studentSet.add(sg);
		}
	}
	/**
	 * @return the datePlayed
	 */
	@Temporal(TemporalType.DATE)
	public Date getDatePlayed() {
		return datePlayed;
	}
	/**
	 * @param datePlayed the datePlayed to set
	 */
	public void setDatePlayed(Date datePlayed) {
		this.datePlayed = datePlayed;
	}
	/**
	 * @return the questionsAsked
	 */
	@ElementCollection
	@JoinTable(name="GAMEQUESTIONS",
			joinColumns = @JoinColumn(name="GAMEID"))
	public List<GameQuestion> getQuestionsAsked() {
		return questionsAsked;
	}
	/**
	 * @param questionsAsked the questionsAsked to set
	 */
	public void setQuestionsAsked(List<GameQuestion> questionsAsked) {
		this.questionsAsked = questionsAsked;
		for(GameQuestion gq : questionsAsked) {
			int gridIndex = gq.getGridIndex();
			if(!topicList.contains(gq.getQuestion().getTopic())) {
				topicList.add(gq.getQuestion().getTopic());
			}
			if(gridIndex > questionList.size())	questionList.add(gq.getQuestion());
			else questionList.add(gridIndex, gq.getQuestion());
		}
	}
	
	@Transient
	public List<Question> getQuestionList() {
		return questionList;
	}
	
	@Transient 
	public List<Topic> getTopicList() {
		return topicList;
//		return new ArrayList<Topic>(topicSet);
	}
	
	@Transient
	public List<StudentGame> getStudentGameList() {
		return new ArrayList<StudentGame>(studentSet);
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
				+ ((datePlayed == null) ? 0 : datePlayed.hashCode());
		result = prime * result
				+ ((gameRoster == null) ? 0 : gameRoster.hashCode());
		result = prime * result + ((gameid == null) ? 0 : gameid.hashCode());
		result = prime * result
				+ ((questionsAsked == null) ? 0 : questionsAsked.hashCode());
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
		Game other = (Game) obj;
		if (datePlayed == null) {
			if (other.datePlayed != null)
				return false;
		} else if (!datePlayed.equals(other.datePlayed))
			return false;
		if (gameRoster == null) {
			if (other.gameRoster != null)
				return false;
		} else if (!gameRoster.equals(other.gameRoster))
			return false;
		if (gameid == null) {
			if (other.gameid != null)
				return false;
		} else if (!gameid.equals(other.gameid))
			return false;
		if (questionsAsked == null) {
			if (other.questionsAsked != null)
				return false;
		} else if (!questionsAsked.equals(other.questionsAsked))
			return false;
		return true;
	}	
}
