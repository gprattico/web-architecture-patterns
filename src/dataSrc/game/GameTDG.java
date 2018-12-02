package dataSrc.game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class GameTDG {

	public static int insert(long id, long challengerID, long challengeeID, long status, int version, long currentTurn) throws SQLException{

		Connection con = DbRegistry.getDbConnection();
		
		String query = "INSERT INTO game VALUES (?,?,?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setLong(2, challengerID);
		ps.setLong(3, challengeeID);
		ps.setLong(4, status);
		ps.setInt(5, version);
		ps.setLong(6, currentTurn);
		int count = ps.executeUpdate();
		ps.close();
		
		return count;
	}
	
	public static int delete(long id) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "DELETE FROM game WHERE id=?;";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		
		int count = ps.executeUpdate();
		
		ps.close();
		
		return count;
	}

	public static int update(long id, long challengerID, long challengeeID, long status, int version, long currentTurn) throws SQLException {
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "UPDATE game SET challenger = ?, challengee= ?, status = ? , version = version +1, currentTurn = ? WHERE id=? AND version =?;";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, challengerID);
		ps.setLong(2, challengeeID);
		ps.setLong(3, status);
		ps.setLong(4, id);
		ps.setInt(5, version);
		ps.setLong(6, currentTurn);
		
		int count = ps.executeUpdate();
		
		ps.close();
		return count;
		
		
	}
	
}
