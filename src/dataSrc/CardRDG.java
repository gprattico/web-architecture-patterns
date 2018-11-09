package dataSrc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class CardRDG {
	
	private long id;
	private long deck;
	private String type;
	private String name;
	public static long maxCardID=0;
	
	public CardRDG(long id, long deck,String type, String name){
		this.id = id;
		this.deck =deck;
		this.type = type;
		this.name = name;
	}
	
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
	
	public int insert() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "INSERT INTO card VALUES (?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setLong(2, deck);
		ps.setString(3, type);
		ps.setString(4, name);
		
		int count = ps.executeUpdate();
		ps.close();
		
		return count;
	}
	
	public int delete() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		String query = "DELETE FROM card WHERE id =?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		int count = ps.executeUpdate();
		ps.close();
		return count;
	}
	

}
