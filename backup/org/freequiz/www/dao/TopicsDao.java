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
import org.freequiz.www.model.Topic;

/**
 * @author Skylar Hiebert
 *
 */
public class TopicsDao {
	private static Connection conn;
	
	
	public TopicsDao() {
		initComponents();
	}
	
	private void initComponents() {
		conn = null;
	}
	
	public static Topic getTopicByid(int topicid) {
		Statement stmt = null;
		ResultSet results = null;
		
		try {		
			conn = DerbyController.createConnection();
			stmt = conn.createStatement();
			
			results = stmt.executeQuery("SELECT * FROM topics WHERE topicid = " + topicid);
			int id = results.getInt(1);
			String text = results.getString(2);
			int gradeLevel = results.getInt(3);
			System.out.println("Id: " + id + " Text: " + text + " gradeLevel " + gradeLevel);
			return new Topic(results.getInt(1), results.getString(2), results.getInt(3));
		}
		catch (SQLException ex) {
			
		}
		
		return null;
	}
	
	public static Vector<Topic> getAllTopicsVector() {
		Vector<Topic> topics = new Vector<Topic>();
		Statement stmt = null;
		ResultSet results = null;
		SubjectsDao subjectsDao = new SubjectsDao();
		
		try {		
			conn = DerbyController.createConnection();
			stmt = conn.createStatement();
			
			results = stmt.executeQuery("SELECT * FROM topics ORDER BY topic");
		
			// Iterate through the resultset and add to our Vector	
			int rowsReturned = 0;
			while(results.next()) {
				int topicid;
				String topic;
				int gradeLevel;
				
				topicid = results.getInt(1);
				topic = results.getString(2);
				gradeLevel = results.getInt(3);
				

				System.out.println("Adding Topic");
				topics.add(new Topic(topicid, topic, gradeLevel));
				rowsReturned++;	
			}	
			System.out.println("Rows read - " + rowsReturned);

			for(Topic t : topics) {
				ResultSet rs = null;
				Subject subject = null;
				rs = stmt.executeQuery("SELECT subjectid FROM topicSubjects WHERE topicid = " + t.getTopicid());
				if(!rs.next()) {
	                System.err.println("No rows in ResultSet");
				} 
				else {
					System.out.println("Getting Subject for " + t + " topic");
					subject = subjectsDao.getSubjectByid(rs.getInt(1));
				}
				t.setSubject(subject);
			}
			conn = null;
			System.out.println("Returning topics");
			return topics;
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
	
	public static Vector<Integer> getGradeLevels() {
		Vector<Integer> gradeLevels = new Vector<Integer>();
		Statement stmt = null;
		ResultSet results = null;
		
		try {		
			conn = DerbyController.createConnection();
			stmt = conn.createStatement();
			
			results = stmt.executeQuery("SELECT DISTINCT gradeLevel FROM topics");
		
			// Iterate through the resultset and add to our Vector	
			int rowsReturned = 0;
			while(results.next()) {
				int gradeLevel;
				
				gradeLevel = results.getInt(1);			

				System.out.println("Adding GradeLevel");
				gradeLevels.add(new Integer(gradeLevel));
				rowsReturned++;	
			}	
			System.out.println("Rows read - " + rowsReturned);
			return gradeLevels;
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
