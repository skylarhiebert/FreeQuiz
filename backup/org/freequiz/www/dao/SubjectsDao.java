/**
 * 
 */
package org.freequiz.www.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import org.freequiz.www.model.Subject;

/**
 * @author Skylar Hiebert
 *
 */
public class SubjectsDao {
	private static Connection conn = null;
	
	public static Subject getSubjectByid(int subjectid) {
		Statement stmt = null;
		ResultSet results = null;
		
		try {		
			conn = DerbyController.createConnection();
			stmt = conn.createStatement();
			
			results = stmt.executeQuery("SELECT * FROM subjects WHERE subjectid = " + subjectid);
			int id = results.getInt(1);
			String text = results.getString(2);
			System.out.println("Id: " + id + " Text: " + text + "\n");
			return new Subject(results.getInt(1), results.getString(2));
		}
		catch (SQLException ex) {
			
		}
		
		return null;
	}
	
	public static Vector<Subject> getAllSubjectsVector() {
		Vector<Subject> subjects = new Vector<Subject>();
		Statement stmt = null;
		ResultSet results = null;
		
		try {		
			conn = DerbyController.createConnection();
			stmt = conn.createStatement();
			
			results = stmt.executeQuery("SELECT * FROM subjects ORDER BY subject");
		
			// Iterate through the resultset and add to our Vector	
			int rowsReturned = 0;
			while(results.next()) {		
				int subjectid;
				String subjectText;
				
				subjectid = results.getInt(1);
				subjectText = results.getString(2);	
				
				System.out.println("Adding Subject");
				subjects.add(new Subject(subjectid, subjectText));
				rowsReturned++;
			}	
			System.out.println("Rows read - " + rowsReturned);
			
			conn = null;
			System.out.println("Returning Subjects");
			return subjects;
		}
		catch (SQLException ex) {
			System.err.println("SQL Exception in getAllTopicsVector\n");
			DerbyController.printSQLException(ex);
		}
		finally {
			DerbyController.closeConnection();
		}
		return null;
	}
	
}
