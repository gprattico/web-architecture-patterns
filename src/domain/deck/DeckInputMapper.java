package domain.deck;

import java.sql.ResultSet;
import java.sql.SQLException;

import dataSrc.deck.DeckFinder;

public class DeckInputMapper {
	
	public static long getMaxDeckID() throws SQLException {
		
		return DeckFinder.getMaxDeckID();
	}
	
	public static Deck find(long id) throws SQLException {
		
		ResultSet rs = DeckFinder.find(id);

		Deck deck = null;
		if (rs.next()) {
			deck = new Deck(rs.getLong("id"), rs.getLong("userID"));
		}
		
		rs.close();
		
		return deck;
		
	}
	
	public static Deck findByUserID(long id) throws SQLException{
		
		ResultSet rs = DeckFinder.findByUserID(id);
		
		Deck deck = null;
		if (rs.next()) {
			deck = new Deck(rs.getLong("id"), rs.getLong("userID"));
		}
		
		rs.close();

		return deck;
	}
}
