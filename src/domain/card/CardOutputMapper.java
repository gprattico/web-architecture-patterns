package domain.card;

import java.sql.SQLException;

import dataSrc.card.CardTDG;

/**
 * Sends/Receives messages from Presentation to TDG
 * Insert
 * Update
 * Delete
 * @author Giovanni
 *
 */
public class CardOutputMapper {

	public static void insert(Card card) throws SQLException {

		CardTDG.insert(card.getId(), card.getDeck(), card.getType(), card.getName(), card.getStatus(), card.getGame());
	
	}
	
	public static void update(Card card) throws SQLException {
		
		CardTDG.update(card.getId(), card.getDeck(), card.getType(), card.getName(), card.getStatus(), card.getGame());
	
	}
	
	public static void delete(Card card) throws SQLException {
		
		CardTDG.delete(card.getId());
	}
	
	
}
