package dataSrc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class HandRDG {
	
	private long id;
	private long gameID;
	private long userID;
	public static long maxHandID=0;
	
	public HandRDG(long id, long gameID, long userID) {
		super();
		this.id = id;
		this.gameID = gameID;
		this.userID = userID;
	}

	public long getGameID() {
		return gameID;
	}

	public void setGameID(long gameID) {
		this.gameID = gameID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public long getId() {
		return id;
	}
	
	public static synchronized long getMaxHandID() throws SQLException{
		
		//need the if 0 or else can't insert more than one at a time
		if (maxHandID == 0) {
			Connection con = DbRegistry.getDbConnection();
			String query = "Select MAX(id) as id FROM hand;";
			
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				maxHandID = rs.getLong("id");
			
			ps.close();
			rs.close();
		}
		
		return ++maxHandID;
	}
	
	public int insert() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		//version auto set to 1
		String query = "INSERT INTO hand (id, gameID, userID) VALUES (?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setLong(2, gameID);
		ps.setLong(3, userID);
		int dbcount = ps.executeUpdate();//returns 1 if DML, 0 if SQL
		
		ps.close();
		return dbcount;
	}
	
	public int update() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "UPDATE hand SET gameID = ?, userID = ?, WHERE id=?;"; 
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, gameID);
		ps.setLong(2, userID);
		ps.setLong(3, id);
		
		int count = ps.executeUpdate();
		
		ps.close();
		return count;
		
	}

	public static HandRDG findByUserId(long ID) throws SQLException {

		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM hand WHERE userID = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, ID);
		ResultSet rs = ps.executeQuery();
		HandRDG hand = null;
		if (rs.next()) {
			hand = new HandRDG(rs.getLong("id"), rs.getLong("gameID"), rs.getLong("userID"));
		}
		
		rs.close();
		ps.close();
		
		return hand;
		
	}
}
