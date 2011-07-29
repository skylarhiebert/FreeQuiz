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
	private List<StudentGame> gamesPlayed = new ArrayList<StudentGame>();
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
	
	/**
	 * @return the gamesPlayed
	 */
	@JoinTable(name="STUDENTGAMES",
			joinColumns = @JoinColumn(name="STUDENTID"))
	public List<StudentGame> getGamesPlayed() {
		return gamesPlayed;
	}

	/**
	 * @param gamesPlayed the gamesPlayed to set
	 */
	public void setGamesPlayed(List<StudentGame> gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
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
	public Integer getNumberGames() {
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
