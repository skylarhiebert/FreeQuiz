/**
 * 
 */
package org.freequiz.www.model;

/**
 * @author Skylar Hiebert
 *
 */
public class Student {
	int studentid;
	String name;
	
	/**
	 * Class constructor specifying studentid and name
	 * @param studentid
	 * @param name
	 */
	public Student(int studentid, String name) {
		super();
		setStudentid(studentid);
		setName(name);
	}

	/**
	 * 
	 * @return the studentid
	 */
	public int getStudentid() {
		return studentid;
	}

	/**
	 * 
	 * @param studentid the studentid to set
	 */
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	/**
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
