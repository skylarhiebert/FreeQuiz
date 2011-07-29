/**
 * 
 */
package org.freequiz.www.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Skylar Hiebert
 *
 */
@Entity
public class Subject {
	@Id
	@GeneratedValue
	private int subjectid;
	private String subjectText;
	
	public Subject() {
		
	}
	
	/**
	 * Class Constructor
	 * @param subjectid the subjectid to set
	 * @param subjectText the subjectText to set
	 */
	public Subject(int subjectid, String subjectText) {
		setSubjectid(subjectid);
		setSubjectText(subjectText);
	}

	/**
	 * @return the subjectid
	 */
	public int getSubjectid() {
		return subjectid;
	}

	/**
	 * @param subjectid the subjectid to set
	 * @return <code>true</code> if the subjectid was set
	 */
	public boolean setSubjectid(int subjectid) {
		if(subjectid > 0) {
			this.subjectid = subjectid;
			return true;
		} 
		else {
			System.err.println("Invalid subjectid " + subjectid + ", subjectid must be greater than 0.");			
			return false;
		}
	}

	/**
	 * @return the subjectText
	 */
	public String getSubjectText() {
		return subjectText;
	}

	/**
	 * @param subjectText the subjectText to set
	 * @return <code>true</code> if the subjectText was set
	 */
	public boolean setSubjectText(String subjectText) {
		if(!subjectText.isEmpty()) {
			this.subjectText = subjectText;
			return true;
		}
		else {
			System.err.println("subjectText cannot be null.");
			return false;
		}
	}

	/**
	 * 
	 * @return <code>true</code> if subjectid is greater than 0 and subjectText is not null
	 */
	public boolean isValid() {
		if(subjectid > 0 && !subjectText.isEmpty())
			return true;
		return false;
	}
	
	public String toString() {
		return subjectText;
	}
}
