/**
 * 
 */
package org.freequiz.www.model;

/**
 * @author Skylar Hiebert
 *
 */
public class Topic {
	int topicid;
	String topicText;
	int gradeLevel;

	/**
	 * Class constructor specifying topic id, topic text and grade level
	 * @param topicid the topicid to set
	 * @param topicText the topicText to set
	 * @param gradeLevel the gradeLevel to set
	 */
	public Topic(int topicid, String topicText, int gradeLevel) {
		super();
		this.topicid = topicid;
		this.topicText = topicText;
		this.gradeLevel = gradeLevel;
	}
	
	/**
	 * @return the topicid
	 */
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
			return true;
		}
		else {
			System.err.println("Invalid gradeLevel " + gradeLevel + ", gradeLevel must be greater than 0.");	
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
