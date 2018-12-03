package domain.card;

public class Card {

	private long id;
	private long deck;
	private String type;
	private String name;
	private int status;
	private long game;


	public Card(long id, long deck, String type, String name, int status, long game) {
		super();
		this.id = id;
		this.deck = deck;
		this.type = type;
		this.name = name;
		this.status = status;
		this.game = game;
	}

	
	public long getDeck() {
		return deck;
	}

	public void setDeck(long deck) {
		this.deck = deck;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public long getGame() {
		return game;
	}

	public void setGame(long l) {
		this.game = l;
	}
	
}
