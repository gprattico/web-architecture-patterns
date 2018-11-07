package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class ChallengeRDG {

	long id;
	long challenger;
	long challengee;
	int status;
	
	public ChallengeRDG(long id, long Challenger, long Challengee, int status){
		
		this.id = id;
		this.challenger = Challenger;
		this.challengee = Challengee;
		this.status = status;
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
	
	
}
