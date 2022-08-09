package Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Question;

public class DBQuestion {
	static DBController db = new DBController();
	Question qns = new Question();
	
	public void qnsConnection() throws Exception {

	}
	// Methods
		/**
		 * Purpose:	This method converts a ResultSet object into an
		 * 			Question object
		 * Input:	ResultSet object
		 * Return:	Question object
		 */
		private static Question convertToQuestion(ResultSet rs) throws SQLException {
			Question question;
			int id = rs.getInt("id");
			String text = rs.getString("text");
			int feedbackId = rs.getInt("feedbackId");
			question = new Question(id, text, feedbackId);

			return question;
		}

		/**
		 * Purpose:	This method retrieves all the question records in the
		 * 			database and returns them in an ArrayList.
		 * Input:	Nil
		 * Return:	ArrayList of Question objects
		 */
		public static ArrayList<Question> retrieveAllQuestion() {
			// declare local variables
			ArrayList<Question> list = new ArrayList<Question>();
			ResultSet rs = null;
			DBController db = new DBController();
			String dbQuery;

			// Step 1 - connect to database
			db.getConnection();

			// step 2 - declare the SQL statement
			dbQuery = "SELECT * FROM Question_Leif";

			// step 3 - using DBController readRequest method
			rs = db.readRequest(dbQuery);
			try {
				while (rs.next()) {
					Question question = convertToQuestion(rs);
					list.add(question);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// step 4 - close connection
			db.terminate();

			return list;
		}
//	public static void main (String[] args) throws Exception {
//		ArrayList<Question> list =  DBQuestion.retrieveAllQuestion();
//		
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getText());
//		}
//		//STEP 6: Clean-up environment
//		db.terminate();
//	}
}
