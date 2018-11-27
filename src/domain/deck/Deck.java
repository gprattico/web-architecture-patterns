package domain.deck;

public class Deck {

	private long id;
	private long userID;
	
	public Deck(long id, long userID) {
		super();
		this.id = id;
		this.userID = userID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public long getId() {
		return id;
	}
	
}
