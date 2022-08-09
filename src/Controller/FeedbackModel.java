package Controller;
import java.sql.*;

import Database.Connection.DBController;
import Database.Connection.DBEvent;

public class FeedbackModel {
	public Connection connection;
	private DBController db = new DBController();
	
	public FeedbackModel() {
		connection = db.getConnection();
		if (connection == null) System.exit(1);
	}
	
	public boolean isDbConnected() {
		try {
			return !connection.isClosed();
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
