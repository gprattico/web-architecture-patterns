package domain.challenge;

import java.sql.SQLException;

import dataSrc.challenge.ChallengeTDG;

public class ChallengeOutputMapper {

	public static void insert(Challenge challenge) throws SQLException {
		
		ChallengeTDG.insert(challenge.getId(), challenge.getChallenger(), challenge.getChallengee(), challenge.getStatus());
		
	}
	
	public static void update(Challenge challenge) throws SQLException{
		
		ChallengeTDG.update(challenge.getChallenger(), challenge.getChallengee(), challenge.getStatus(), challenge.getId(), challenge.getVersion());
	}
	
	public static void delete(Challenge challenge) throws SQLException{
		
		ChallengeTDG.delete(challenge.getId());
		
	}
}
