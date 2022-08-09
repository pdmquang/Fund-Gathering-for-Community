package Database.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Person;


public class DBUpdateParticular {

	/**
	 * Purpose:	This method converts a ResultSet object into an
	 * 			Expense object
	 * Input:	ResultSet object
	 * Return:	Expense object
	 */
	private static Person convertToPerson(ResultSet rs) throws SQLException {
		Person person;
		String nric = rs.getString("nric");
	    String personName = rs.getString("personName");
	    int homeNo = rs.getInt("homeNo");
	    int contactNo = rs.getInt("contactNo");
	    String email = rs.getString("email");
	    String dateOfBirth = rs.getString("dateOfBirth");
	    String address = rs.getString("address");
	    String language = rs.getString("language");
	    String occupation = rs.getString("occupation");
	    String gender = rs.getString("gender");
	    String company = rs.getString("company");

		person = new Person(nric, personName, homeNo, contactNo, email, dateOfBirth, address, language, occupation, gender, company);

		return person;
	}
	/**
	 * Purpose:	This method retrieves all the expense records in the
	 * 			database and returns them in an ArrayList.
	 * Input:	Nil
	 * Return:	ArrayList of Expense objects
	 */
	public static ArrayList<Person> retrieveAllPerson() {
		// declare local variables
		ArrayList<Person> list = new ArrayList<Person>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;

		// Step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM Person";

		// step 3 - using DBControlle readRequest method
		rs = db.readRequest(dbQuery);
		try {
			while (rs.next()) {
				Person person = convertToPerson(rs);
				list.add(person);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}
	/**
	 * Purpose:	This method retrieves an expense record by id
	 * 			and returns it to the calling program.
	 * Input:	int 
	 * Return:	Expense object
	 */
	public static Person retrieveAllByNRIC(String nric) {
		// declare local variables
		Person person = null;
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM Person WHERE nric = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, nric);
			rs = pstmt.executeQuery();
			if (rs.next()) { // first record found
				person = convertToPerson(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();
		return person;
	}

	public static ArrayList<Person> retrievePersonByNRIC(String nric) {
		// declare local variables
		ArrayList<Person> list = new ArrayList<Person>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM Person WHERE nric = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, nric);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Person person = convertToPerson(rs);
				list.add(person);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}

	
	public static boolean updatePerson(Person person) {
		//declare local variables
		boolean success = false;
		DBController db = new DBController();
		String dbQuery;	
		PreparedStatement pstmt;
		
		//step 1 - establish connection to database
		db.getConnection();		

		//step 2 - declare the SQL statement
		dbQuery = "UPDATE Person SET personName= ?, homeNo=' ?, contactNo= ?, email= ?, dateOfBirth= ?, address= ?, language= ?, occupation= ?, gender = ?, company = ? where nric='"+person.getNric()+"'";
		pstmt = db.getPreparedStatement(dbQuery);
		
		//step 3 - to update record using executeUpdate method
		try {
			pstmt.setString(1, person.getPersonName());
			pstmt.setInt(2, person.getHomeNo());
			pstmt.setInt(3, person.getContactNo());
			pstmt.setString(4, person.getEmail());
			pstmt.setString(5, person.getDateOfBirth());
			pstmt.setString(6, person.getAddress());
			pstmt.setString(7, person.getLanguage());
			pstmt.setString(8, person.getOccupation());
			pstmt.setString(9, person.getGender());
			pstmt.setString(10, person.getCompany());
			if (pstmt.executeUpdate() == 1)
				success = true;
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(success);
	    //step 4 - close connection
		db.terminate();
		
		return success;		
	}
}