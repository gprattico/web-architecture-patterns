package dataSrc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class GameRDG {
	
	private long id;
	private long challengerID;
	private long challengeeID;
	private long status;//status 0 if ongoing, 1 challenger wins, 2 challengee wins, 3 game retired
	public static long maxGameID=0;
	
	public GameRDG(long id, long challengerID, long challengeeID, long status) {
		super();
		this.id = id;
		this.challengerID = challengerID;
		this.challengeeID = challengeeID;
		this.status = status;
	}
	
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
	
	public int insert() throws SQLException{

		Connection con = DbRegistry.getDbConnection();
		
		String query = "INSERT INTO game VALUES (?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setLong(2, challengerID);
		ps.setLong(3, challengeeID);
		ps.setLong(4, status);
		int count = ps.executeUpdate();
		ps.close();
		
		return count;
	}
	
	public static ArrayList<GameRDG> findAll() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM game;";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<GameRDG> gameList = new ArrayList<GameRDG>();
		GameRDG game = null;
		while(rs.next()){
			game = new GameRDG(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4));
			gameList.add(game);
		}
		
		rs.close();
		ps.close();
		
		return gameList;
	}

	public long getChallengerID() {
		return challengerID;
	}

	public void setChallengerID(long challengerID) {
		this.challengerID = challengerID;
	}

	public long getChallengeeID() {
		return challengeeID;
	}

	public void setChallengeeID(long challengeeID) {
		this.challengeeID = challengeeID;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}
}
