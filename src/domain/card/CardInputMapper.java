package domain.card;

import java.sql.SQLException;
import java.util.ArrayList;

import dataSrc.card.CardFinder;

public class CardInputMapper {

	public static ArrayList<Card> findAll(long id) throws SQLException{
		
		return CardFinder.findAll(id);
		
	}
	
	public static ArrayList<Card> findAllInHand(long id) throws SQLException {
		
		return CardFinder.findAllInHand(id);
	}
	
	public static ArrayList<Card> findAllBenched(long id) throws SQLException{
		
		
		return CardFinder.findAllBenched(id);
	}
	
	public static long getMaxCardID() throws SQLException {
		
		return CardFinder.getMaxCardID();
	
	}
	
}
