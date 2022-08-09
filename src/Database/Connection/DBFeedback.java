package Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Feedback;

public class DBFeedback {
	static DBController db = new DBController();
	Feedback fdbk = new Feedback();
	
	public void getFeedback() throws Exception {
		
	}
	// Mehods
	/**
	 * Purpose:	This method converts a ResultSet object into an
	 * 			Question object
	 * Input:	ResultSet object
	 * Return:	Question object
	 */
	private static Feedback convertToFeedback(ResultSet rs) throws SQLException {
		Feedback feedback;
		int id = rs.getInt("id");
		String title = rs.getString("title");
		String date = rs.getString("date");
		feedback = new Feedback(id, title, date);

		return feedback;
	}

	/**
	 * Purpose:	This method retrieves all the question records in the
	 * 			database and returns them in an ArrayList.
	 * Input:	Nil
	 * Return:	ArrayList of Question objects
	 */
	public static ArrayList<Feedback> retrieveAllFeedback() {
		// declare local variables
		ArrayList<Feedback> list = new ArrayList<Feedback>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;

		// Step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM feedback";

		// step 3 - using DBController readRequest method
		rs = db.readRequest(dbQuery);
		try {
			while (rs.next()) {
				Feedback feedback = convertToFeedback(rs);
				list.add(feedback);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}
	
	public static void main (String[] args) throws Exception {
//		ArrayList<Feedback> list =  DBFeedback.retrieveAllFeedback();
//		
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getTitle());
//		}
//		//STEP 6: Clean-up environment
//		db.terminate();
	}
}
