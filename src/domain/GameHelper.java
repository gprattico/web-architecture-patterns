package domain;

public class GameHelper {

	private long id;
	private long challengerID;
	private long challengeeID;
	private long status;
	
	public GameHelper(long id, long challengerID, long challengeeID, long status) {
		super();
		this.id = id;
		this.challengerID = challengerID;
		this.challengeeID = challengeeID;
		this.status = status;
	}

	public long getChallengerID() {
		return challengerID;
	}

	public void setChallengerID(long challengerID) {
		this.challengerID = challengerID;
	}

	public long getChallengeeID() {
		return challengeeID;
	}

	public void setChallengeeID(long challengeeID) {
		this.challengeeID = challengeeID;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}
	
	
	
}
