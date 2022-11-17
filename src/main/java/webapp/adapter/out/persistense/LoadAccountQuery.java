package webapp.adapter.out.persistense;

import main.JdbcCreateTest;
import webapp.domain.Account;

import java.sql.*;

class LoadAccountQuery {

	public Account getAccount() {
		Account account = null;
	      try(Connection conn = DriverManager.getConnection(JdbcCreateTest.CONNECTION_URL, JdbcCreateTest.USER, JdbcCreateTest.PASSWORD);
	    		  Statement stmt = conn.createStatement()) {
	    	 String query;
	    	 query = "USE accounts;";
	    	 stmt.executeUpdate(query);
	    	 
	    	 query = "SELECT Balance FROM Account2;";
	    	 ResultSet result = stmt.executeQuery(query);
	    	 result.next();
	    	 System.out.println("Database fetched: " + result.getInt("Balance"));
	    	 
	    	 account = new Account(result.getInt("Balance"));
	         
	      }catch( SQLException e ){
	             System.err.println( "SQLException: " + e.getMessage() );
	             System.err.println( "SQLState:     " + e.getSQLState() );
	             System.err.println( "VendorError:  " + e.getErrorCode() );
	      }
		return account;
	}

}
