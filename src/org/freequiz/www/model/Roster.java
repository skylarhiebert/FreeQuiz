/**
 * 
 */
package org.freequiz.www.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Skylar Hiebert
 *
 */
public class Roster {
	ArrayList<Student> roster;
	String name;
	String description;

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
	public Roster(Collection<Student> roster, String name, String description) {
		super();
		this.roster.addAll(roster);
		setName(name);
		setDescription(description);
	}

	/**
	 * Returns an ArrayList collection of the Students
	 * @return the roster
	 */
	public ArrayList<Student> getRoster() {
		return roster;
	}

	/**
	 * Sets the class list of the Roster
	 * @param roster must be of type ArrayList<Student>
	 */
	public void setRoster(ArrayList<Student> roster) {
		this.roster = roster;
	}

	/**
	 * 
	 * @return name of the roster
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name must not be null
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return description of the roster
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description must not be null
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Adds a student to the class roster
	 * @param student the student to be added to the roster
	 * @return <code>true</code> if this roster changed as a result of the call (as specified by Collection.add(E))
	 */
	public boolean addStudent(Student student) {
		return this.roster.add(student);
	}
	
	/**
	 * Removes a student from the class roster
	 * @param student the student to be added to the roster
	 * @return <code>true</code> if this list contained the specified element
	 */
	public boolean removeStudent(Student student) {
		return this.roster.remove(student);
	}
	
	/**
	 * Appends all of the students in the specified collection to the end of this roster, in the order that they are returned by the specified collection's Iterator.
	 * @param students collection containing the students to be added to the roster
	 * @return <code>true</code> if this roster changed as a result of the call
	 * @throws NullPointerException if the specified collection is null
	 * @see AbstractCollection.add(Object)
	 */
	public boolean addStudents(Collection<Student> students) {
		return this.roster.addAll(students);
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
		return this.roster.removeAll(students);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
}
