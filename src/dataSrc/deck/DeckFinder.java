package dataSrc.deck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class DeckFinder {
	
	public static long maxDeckID=0;


	public static synchronized long getMaxDeckID() throws SQLException{
		
		//need the if 0 or else can't insert more than one at a time
		if (maxDeckID == 0) {
			Connection con = DbRegistry.getDbConnection();
			String query = "Select MAX(id) as id FROM deck;";
			
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				maxDeckID = rs.getLong("id");
			
			ps.close();
			rs.close();
		}
		
		return ++maxDeckID;
	}
	
	public static ResultSet find(long id) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM deck WHERE id = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
//		DeckRDG deck = null;
//		if (rs.next()) {
//			deck = new DeckRDG(rs.getLong("id"), rs.getLong("userID"));
//		}
//		
//		rs.close();
//		ps.close();
		
		return rs;
	}
	
	public static ResultSet findByUserID(long id) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM deck WHERE userID = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
//		DeckRDG deck = null;
//		if (rs.next()) {
//			deck = new DeckRDG(rs.getLong("id"), rs.getLong("userID"));
//		}
//		
//		rs.close();
//		ps.close();
		
		return rs;
		
	}

	public static ResultSet findAllByUserID(long id) throws SQLException {
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM deck WHERE userID = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		
		return rs;
		
	}
	
}
