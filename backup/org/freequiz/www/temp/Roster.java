/**
 * 
 */
package org.freequiz.www.temp;

import java.util.ArrayList;
import java.util.Collection;


/**
 * @author Skylar Hiebert
 *
 */
public class Roster {
	private ArrayList<Student> roster;
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
	public Roster(Collection<Student> roster, String name, String description) {
		super();
		this.roster.addAll(roster);
		setName(name);
		setDescription(description);
	}

	/**
	 * @return the roster
	 */
	public ArrayList<Student> getRoster() {
		return roster;
	}

	/**
	 * @param roster must be of type ArrayList<Student>
	 * @return <code>true</code> if roster was set
	 */
	public boolean setRoster(ArrayList<Student> roster) {
		if(roster.isEmpty()) {
			System.err.println("roster cannot be empty");
			return false;
		}
		else {
			this.roster = roster;
			return true;
		}
	}

	/**
	 * @return name of the roster
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name must not be null
	 * @return <code>true</code> if the name was set
	 */
	public boolean setName(String name) {
		if(!name.isEmpty()) {
			this.name = name;
			return true;
		}
		else {
			System.err.println("name cannot be null.");
			return false;
		}
	}

	/**
	 * @return description of the roster
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description must not be null
	 * @return <code>true</code> if the name was set
	 */
	public boolean setDescription(String description) {
		if(!description.isEmpty()) {
			this.description = description;
			return true;
		}
		else {
			System.err.println("description cannot be null.");
			return false;
		}
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
