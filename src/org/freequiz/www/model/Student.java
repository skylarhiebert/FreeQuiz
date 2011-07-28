/**
 * 
 */
package org.freequiz.www.model;

import java.util.List;

import javax.persistence.*;

/**
 * @author Skylar Hiebert
 *
 */
@Entity
@Table(name = "STUDENTS")
public class Student {
	private int studentid;
	private String name;
	private List<Integer> scores;
	private List<Roster> rosterMembership;
	public Student() {}
	
	/**
	 * Class constructor specifying studentid and name
	 * @param studentid
	 * @param name
	 */
	public Student(int studentid, String name) {
		setStudentid(studentid);
		setName(name);
	}
	
	public Student(String name) {
		setName(name);
	}

	/**
	 * 
	 * @return the studentid
	 */
	@Id
	@GeneratedValue
	@Column(name = "STUDENTID")
	public int getStudentid() {
		return studentid;
	}

	/**
	 * 
	 * @param studentid the studentid to set
	 * @return <code>true</code> if the studentid was set
	 */
	public boolean setStudentid(int studentid) {
		if(studentid > 0) {
			this.studentid = studentid;
			return true;
		} 
		else {
			System.err.println("Invalid studentid " + studentid + ", studentid must be greater than 0.");			
			return false;
		}
	}

	/**
	 * 
	 * @return the name
	 */
	@Column(name="NAME")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name the name to set
	 * @return <code>true</code> if the name was set
	 */
	public void setName(String name) {
		if(!name.isEmpty()) {
			this.name = name;
		}
		else {
			System.err.println("name cannot be null.");
		}
	}
	
	@ManyToMany(mappedBy = "studentList")
	public List<Roster> getRosterMembership() {
		return rosterMembership;
	}
	
	public void setRosterMembership(List<Roster> rosterMembership) {
		this.rosterMembership = rosterMembership;
	}
	
	@Transient
	public List<Integer> getScores() {
		return scores;
	}
	
	@Transient
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
	
	@Transient
	public Integer getGamesPlayed() {
		if(scores == null || scores.isEmpty())
			return 0;
		else
			return scores.size();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Transient
	@Override
	public String toString() {
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Transient
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + studentid;
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
		Student other = (Student) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (studentid != other.studentid)
			return false;
		return true;
	}
}
