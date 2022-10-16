package main;

import java.sql.*;

public abstract class JdbcCreateTest {
	public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/";
	private JdbcCreateTest() {
		
	}
	
	protected static void createDb() {

	      try(Connection conn = DriverManager.getConnection(CONNECTION_URL, "root", "pass123");
	    		  Statement stmt = conn.createStatement();) {
	    	 String query = "";
	    	 query = "CREATE DATABASE accounts;";
	    	 try{stmt.executeUpdate(query);}catch(Exception e) {}
	    	 
	    	 query = "USE accounts;";
	    	 try{stmt.executeUpdate(query);}catch(Exception e) {}
	    	 
	    	 query = "CREATE TABLE Account(Balance int);";
	    	 try{stmt.executeUpdate(query);}catch(Exception e) {}
	    	 
	    	 query = "INSERT INTO Account VALUES (60850);";
	    	 stmt.executeUpdate(query);
	         
	         
	      }catch( SQLException e ){
	             System.err.println( "SQLException: " + e.getMessage() );
	             System.err.println( "SQLState:     " + e.getSQLState() );
	             System.err.println( "VendorError:  " + e.getErrorCode() );
	      }
	}

}
