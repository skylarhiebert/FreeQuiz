/**
 * 
 */
package org.freequiz.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @author Skylar Hiebert
 *
 */
final public class DerbyController {
	//private String framework = "embedded";
	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String protocol = "jdbc:derby:";
	private static String dbname = "fqdb";
	private static String dbun = "freequizUser";
	private static String dbpw = "password";
	private static Connection sqlConn = null; // jdbc Connection

	
//	public DerbyController() {
//		this("fqdb", "freequizUser", "!freequiz!");
//	}
//	
//	public DerbyController(String db, String user, String pass) {
//		dbname = db;
//		dbun = user;
//		dbpw = pass;
//	}
	
	public static Connection createConnection() {
		Properties props = new Properties();
		props.put("user", dbun);
		props.put("password", dbpw);
		
		try {
	        try {
	            Class.forName(driver).newInstance();
	            System.out.println("Loaded the appropriate driver");
	        } catch (ClassNotFoundException cnfe) {
	            System.err.println("\nUnable to load the JDBC driver " + driver);
	            System.err.println("Please check your CLASSPATH.");
	            cnfe.printStackTrace(System.err);
	        } catch (InstantiationException ie) {
	            System.err.println(
	                        "\nUnable to instantiate the JDBC driver " + driver);
	            ie.printStackTrace(System.err);
	        } catch (IllegalAccessException iae) {
	            System.err.println(
	                        "\nNot allowed to access the JDBC driver " + driver);
	            iae.printStackTrace(System.err);
	        }

			// Get a connection
			sqlConn = DriverManager.getConnection(protocol + dbname);
			System.out.println("Connected to database " + dbname);
			return sqlConn;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static void closeConnection() {
		try	{
		    // the shutdown=true attribute shuts down Derby
		    DriverManager.getConnection(protocol + ";shutdown=true");
		
		    // To shut down a specific database only, but keep the
		    // engine running (for example for connecting to other
		    // databases), specify a database in the connection URL:
		    //DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
		}
		catch (SQLException ex)	{
		    if (( (ex.getErrorCode() == 50000)
		            && ("XJ015".equals(ex.getSQLState()) ))) {
		        // we got the expected exception
		        System.out.println("Derby shut down normally");
		        // Note that for single database shutdown, the expected
		        // SQL state is "08006", and the error code is 45000.
		    } else {
		        // if the error code or SQLState is different, we have
		        // an unexpected exception (shutdown failed)
		        System.err.println("Derby did not shut down normally");
		        printSQLException(ex);
		    }
		}
		finally {
            // release all open resources to avoid unnecessary memory usage
            //Connection
            try {
                if (sqlConn != null) {
                    sqlConn.close();
                    sqlConn = null;
                }
            } catch (SQLException ex) {
                printSQLException(ex);
            }
        }
	}

	/**
	 * Prints details of an SQLException chain to <code>System.err</code>.
	 * Details included are SQL State, Error code, Exception message.
	 *
	 * @param ex the SQLException from which to print details.
	 */
	public static void printSQLException(SQLException ex)
	{
	    // Unwraps the entire exception chain to unveil the real cause of the
	    // Exception.
	    while (ex != null)
	    {
	        System.err.println("\n----- SQLException -----");
	        System.err.println("  SQL State:  " + ex.getSQLState());
	        System.err.println("  Error Code: " + ex.getErrorCode());
	        System.err.println("  Message:    " + ex.getMessage());
	        // for stack traces, refer to derby.log or uncomment this:
	        ex.printStackTrace(System.err);
	        ex = ex.getNextException();
	    }
	}
	
}
