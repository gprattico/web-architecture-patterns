package dataSrc.challenge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class ChallengeTDG {

	public static int insert(long id, long challenger, long challengee, int status, int version,long deckOfChallenger) throws SQLException {
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "INSERT INTO challenge (id, challenger,challengee, status,version,deckOfChallenger) VALUES (?,?,?,?,1,?)";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setLong(2, challenger); //set challenger
		ps.setLong(3, challengee); //set challengee
		ps.setInt(4, status);
		ps.setLong(5, deckOfChallenger);
		int dbcount = ps.executeUpdate();//returns 1 if DML, 0 if SQL
		
		ps.close();
		return dbcount;
		
	}
	
	public static int update(long challenger, long challengee, int status, long id, int version, long deckOfChallenger) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "UPDATE challenge SET challenger = ?, challengee = ?, status= ?, deckOfChallenger=? WHERE id=? AND version = ?;"; 
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, challenger);
		ps.setLong(2, challengee);
		ps.setInt(3, status);
		ps.setLong(4, deckOfChallenger);
		ps.setLong(5, id);
		ps.setInt(6, version);
		

		int count = ps.executeUpdate();
		
		ps.close();
		return count;
	}
	
	public static int delete(long id) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "DELETE FROM challenge WHERE id=?;";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		
		int count = ps.executeUpdate();
		
		ps.close();
		
		return count;
		
	}
	
	
}
