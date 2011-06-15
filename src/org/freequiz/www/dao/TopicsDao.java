/**
 * 
 */
package org.freequiz.www.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import org.freequiz.www.model.Subject;
import org.freequiz.www.model.Topic;

/**
 * @author Skylar Hiebert
 *
 */
public class TopicsDao {
	DerbyController derbyController;
	Connection conn;
	
	
	public TopicsDao() {
		initComponents();
	}
	
	private void initComponents() {
		derbyController = new DerbyController();
		conn = null;
	}
	
	public ArrayList<Topic> getAllTopicsArrayList() {
		ArrayList<Topic> topics = new ArrayList<Topic>();
		Statement stmt = null;
		ResultSet results = null;
		SubjectsDao subjectsDao = new SubjectsDao();
		
		try {		
			conn = derbyController.createConnection();
			stmt = conn.createStatement();
			
			results = stmt.executeQuery("SELECT * FROM topics ORDER BY topic");
		
			// Iterate through the resultset and add to our ArrayList	
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
			System.err.println("SQL Exception in getAllTopicsArrayList\n");
			DerbyController.printSQLException(ex);
		}
		finally {
			derbyController.closeConnection();
		}
		return null;
	}
	
}
