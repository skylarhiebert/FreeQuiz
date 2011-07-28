/**
 * 
 */
package org.freequiz.www.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Skylar Hiebert
 *
 */
@Entity
@Table(name="SUBJECTS")
public class Subject {
	private Long subjectid;
	private String subjectText;
	
	public Subject() {
		
	}
	
	public Subject(String subjectText) {
		setSubjectText(subjectText);
	}
	
	/**
	 * Class Constructor
	 * @param subjectid the subjectid to set
	 * @param subjectText the subjectText to set
	 */

	public Subject(Long subjectid, String subjectText) {
		setSubjectid(subjectid);
		setSubjectText(subjectText);
	}

	/**
	 * @return the subjectid
	 */
	@Id
	@GeneratedValue
	@Column(name="SUBJECTID")
	public Long getSubjectid() {
		return subjectid;
	}

	/**
	 * @param subjectid the subjectid to set
	 * @return <code>true</code> if the subjectid was set
	 */
	private void setSubjectid(Long subjectid) {
		this.subjectid = subjectid;
	}

	/**
	 * @return the subjectText
	 */
	@Column(name="SUBJECT", length = 255, nullable = false)
	public String getSubjectText() {
		return subjectText;
	}

	/**
	 * @param subjectText the subjectText to set
	 * @return <code>true</code> if the subjectText was set
	 */
	public void setSubjectText(String subjectText) {
		this.subjectText = subjectText;
	}

	/**
	 * 
	 * @return <code>true</code> if subjectid is greater than 0 and subjectText is not null
	 */
	@Transient
	public boolean isValid() {
		if(subjectid > 0 && !subjectText.isEmpty())
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
		result = prime * result
				+ ((subjectText == null) ? 0 : subjectText.hashCode());
		result = prime * result
				+ ((subjectid == null) ? 0 : subjectid.hashCode());
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
		Subject other = (Subject) obj;
		if (subjectText == null) {
			if (other.subjectText != null)
				return false;
		} else if (!subjectText.equals(other.subjectText))
			return false;
		if (subjectid == null) {
			if (other.subjectid != null)
				return false;
		} else if (!subjectid.equals(other.subjectid))
			return false;
		return true;
	}

	@Transient
	public String toString() {
		return subjectText;
	}
}
