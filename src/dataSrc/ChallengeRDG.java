package dataSrc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class ChallengeRDG {

	long id;
	long challenger;
	long challengee;
	int status;
	public static long maxChallengeID = 0;

	
	public ChallengeRDG(long id, long Challenger, long Challengee, int status){
		
		this.id = id;
		this.challenger = Challenger;
		this.challengee = Challengee;
		this.status = status;
	}
	
	//method madee synchronized because we dont want users simulataneously getting the same ID
		public static synchronized long getMaxChallengeID() throws SQLException{
			
			//need the if 0 or else can't insert more than one at a time
			if (maxChallengeID == 0) {
				Connection con = DbRegistry.getDbConnection();
				String query = "Select MAX(id) as id FROM challenge;";
				
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next())
					maxChallengeID = rs.getLong("id");
				
				ps.close();
				rs.close();
			}
			
			return ++maxChallengeID;
		}
	
		
	public int insert() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "INSERT INTO challenge (id, challenger,challengee, status) VALUES (?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setLong(2, challenger); //set challenger
		ps.setLong(3, challengee); //set challengee
		ps.setInt(4, status);
		int dbcount = ps.executeUpdate();//returns 1 if DML, 0 if SQL
		
		ps.close();
		return dbcount;
	}
	
	public int update() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "UPDATE users SET challenger = ?, challengee = ?, status= ? WHERE id=?;"; 
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, challenger);
		ps.setLong(2, challengee);
		ps.setInt(3, status);
		ps.setLong(4, id);
		
		int count = ps.executeUpdate();
		
		ps.close();
		return count;
	}
	
	public int delete() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "DELETE FROM users WHERE id=?;";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		
		int count = ps.executeUpdate();
		
		ps.close();
		
		return count;
		
	}
	
	
}
