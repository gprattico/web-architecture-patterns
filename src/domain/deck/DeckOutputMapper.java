package domain.deck;

import java.sql.SQLException;

import dataSrc.deck.DeckTDG;

public class DeckOutputMapper {

	public static void insert(Deck deck) throws SQLException {

		DeckTDG.insert(deck.getId(), deck.getUserID());
	
	}
	
	public static void update(Deck deck) throws SQLException {
		
		DeckTDG.update(deck.getId(), deck.getUserID());
	
	}
	
	public static void delete(Deck deck) throws SQLException {
		
		DeckTDG.delete(deck.getId());
		
	}
	
	
}
