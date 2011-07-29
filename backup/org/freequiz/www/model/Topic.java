/**
 * 
 */
package org.freequiz.www.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Skylar Hiebert
 *
 */
@Entity
@Table(name = "topics")
public class Topic {
	private int topicid;
	private String topicText;
	private Subject subject;
	private int gradeLevel;
	
	protected Topic() {
	}

	public Topic(int topicid, String topicText, int gradeLevel) {
		super();
		setTopicid(topicid);
		setTopicText(topicText);
		setGradeLevel(gradeLevel);
	}

	/**
	 * Class constructor specifying topic id, topic text and grade level
	 * @param topicid the topicid to set
	 * @param topicText the topicText to set
	 * @param subjext the subjext to set
	 * @param gradeLevel the gradeLevel to set
	 */
	public Topic(int topicid, String topicText, Subject subject, int gradeLevel) {
		super();
		setTopicid(topicid);
		setTopicText(topicText);
		setSubject(subject);
		setGradeLevel(gradeLevel);
	}
	
	/**
	 * @return the topicid
	 */
	@Id
	@GeneratedValue
	public int getTopicid() {
		return topicid;
	}

	/**
	 * @param topicid the topicid to set, must be greater than 0
	 * @return <code>true</code> if the topicid was set
	 */
	public boolean setTopicid(int topicid) {
		if(topicid > 0) {
			this.topicid = topicid;
			return true;
		}
		else {
			System.err.println("Invalid topicid " + topicid + ", topicid must be greater than 0.");	
			return false;
		}
	}

	/**
	 * @return the topicText
	 */
	public String getTopicText() {
		return topicText;
	}

	/**
	 * @param topicText the topicText to set, must not be null
	 * @return <code>true</code> if the topic was set
	 */
	public boolean setTopicText(String topicText) {
		if(!topicText.isEmpty()) {
			this.topicText = topicText;
			return true;
		}
		else {
			System.err.println("topicText cannot be null.");
			return false;
		}
	}

	/**
	 * @return the subject
	 */
	@ManyToOne
	public Subject getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 * @return <code>true</code> if subject was set
	 */
	public boolean setSubject(Subject subject) {
		if(subject != null && subject.isValid()) {
			this.subject = subject;
			return true;
		}
		return false;
	}
	
	/**
	 * @return the gradeLevel
	 */
	public int getGradeLevel() {
		return gradeLevel;
	}

	/**
	 * @param gradeLevel the gradeLevel to set, must be greater than 0
	 * @return <code>true</code> if the gradeLevel was set
	 */
	public boolean setGradeLevel(int gradeLevel) {
		if(gradeLevel > 0) {
			this.gradeLevel = gradeLevel;
			//firePropertyChange(DefaultController.)
			return true;
		}
		else {
			System.err.println("Invalid gradeLevel " + gradeLevel + ", gradeLevel must be greater than 0.");	
			return false;
		}
	}
	
	/**
	 * 
	 * @return <code>true</code> if topicid and gradeLevel > 0 and topicText is not null
	 */
	public boolean isValid() {
		if(topicid > 0 && gradeLevel > 0 && !topicText.isEmpty())
			return true;
		return false;
	}
	
	public String toString() {
		return topicText;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
