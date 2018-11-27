package dataSrc.deck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class DeckRDG {
	
	private long id;
	private long userID;
	public static long maxDeckID=0;
	public static int max_cards=40;
	
	public DeckRDG(long id, long userID){
		
		this.id = id;
		this.userID = userID;		
	}
	
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

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public long getId() {
		return id;
	}
	
	public int insert() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		//version auto set to 1
		String query = "INSERT INTO deck (id, userID) VALUES (?,?)";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setLong(2, userID);
		int dbcount = ps.executeUpdate();//returns 1 if DML, 0 if SQL
		
		ps.close();
		return dbcount;
		
	}
	
	public int update() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "UPDATE deck SET userID= ? WHERE id=?;"; 
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, userID);
		ps.setLong(2, id);
		
		int count = ps.executeUpdate();
		
		ps.close();
		return count;
		
	}
	
	public int delete() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "DELETE FROM deck WHERE id=?;";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		int count = ps.executeUpdate();
		ps.close();
		
		return count;
		
	}
	
	public static DeckRDG find(long id) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM deck WHERE id = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		DeckRDG deck = null;
		if (rs.next()) {
			deck = new DeckRDG(rs.getLong("id"), rs.getLong("userID"));
		}
		
		rs.close();
		ps.close();
		
		return deck;
		
	}
	
	public static DeckRDG findByUserID(long id) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM deck WHERE userID = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		DeckRDG deck = null;
		if (rs.next()) {
			deck = new DeckRDG(rs.getLong("id"), rs.getLong("userID"));
		}
		
		rs.close();
		ps.close();
		
		return deck;
		
	}
	

}
