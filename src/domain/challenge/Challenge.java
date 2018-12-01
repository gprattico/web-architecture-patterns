package domain.challenge;

import java.sql.SQLException;

import domain.deck.DeckInputMapper;
import domain.user.User;
import domain.user.UserInputMapper;

public class Challenge {
	
	long id;
	long challenger;
	long challengee;
	int status;
	int version;
	long deckOfChallenger;
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Challenge(long id, long challenger, long challengee, int status, int version, long deckOfChallenger) {
		super();
		this.id = id;
		this.challenger = challenger;
		this.challengee = challengee;
		this.status = status;
		this.version = version;
		this.deckOfChallenger=deckOfChallenger;
	}

	public long getDeckOfChallenger() {
		return deckOfChallenger;
	}

	public void setDeckOfChallenger(long deckOfChallenger) {
		this.deckOfChallenger = deckOfChallenger;
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
	
	//returns the username of the challenger in this challenge
	public String findChallengerUsername() throws SQLException{
			User user = UserInputMapper.find(challenger);
			
			String temp="";
			temp = user.getUsername();	
			
			return temp;
	}
	
	public long getChallengerDeckID() throws SQLException {
		
		return DeckInputMapper.find(this.challenger).getId();
		
	}
	
}
