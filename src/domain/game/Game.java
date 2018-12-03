package domain.game;

public class Game {

	private long id;
	private long challengerID;
	private long challengeeID;
	private long status;//status 0 if ongoing, 1 challenger wins, 2 challengee wins, 3 game retired
	private int version;
	private long currentTurn;
	private long deckOfChallenger;
	private long deckOfChallengee;
	
	public Game(long id, long challengerID, long challengeeID, long status, int version, long currentTurn, long deckOfChallenger, long deckOfChallengee) {
		super();
		this.id = id;
		this.challengerID = challengerID;
		this.challengeeID = challengeeID;
		this.status = status;
		this.version = version;
		this.currentTurn = currentTurn;
		this.deckOfChallenger = deckOfChallenger;
		this.deckOfChallengee = deckOfChallengee;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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
	
	public long getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(long currentTurn) {
		this.currentTurn = currentTurn;
	}

	public long getDeckOfChallenger() {
		return deckOfChallenger;
	}

	public void setDeckOfChallenger(long deckOfChallenger) {
		this.deckOfChallenger = deckOfChallenger;
	}

	public long getDeckOfChallengee() {
		return deckOfChallengee;
	}

	public void setDeckOfChallengee(long deckOfChallengee) {
		this.deckOfChallengee = deckOfChallengee;
	}
}
