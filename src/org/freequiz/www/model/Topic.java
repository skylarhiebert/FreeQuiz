package org.freequiz.www.model;
/**
 * 
 */


import javax.persistence.*;

//import javax.persistence.CascadeType;

/**
 * @author Skylar Hiebert
 *
 */
@Entity
@Table(name = "TOPICS")
public class Topic {
	private Long topicid;
	private String topicText;
	private Subject subject;
	
	public Topic() {
	}

	public Topic(String topicText) {
		setTopicText(topicText);
	}
	
	/**
	 * Class constructor specifying topic id, topic text and grade level
	 * @param topicid the topicid to set
	 * @param topicText the topicText to set
	 */
	public Topic(Long topicid, String topicText) {
		setTopicid(topicid);
		setTopicText(topicText);
	}
	
	/**
	 * @return the topicid
	 */
	@Id
	@GeneratedValue
	@Column(name="TOPICID")
	public Long getTopicid() {
		return topicid;
	}

	/**
	 * @param topicid the topicid to set, must be greater than 0
	 * @return <code>true</code> if the topicid was set
	 */
	private void setTopicid(Long topicid) {
			this.topicid = topicid;
	}

	/**
	 * @return the topicText
	 */
	@Column(name="TOPIC", length = 255, nullable = false)
	public String getTopicText() {
		return topicText;
	}

	/**
	 * @param topicText the topicText to set, must not be null
	 * @return <code>true</code> if the topic was set
	 */
	public void setTopicText(String topicText) {
			this.topicText = topicText;
	}
	
	/**
	 * @return the subject
	 */
	@ManyToOne
	@JoinColumn(name="SUBJECTID", nullable=false)
	public Subject getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 * @return <code>true</code> if subject was set
	 */
	public void setSubject(Subject subject) {
			this.subject = subject;
	}
	
	/**
	 * 
	 * @return <code>true</code> if topicid and gradeLevel > 0 and topicText is not null
	 */
	@Transient
	public boolean isValid() {
		if(topicid > 0 && !topicText.isEmpty())
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Transient
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result
				+ ((topicText == null) ? 0 : topicText.hashCode());
		result = prime * result + ((topicid == null) ? 0 : topicid.hashCode());
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
		Topic other = (Topic) obj;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (topicText == null) {
			if (other.topicText != null)
				return false;
		} else if (!topicText.equals(other.topicText))
			return false;
		if (topicid == null) {
			if (other.topicid != null)
				return false;
		} else if (!topicid.equals(other.topicid))
			return false;
		return true;
	}

	@Transient
	public String toString() {
		return topicText;
	}
}
