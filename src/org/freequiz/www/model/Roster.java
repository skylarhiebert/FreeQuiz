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
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author Skylar Hiebert
 *
 */
@Entity
@Table(name="CLASS")
//@SecondaryTable(name="STUDENT_ROSTER")
public class Roster {
	private Long rosterid;
	private List<Student> studentList = new ArrayList<Student>();
	private String name;
	private String description;

	public Roster() {
		super();
		setName("New Roster");
		setDescription("New Roster Description");
	}
	
	/**
	 * Class constructor specifying the name and description of the Roster
	 * @param name must not be null
	 * @param description must not be null
	 */
	public Roster(String name, String description) {
		super();
		setName(name);
		setDescription(description);
	}

	/**
	 * Class constructor specifying the students, name and description of the Roster
	 * @param roster the collection of students to set, must not be null
	 * @param name the name to set, must not be null
	 * @param description the description to set, must not be null
	 */
	public Roster(List<Student> roster, String name, String description) {
		super();
		setStudentList(roster);
		setName(name);
		setDescription(description);
	}
	
	/**
	 * Class constructor specifying the students, name and description of the Roster
	 * @param roster the collection of students to set, must not be null
	 * @param name the name to set, must not be null
	 * @param description the description to set, must not be null
	 */
	public Roster(Long rosterid, List<Student> roster, String name, String description) {
		super();
		setRosterid(rosterid);
		setStudentList(roster);
		setName(name);
		setDescription(description);
	}

	/**
	 * @return the rosterid
	 */
	@Id
	@GeneratedValue
	@Column(name="ROSTERID")
	public Long getRosterid() {
		return rosterid;
	}

	/**
	 * @param rosterid the rosterid to set
	 */
	private void setRosterid(Long rosterid) {
		this.rosterid = rosterid;
	}

	/**
	 * @return the roster
	 */
	@ManyToMany
	@JoinTable(name="STUDENT_ROSTER",
			joinColumns={@JoinColumn(name="ROSTERID")},
			inverseJoinColumns={@JoinColumn(name="STUDENTID")}
	)
	public List<Student> getStudentList() {
		return studentList;
	}

	/**
	 * @param studentList the roster to set
	 */
	public void setStudentList(List<Student> studentList) {
		Hibernate.initialize(studentList);
		this.studentList = studentList;
	}

	/**
	 * @return name of the roster
	 */
	@Column(name="NAME")
	public String getName() {
		return name;
	}

	/**
	 * @param name must not be null
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

	/**
	 * @return description of the roster
	 */
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description must not be null
	 * @return <code>true</code> if the name was set
	 */
	public void setDescription(String description) {
		if(!description.isEmpty()) {
			this.description = description;
		}
		else {
			System.err.println("description cannot be null.");
		}
	}
	
	@Transient
	public int getNumberOfStudents() {
		return studentList.size();
	}
	
	/**
	 * Adds a student to the class roster
	 * @param student the student to be added to the roster
	 * @return <code>true</code> if this roster changed as a result of the call (as specified by Collection.add(E))
	 */
	public boolean addStudent(Student student) {
		return this.studentList.add(student);
	}
	
	/**
	 * Removes a student from the class roster
	 * @param student the student to be added to the roster
	 * @return <code>true</code> if this list contained the specified element
	 */
	public boolean removeStudent(Student student) {
		return this.studentList.remove(student);
	}
	
	/**
	 * Appends all of the students in the specified collection to the end of this roster, in the order that they are returned by the specified collection's Iterator.
	 * @param students collection containing the students to be added to the roster
	 * @return <code>true</code> if this roster changed as a result of the call
	 * @throws NullPointerException if the specified collection is null
	 * @see AbstractCollection.add(Object)
	 */
	public boolean addStudents(Collection<Student> students) {
		return this.studentList.addAll(students);
	}
	
	/**
	 * Removes all of this roster's students that are also contained in the specified collection. After this call returns, this roster will contain no students in common with the specified collection. 
	 * @param students collection containing students to be removed from the roster
	 * @return <code>true</code> if this collection changed as a result of the call
	 * @throws UnsupportedOperationException if the removeAll method is not supported by this collection
	 * @throws ClassCastException if the types of one or more elements in this collection are incompatible with the specified collection
	 * @throws NullPointerException if this collection contains one or more null elements and the specified collection does not support null elements, or if the specified collection is null.
	 * @see remove(Object), contains(Object), Collection.removeAll(Collection<? extends E>)
	 */

	public boolean removeStudents(Collection<Student> students) {
		return this.studentList.removeAll(students);
	}
	
	public void removeAllStudents() {
		this.studentList.clear();
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
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((studentList == null) ? 0 : studentList.hashCode());
		result = prime * result
				+ ((rosterid == null) ? 0 : rosterid.hashCode());
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
		Roster other = (Roster) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (studentList == null) {
			if (other.studentList != null)
				return false;
		} else if (!studentList.equals(other.studentList))
			return false;
		if (rosterid == null) {
			if (other.rosterid != null)
				return false;
		} else if (!rosterid.equals(other.rosterid))
			return false;
		return true;
	}
	
}
