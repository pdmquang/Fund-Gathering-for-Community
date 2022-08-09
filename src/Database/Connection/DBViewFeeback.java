package Database.Connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBViewFeeback {
	private DBController database_con = new DBController();
	private Connection con;
	private ResultSet rs;
	
	private int eventId = 1;
	
	private String sql;
	private String quesNo;

	public String getQuesNo() {
		return quesNo;
	}

	public void setQuesNo(String quesNo) {
		this.quesNo = quesNo;
		
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	// retrieve Feedback Questions
	public List<String> retrieveQuestions(String eventName){
		
		List<String> questionStatement = new ArrayList<String>();
		con = database_con.getConnection();
		try {
			sql = "select Question.Statement from Events "
					+ "inner join Question on Question.EventId = Events.eventId where "
					+ "Events.eventId = (select Events.eventId from Events "
					+ "where Events.eventName = '"+eventName+"');";
			
			rs = database_con.readRequest(sql);
			
			while(rs.next()){
				questionStatement.add(rs.getString(1));
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return questionStatement;
	}
	
	//return Event to display on Combo box
	public List<String> retrieveEvents(){
		List<String> events = new ArrayList<>();
		con = database_con.getConnection();
		
		try {
			sql = "select Events.eventName, Events.eventId ,count(Events.eventId) "
					+ "from Events inner join Rating on Events.eventId = Rating.eventId group by Events.eventName;";
			
			rs = database_con.readRequest(sql);
			
			while(rs.next()){
				events.add(rs.getString(1));
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return events;
	}
	
	// retrieve answers
	public List<Integer> retrieveAnswer(String eventName){
		List<Integer> answer = new ArrayList<>();
		
		con = database_con.getConnection();
		
		StringBuilder quesStatement = new StringBuilder();
		quesStatement.append("Rating.Question_");
		quesStatement.append(this.quesNo);

		try {
			sql = "Select '"+quesStatement+"',count(Rating.eventId) no_of_volunteer "
					+ "from Events inner join Rating on Events.eventId = Rating.eventId "
					+ "where Rating.eventId = "
					+ "(select Events.eventId from Events where Events.eventName = '"+eventName+"') "
					+ "group by " + quesStatement;
			
			rs = database_con.readRequest(sql);
			
			while(rs.next()){
				answer.add(rs.getInt(2));
			}

		} catch (SQLException e1) {
			System.err.println("DataBase Error from Home_Hotel");
			e1.printStackTrace();
		}
		
		return answer;
	}
	
}
