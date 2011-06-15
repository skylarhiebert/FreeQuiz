/**
 * 
 */
package org.freequiz.www.model;

/**
 * @author Skylar Hiebert
 *
 */
public class Student {
	private int studentid;
	private String name;
	
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
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name the name to set
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
