package Database.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Sponsorship;

public class DBSponsorship {
	@SuppressWarnings("resource")
	public static boolean createSponsor(ArrayList<Sponsorship> sponsorshipArray, int eventID){
		boolean success = false;
		String dbCreateSponsorshipRecordQuery;
		String dbCreateSponsorItemQuery;
		DBController db = new DBController();
		PreparedStatement pstmt;		
		db.getConnection();

		// Declare and execute query to get event ID
		dbCreateSponsorshipRecordQuery = "INSERT INTO Sponsorship(eventid, nric) VALUES(?, ?)";
		dbCreateSponsorItemQuery = "INSERT INTO Sponsor_Item(nric, amount, type) VALUES(?, ?, ?)";
		pstmt = db.getPreparedStatement(dbCreateSponsorshipRecordQuery);
		try {
			pstmt.setInt(1, eventID);
			pstmt.setString(2, sponsorshipArray.get(0).getSponsorshipNric());
			if (pstmt.executeUpdate() == 1){
				success = true;
				pstmt = db.getPreparedStatement(dbCreateSponsorItemQuery);
			}
			for (int i = 0; i < sponsorshipArray.size(); i++){
				pstmt.setString(1, sponsorshipArray.get(i).getSponsorshipNric());
				pstmt.setDouble(2, sponsorshipArray.get(i).getDonationAmt());
				pstmt.setString(3, sponsorshipArray.get(i).getDonationType());
				if (pstmt.executeUpdate() == 1){
					success = true;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}
	
	
}
