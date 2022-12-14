package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class JdbcCreateTest {
	public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/";
	public static final String USER = "root";
	public static final String PASSWORD = "pass123";
	
	private JdbcCreateTest() {
		
	}
	
	protected static void createDb() {

	      try(Connection conn = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
	    		  Statement stmt = conn.createStatement()) {
	    	 String query;
	    	 query = "CREATE DATABASE accounts;";
	    	 try{stmt.executeUpdate(query);}catch(Exception e) {
				 //
			 }
	    	 
	    	 query = "USE accounts;";
	    	 try{stmt.executeUpdate(query);}catch(Exception e) {
				 //
			 }
	    	 
	    	 query = "CREATE TABLE Account2(Balance int);";
	    	 try{stmt.executeUpdate(query);}catch(Exception e) {
				//
			 }
	    	 
	    	 query = "INSERT INTO Account2 VALUES (60850);";
	    	 stmt.executeUpdate(query);
	         
	         
	      }catch( SQLException e ){
	             System.err.println( "SQLException: " + e.getMessage() );
	             System.err.println( "SQLState:     " + e.getSQLState() );
	             System.err.println( "VendorError:  " + e.getErrorCode() );
	      }
	}

}
