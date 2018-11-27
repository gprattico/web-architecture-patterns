package dataSrc.deck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class DeckTDG {

	public static int insert(long id, long userID) throws SQLException{
		
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
	
	public static int update(long id, long userID) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "UPDATE deck SET userID= ? WHERE id=?;"; 
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, userID);
		ps.setLong(2, id);
		
		int count = ps.executeUpdate();
		
		ps.close();
		return count;
		
	}
	
	public static int delete(long id) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "DELETE FROM deck WHERE id=?;";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		int count = ps.executeUpdate();
		ps.close();
		
		return count;
		
	}
	
}
