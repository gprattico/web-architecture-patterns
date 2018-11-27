package dataSrc.challenge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class ChallengeFinder {
	
	//included because we do not use auto increment in our relational db
	public static long maxChallengeID=0;
	
	public static synchronized long getMaxChallengeID() throws SQLException{
		
		//need the if 0 or else can't insert more than one at a time
		if (maxChallengeID == 0) {
			Connection con = DbRegistry.getDbConnection();
			String query = "Select MAX(id) as id FROM challenge;";
			
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				maxChallengeID = rs.getLong("id");
			
			ps.close();
			rs.close();
		}
		
		return ++maxChallengeID;
	}
	
	
//	public static Challenge find(long id) throws SQLException{
//		
//		Connection con = DbRegistry.getDbConnection();
//		
//		String query = "SELECT id, challenger, challengee, status FROM challenge WHERE id = ?;";
//		PreparedStatement ps = con.prepareStatement(query);
//		ps.setLong(1, id);
//		ResultSet rs = ps.executeQuery();
//		
//		//temp user
//		ChallengeRDG challenge = null;
//		if (rs.next()) {
//			challenge = new ChallengeRDG(rs.getLong("id"), rs.getLong("challenger"), rs.getLong("challengee"), rs.getInt("status"));
//		}
//		
//		rs.close();
//		ps.close();
//		
//		return challenge;
//	}
	
	

}
