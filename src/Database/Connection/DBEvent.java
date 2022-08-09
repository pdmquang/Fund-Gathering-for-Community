package Database.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Entity.CharityEvent;
import Entity.CharityEventRecommendation;
import Entity.CharityEventCombine;

public class DBEvent {
	private Connection con;
	private DBController database_con = new DBController();
	//Create Event
	
	public Boolean createExpense(String nric, ArrayList<Integer> eventIdList, ArrayList<String> eventDateList) {
		// declare local variables
		boolean success = false;
		
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - establish connection to database
		con = database_con.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "INSERT INTO Registration(nric, eventId, schedule) "
				+ "VALUES(?, ?, ?);";
		
		//dbQuery = "INSERT INTO expense(id, dateSpend, category, amount, content) VALUES(?, ?, ?, ?, ?)";
		try {
		pstmt = con.prepareStatement(dbQuery);

		for(int i = 0; i < eventIdList.size(); i++){
			
				pstmt.setString(1, nric);
				pstmt.setInt(2, eventIdList.get(i));
				System.out.println("DBEVENT eventIdList.get(i) :: " + eventIdList.get(i));
				pstmt.setString(3, eventDateList.get(i));
				System.out.println("DBEVENT eventDateList.get(i) :: " + eventDateList.get(i));
				if (pstmt.executeUpdate() == 1)
					success = true;
		}
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (Exception e) {
			System.out.println("Multiple Update error");
			e.printStackTrace();
		}
		
		
		return success;
		// step 4 - close connection
//		try {
//		     con = 	database_con.getConnection();;
//		    return conn;
//		} catch (SQLException e) {
//		    return null;
//		}
	}
	
	/*
	 * Retrieve Description
	 * (category,name,start,end,venue)
	 *  from database
	 */
	public CharityEvent retrieveRegisterDetail(String fileName)
	{
		String eventTitle = "";
		String eventType = "";
		String startDate = "";
		String endDate = "";
		
		StringBuilder filechanged = new StringBuilder();
		filechanged.append("\"");
		filechanged.append(fileName);
		filechanged.append("\"");
		con = database_con.getConnection();
		
		try {
			String sql = "select Events.eventName, Events.eventType, Events.startDate, Events.endDate "
						+ "from Events where Events.image = " + filechanged + ";";
			ResultSet rs = con.createStatement().executeQuery(sql);
			while(rs.next()){
				eventTitle = rs.getString(1);
				eventType = rs.getString(2);
				startDate = rs.getString(3);
				endDate = rs.getString(4);
			}

		} catch (SQLException e1) {
			System.err.println("DataBase Error from DBEvent");
			e1.printStackTrace();
		}
		
		CharityEvent event = new CharityEvent(eventTitle, eventType, startDate, endDate);
		return event;
	}
	
	public CharityEvent retrieveDescription() 
	{
		List<String> category = new ArrayList<String>();
		List<String> name = new ArrayList<String>();
		List<String> start = new ArrayList<String>();
		List<String> end = new ArrayList<String>();
		List<String> organization = new ArrayList<String>();
		List<String> description = new ArrayList<String>();
		List<String> image = new ArrayList<String>();
		
		//Connect to Database
		con = database_con.getConnection();
		
		try {
			String sql = "Select Events.eventType,Events.eventName,Events.startDate,"
							+ "Events.endDate,Events.organization,Events.decription "
							+ ",Events.image from Events "
							+ "LIMIT 6";
			ResultSet rs = con.createStatement().executeQuery(sql);
			while(rs.next()){
				//Called 11 times :((

				category.add(rs.getString(1));
				name.add(rs.getString(2));
				start.add(rs.getString(3));
				end.add(rs.getString(4));
				organization.add(rs.getString(5));
				description.add(rs.getString(6));
				image.add(rs.getString(7));
			}

		} catch (SQLException e1) {
			System.err.println("DataBase Error from Home_Hotel");
			e1.printStackTrace();
		}
		
		CharityEvent event = new CharityEvent(category, name, start, end, organization, description, image);

		// step 4 - close connection
		database_con.terminate();
		
		return event;
	}
	
	public CharityEventRecommendation retrieveRecommend() 
	{
		List<String> category = new ArrayList<String>();
		List<String> name = new ArrayList<String>();
		List<String> start = new ArrayList<String>();
		List<String> end = new ArrayList<String>();
		List<String> organization = new ArrayList<String>();
		List<String> description = new ArrayList<String>();
		List<String> image = new ArrayList<String>();
		
		//Connect to Database
		con = database_con.getConnection();
		
		try {
			String sql = "Select Events.eventType,Events.eventName,Events.startDate,Events.endDate,"
					+ "Events.organization,Events.decription,Events.image from Events "
					+ "where Events.eventType in ('Sports','Community') "
					+ "order by RANDOM() LIMIT 7;";
			ResultSet rs = con.createStatement().executeQuery(sql);
			while(rs.next()){
				//Called 11 times :((

				category.add(rs.getString(1));
				name.add(rs.getString(2));
				start.add(rs.getString(3));
				end.add(rs.getString(4));
				organization.add(rs.getString(5));
				description.add(rs.getString(6));
				image.add(rs.getString(7));
			}

		} catch (SQLException e1) {
			System.err.println("DataBase Error from Home_Hotel");
			e1.printStackTrace();
		}
		
		CharityEventRecommendation event = new CharityEventRecommendation(category, name, start, end, organization, description, image);

		// step 4 - close connection
		database_con.terminate();
		
		return event;
	}

	public ArrayList<Integer> converterFileToId(ArrayList<String> path) {
		ArrayList<Integer> eventIdList = new ArrayList<>();
		
		
		con = database_con.getConnection();
		System.out.println("path in Converter : " + path);
		for(int i = 0; i < path.size(); i++)
		{
			try {
				StringBuilder filechanged = new StringBuilder();
				filechanged.append("\"");
				filechanged.append(path.get(i));
				filechanged.append("\"");
				
				String sql = "select Events.eventId from Events "
							+ "where Events.image = " + filechanged;
				ResultSet rs = con.createStatement().executeQuery(sql);
				while(rs.next()){
					eventIdList.add(rs.getInt(1));
				}

			} catch (SQLException e1) {
				System.err.println("Converter Failed");
				e1.printStackTrace();
			}
		}	
		// step 4 - close connection
		database_con.terminate();
		
		return eventIdList;
	}
	
	public static CharityEventCombine retrieveEvent(){
		//Declare variables
		CharityEventCombine e = new CharityEventCombine();
		int id = 0;
		String eventName = null;
		String eventDetails = null;
		String donationRequest = null;
		ResultSet rs = null;
		DBController db = new DBController();
		String dbIDQuery;
		String dbNameQuery;
		String dbEventDetails;
		String dbDonationRequest;
		PreparedStatement pstmt;
		
		//get connection
		db.getConnection();
		
		// Declare and execute query to get event ID
		dbIDQuery = "SELECT id FROM Events";
		pstmt = db.getPreparedStatement(dbIDQuery);
		try {
			rs = pstmt.executeQuery();
			if (rs.next()) { // first record found
				id = rs.getInt(1);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//Get event name from the event ID obtained earlier
		dbNameQuery = "Select eventName from Events where eventId = '" + id + "'";
		pstmt = db.getPreparedStatement(dbNameQuery);
		try {
			rs = pstmt.executeQuery();
			if (rs.next()) { // first record found
				eventName = rs.getString(1);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		dbEventDetails = "Select description from Events where eventId = '" + id + "'";
		pstmt = db.getPreparedStatement(dbEventDetails);
		try {
			rs = pstmt.executeQuery();
			if (rs.next()) { // first record found
				eventDetails = rs.getString(1);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		dbDonationRequest = "Select donation_request from Events where id = '" + id + "'";
		pstmt = db.getPreparedStatement(dbDonationRequest);
		try {
			rs = pstmt.executeQuery();
			if (rs.next()) { // first record found
				donationRequest = rs.getString(1);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		// step 4 - close connection
		e.setEventName(eventName);
		e.setEventId(id);
		e.setDescription(eventDetails);
		e.setDonationRequest(donationRequest);
		System.out.println("Delete me" + e.getEventName() + e.getEventId());
		db.terminate();
		return e;
	}
	
	private static CharityEventCombine convertToEvent(ResultSet rs) throws SQLException{
		CharityEventCombine event;
		int id = rs.getInt("eventId");
		String eventName = rs.getString("eventName");
		String eventDetails = rs.getString("decription");
		String donationRequest = rs.getString("donation_request");
		int status = rs.getInt("status");
		event = new CharityEventCombine(id, eventName, eventDetails, donationRequest, status);
		return event;		
	}
	
	public static ArrayList<CharityEventCombine> retrieveAllEvent() {
		// declare local variables
		ArrayList<CharityEventCombine> eventList = new ArrayList<CharityEventCombine>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;

		// Step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM Events";

		// step 3 - using DBControlle readRequest method
		rs = db.readRequest(dbQuery);
		try {
			while (rs.next()) {
				CharityEventCombine expense = convertToEvent(rs);
				eventList.add(expense);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return eventList;
	}
		
}
