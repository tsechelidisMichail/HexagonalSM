package webApp.adapter.out.persistense;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import main.JdbcCreateTest;
import webApp.domain.Account;

class UpdateAccountQuery {

	public boolean updateAccount(Account account) {
	      try(Connection conn = DriverManager.getConnection(JdbcCreateTest.CONNECTION_URL, "root", "pass123");
	    		  Statement stmt = conn.createStatement();) {
	    	 String query = "";
	    	 query = "USE accounts;";
	    	 stmt.executeUpdate(query);
	    	 
	    	 query = "UPDATE account SET Balance = "+ account.getDeposit() + " LIMIT 1;";
	    	 stmt.executeUpdate(query);
	    	 System.out.println("insert back deposit: " + account.getDeposit());
	         return true;
	      }catch( SQLException e ){
	             System.err.println( "SQLException: " + e.getMessage() );
	             System.err.println( "SQLState:     " + e.getSQLState() );
	             System.err.println( "VendorError:  " + e.getErrorCode() );
	      }
	      return false;
	}
}
