package dataSrc.game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class GameFinder {
	
	public static long maxGameID=0;

	public static synchronized long getMaxGameID() throws SQLException{
		
		//need the if 0 or else can't insert more than one at a time
		if (maxGameID == 0) {
			Connection con = DbRegistry.getDbConnection();
			String query = "Select MAX(id) as id FROM game;";
			
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				maxGameID = rs.getLong("id");
			
			ps.close();
			rs.close();
		}
		
		return ++maxGameID;
	}
	
	
	public static ResultSet findAll() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM game;";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
//		
//		ArrayList<GameRDG> gameList = new ArrayList<GameRDG>();
//		GameRDG game = null;
//		while(rs.next()){
//			game = new GameRDG(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4));
//			gameList.add(game);
//		}
//		
//		rs.close();
//		ps.close();
		
		return rs;
	}
	
	public static ResultSet find(long attribute) throws SQLException {
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM game WHERE challengerID=? OR challengeeID=?;";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, attribute);
		ps.setLong(2, attribute);
		ResultSet rs = ps.executeQuery();
//		
//		GameRDG game =null;
//		if(rs.next()) {
//			game = new GameRDG(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4));
//		}
//		
//		ps.close();
//		rs.close();
		
		return rs;
	}


}
