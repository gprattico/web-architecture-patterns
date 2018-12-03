package dataSrc.card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class CardFinder {
	
	//included because we do not use auto increment in our relational db
	public static long maxCardID=0;
	
	
	public static synchronized long getMaxCardID() throws SQLException{
		
		//need the if 0 or else can't insert more than one at a time
		if (maxCardID == 0) {
			Connection con = DbRegistry.getDbConnection();
			String query = "Select MAX(id) as id FROM card;";
			
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				maxCardID = rs.getLong("id");
			
			ps.close();
			rs.close();
		}
		
		return ++maxCardID;
	}
	
	
	
	public static ResultSet findAll(long Deckid) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM card WHERE deck = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, Deckid);
		ResultSet rs = ps.executeQuery();
		
//		//temp user
//		ArrayList<Card> cardList = new ArrayList<Card>(); 
//		while (rs.next()) {
//			cardList.add(new Card(rs.getInt("id"), rs.getInt("deck"), rs.getString("type"), rs.getString("name"),rs.getInt("status")));
//		}
//		
//		rs.close();
		//ps.close();
		
		return rs;
	}
	
	
	public static ResultSet findAllInHand(long deckID, long game) throws SQLException {

		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM card WHERE deck = ? AND status = 1 AND game = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckID);
		ps.setLong(2, game);
		ResultSet rs = ps.executeQuery();
		
//		//temp user
//		ArrayList<Card> cardList = new ArrayList<Card>(); 
//		while (rs.next()) {
//			cardList.add(new Card(rs.getInt("id"), rs.getInt("deck"), rs.getString("type"), rs.getString("name"),rs.getInt("status")));
//		}
//		
//		rs.close();
		//ps.close();
		
		return rs;
	}
	
	public static ResultSet findAllBenched(long deckID, long game) throws SQLException {

		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM card WHERE deck = ? AND status = 3 AND game=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckID);
		ps.setLong(2, game);
		ResultSet rs = ps.executeQuery();
		
		
//		ArrayList<Card> cardList = new ArrayList<Card>(); 
//		while (rs.next()) {
//			cardList.add(new Card(rs.getInt("id"), rs.getInt("deck"), rs.getString("type"), rs.getString("name"),rs.getInt("status")));
//		}
//		
//		rs.close();
		//ps.close();
		
		return rs;		
	}

}
