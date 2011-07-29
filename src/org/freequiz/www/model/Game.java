/**
 * 
 */
package org.freequiz.www.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private List<Question> questionsAsked = new ArrayList<Question>();
	
	public Game() {
		
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
	@ManyToOne
	@JoinColumn(name="ROSTERID", nullable=false)
	public Roster getGameRoster() {
		return gameRoster;
	}
	/**
	 * @param gameRoster the gameRoster to set
	 */
	public void setGameRoster(Roster gameRoster) {
		this.gameRoster = gameRoster;
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
	@ManyToMany
	@JoinTable(name="GAME_QUESTIONS",
			joinColumns={@JoinColumn(name="GAMEID")},
			inverseJoinColumns={@JoinColumn(name="QUESTIONID")}
	)
	public List<Question> getQuestionsAsked() {
		return questionsAsked;
	}
	/**
	 * @param questionsAsked the questionsAsked to set
	 */
	public void setQuestionsAsked(List<Question> questionsAsked) {
		this.questionsAsked = questionsAsked;
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
