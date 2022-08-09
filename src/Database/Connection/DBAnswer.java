package Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Answer;


public class DBAnswer {
	static DBController db = new DBController();
	Answer ans = new Answer();
	
	public void ansConnection() throws Exception {

	}
	// Methods
	/**
	 * Purpose:	This method converts a ResultSet object into an
	 * 			Question object
	 * Input:	ResultSet object
	 * Return:	Question object
	 */
	private static Answer convertToAnswer(ResultSet rs) throws SQLException {
		Answer answer;
		int questionId = rs.getInt("questionId");
		int feedbackId = rs.getInt("feedbackId");
		String answerText = rs.getString("answerText");
		answer = new Answer(questionId, feedbackId, answerText);

		return answer;
	}
	/**
	 * Purpose:	This method retrieves all the question records in the
	 * 			database and returns them in an ArrayList.
	 * Input:	Nil
	 * Return:	ArrayList of Question objects
	 */
	public static ArrayList<Answer> saveAllAnswer() {
		// declare local variables
		ArrayList<Answer> list = new ArrayList<Answer>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		
		// Step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "INSERT INTO answer (questionid, feedbackid, answertext) VALUES (?, ?, ?)";
		
		// step 3 - using DBController readRequest method
		rs = db.readRequest(dbQuery);
		try {
			while (rs.next()) {
				Answer answer = convertToAnswer(rs);
				list.add(answer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}
	
	public static void main (String[] args) throws Exception {
		
	}
}
