package dataSrc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
		
	public long getChallenger() {
			return challenger;
		}

		public void setChallenger(long challenger) {
			this.challenger = challenger;
		}

		public long getChallengee() {
			return challengee;
		}

		public void setChallengee(long challengee) {
			this.challengee = challengee;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public long getId() {
			return id;
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
		
		String query = "UPDATE challenge SET challenger = ?, challengee = ?, status= ? WHERE id=?;"; 
		
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
		
		String query = "DELETE FROM challenge WHERE id=?;";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		
		int count = ps.executeUpdate();
		
		ps.close();
		
		return count;
		
	}
	
	public static ChallengeRDG find(long id) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT id, challenger, challengee, status FROM challenge WHERE id = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		
		//temp user
		ChallengeRDG challenge = null;
		if (rs.next()) {
			challenge = new ChallengeRDG(rs.getLong("id"), rs.getLong("challenger"), rs.getLong("challengee"), rs.getInt("status"));
		}
		
		rs.close();
		ps.close();
		
		return challenge;
	}
	
	public static ArrayList<ChallengeRDG> findOpenByChallenger(long challenger) throws SQLException{
		
	Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT id, challenger, challengee, status FROM challenge WHERE challenger = ? AND status = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, challenger);
		ps.setInt(2, 0);//only concerned with open challenges
		ResultSet rs = ps.executeQuery();
		
		ArrayList<ChallengeRDG> challengeList = new ArrayList<ChallengeRDG>();
		ChallengeRDG challenge = null;
		while (rs.next()) {
			challenge = new ChallengeRDG(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getInt(4));
			challengeList.add(challenge);
		}
		
		rs.close();
		ps.close();
		
		return challengeList;
	}
	
	public static ArrayList<ChallengeRDG> findOpenByChallengee(long challengee) throws SQLException{
		
	Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT id, challenger, challengee, status FROM challenge WHERE challengee = ? AND status = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, challengee);
		ps.setInt(2, 0);//open challenges
		ResultSet rs = ps.executeQuery();
		
		ArrayList<ChallengeRDG> challengeList = new ArrayList<ChallengeRDG>();
		ChallengeRDG challenge = null;
		while (rs.next()) {
			challenge = new ChallengeRDG(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getInt(4));
			challengeList.add(challenge);
		}
		
		rs.close();
		ps.close();
		
		return challengeList;
	}
	
	public static ArrayList<ChallengeRDG> findAllOpen() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM challenge WHERE status = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, 0);//open challenges only
		ResultSet rs = ps.executeQuery();
		
		ArrayList<ChallengeRDG> challengeList = new ArrayList<ChallengeRDG>();
		ChallengeRDG challenge = null;
		while(rs.next()){
			challenge = new ChallengeRDG(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getInt(4));
			challengeList.add(challenge);
		}
		
		rs.close();
		ps.close();
		
		return challengeList;
		
	}
	
public static ArrayList<ChallengeRDG> findAll() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM challenge;";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<ChallengeRDG> challengeList = new ArrayList<ChallengeRDG>();
		ChallengeRDG challenge = null;
		while(rs.next()){
			challenge = new ChallengeRDG(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getInt(4));
			challengeList.add(challenge);
		}
		
		rs.close();
		ps.close();
		
		return challengeList;
		
	}
	
}
