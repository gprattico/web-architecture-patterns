package domain.card;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataSrc.card.CardFinder;

public class CardInputMapper {

	public static ArrayList<Card> findAll(long id) throws SQLException{
		
		ResultSet rs = CardFinder.findAll(id);
		
		ArrayList<Card> cardList = new ArrayList<Card>(); 
		while (rs.next()) {
			cardList.add(new Card(rs.getInt("id"), rs.getInt("deck"), rs.getString("type"), rs.getString("name"),rs.getInt("status")));
		}
		
		rs.close();
		
		return cardList;
		
	}
	
	public static ArrayList<Card> findAllInHand(long id) throws SQLException {
		
		ResultSet rs = CardFinder.findAllInHand(id);
		
		ArrayList<Card> cardList = new ArrayList<Card>(); 
		while (rs.next()) {
			cardList.add(new Card(rs.getInt("id"), rs.getInt("deck"), rs.getString("type"), rs.getString("name"),rs.getInt("status")));
		}
		
		rs.close();
		
		return cardList;
	}
	
	public static ArrayList<Card> findAllBenched(long id) throws SQLException{
		
		ResultSet rs = CardFinder.findAllBenched(id);
		
		ArrayList<Card> cardList = new ArrayList<Card>(); 
		while (rs.next()) {
			cardList.add(new Card(rs.getInt("id"), rs.getInt("deck"), rs.getString("type"), rs.getString("name"),rs.getInt("status")));
		}
		
		rs.close();
		
		return cardList;
		
	}
	
	public static long getMaxCardID() throws SQLException {
		
		return CardFinder.getMaxCardID();
	
	}
	
}
