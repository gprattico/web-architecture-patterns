package domain.challenge;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataSrc.challenge.ChallengeFinder;

public class ChallengeInputMapper {

	public static long getMaxChallengeID() throws SQLException {
		
		return ChallengeFinder.getMaxChallengeID();
		
	}
	
	public static Challenge find(long id) throws SQLException {
		
		ResultSet rs = ChallengeFinder.find(id);
		
		Challenge challenge =null;
		if (rs.next()) {
			challenge = new Challenge(rs.getLong("id"), rs.getLong("challenger"), rs.getLong("challengee"), rs.getInt("status"), rs.getInt("version"));
		}
		
		rs.close();
		
		return challenge;
		
	}
	
	public static ArrayList<Challenge> findOpenByChallenger(long challenger) throws SQLException {
		
		ResultSet rs = ChallengeFinder.findOpenByChallenger(challenger);
		
		ArrayList<Challenge> challengeList = new ArrayList<Challenge>();
		Challenge challenge = null;
		while (rs.next()) {
			challenge = new Challenge(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getInt(4), rs.getInt("version"));
			challengeList.add(challenge);
		}
		
		rs.close();
		
		return challengeList;
	}
	
	public static ArrayList<Challenge> findOpenByChallengee(long challengee) throws SQLException{
		
		ResultSet rs = ChallengeFinder.findOpenByChallengee(challengee);
		
	
		ArrayList<Challenge> challengeList = new ArrayList<Challenge>();
		Challenge challenge = null;
		while (rs.next()) {
			challenge = new Challenge(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getInt(4),rs.getInt("version"));
			challengeList.add(challenge);
		}
		
		rs.close();
		
		return challengeList;
		
	}
	
	public static ArrayList<Challenge> findAllOpen() throws SQLException{
		
		ResultSet rs = ChallengeFinder.findAllOpen();
		
		ArrayList<Challenge> challengeList = new ArrayList<Challenge>();
		Challenge challenge = null;
		while(rs.next()){
			challenge = new Challenge(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getInt(4),rs.getInt("version"));
			challengeList.add(challenge);
		}
		
		rs.close();
		
		return challengeList;
	}
	
	public static ArrayList<Challenge> findAll() throws SQLException{
		
		ResultSet rs = ChallengeFinder.findAll();
		
		ArrayList<Challenge> challengeList = new ArrayList<Challenge>();
		Challenge challenge = null;
		while(rs.next()){
			challenge = new Challenge(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getInt(4),rs.getInt("version"));
			challengeList.add(challenge);
		}
		
		rs.close();
		
		return challengeList;
	}
	
}
