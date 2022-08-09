//package Database.Connection;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//
//public class DBController {
//	static Connection con = null;
//	Statement stmt = null;
//	
//	public static Connection dbConnection(){
//		
//			try {
//				Class.forName("org.sqlite.JDBC");
//				con = DriverManager.getConnection("jdbc:sqlite:E:/NYP STUFF/OOADP/Project/Community Project_javafx/CommunityDatabase.db");
//				
//			}
//			//System.out.println("Error In DBController " );
//			catch(Exception e){
//				System.out.println("DBController Error");
//				e.printStackTrace();
//			}
//			
//			return con;
//	}
//	
//	
//}


package Database.Connection;


import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.Statement;

public class DBController {
	private Connection con; 
	
	/********************************************************
	 * Method Name : testDriver
	 * Input Parameter : nil 
	 * Purpose : To test if the driver is properly installed
	 * Return :nil
	 *******************************************************/
	
	public Connection getConnection(){ 
		String url = ""; 
		try { 
			Class.forName("org.sqlite.JDBC");
			url = "jdbc:sqlite:E:/NYP STUFF/OOADP/Project/Community Project_javafx/CommunityDatabase.db"; 
			con = DriverManager.getConnection(url, "testing", ""); 
		} 
		catch (java.sql.SQLException | ClassNotFoundException e) { 
			System.out.println("Connection failed ->"+ url); 
			System.out.println(e); 
		}
		return con; 
	} 
	/************************************************************
	 * Method Name : readRequest 
	 * Input Parameter : String (database query) 
	 * Purpose : Obtain the result set from the db query 
	 * Return : resultSet (records from the query)
	 ************************************************************/
	public ResultSet readRequest(String dbQuery) {
		ResultSet rs = null;
		try {
			// create a statement object
			Statement stmt = con.createStatement();
			// execute an SQL query and get the result
			rs = stmt.executeQuery(dbQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/***********************************************************
	 * Method Name : updateRequest 
	 * Input Parameter : String (database query) 
	 * Purpose : Execute update using the db query 
	 * Return : int (count is 1 if successful)
	 ***********************************************************/
	public int updateRequest(String dbQuery) {
		int count = 0;
		//System.out.println("DB Query: " + dbQuery);
		try {
			// create a statement object
			Statement stmt = con.createStatement();
			// execute an SQL query and get the result
			count = stmt.executeUpdate(dbQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	/***********************************************************
	 * Method Name : getPreparedStatement 
	 * Input Parameter : String (database query) 
	 * Purpose : Gets Prepared Statement using the db query 
	 * Return : Prepared Statement
	 ***********************************************************/
	public PreparedStatement getPreparedStatement(String dbQuery) {
		PreparedStatement pstmt = null;
		//System.out.println("DB prepare statement: " + dbQuery);
		try {
			// create a statement object
			pstmt = con.prepareStatement(dbQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pstmt;
	}	
	/***********************************************************
	 * Method Name : terminate 
	 * Input Parameter : nil 
	 * Purpose : Close db conection 
	 * Return :nil
	 **********************************************************/
	public void terminate() {
		// close connection
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
