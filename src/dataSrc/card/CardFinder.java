package dataSrc.card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

import domain.Card;

public class CardFinder {
	
	
	public static ArrayList<Card> findAll(long Deckid) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM card WHERE deck = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, Deckid);
		ResultSet rs = ps.executeQuery();
		
		//temp user
		ArrayList<Card> cardList = new ArrayList<Card>(); 
		while (rs.next()) {
			cardList.add(new Card(rs.getInt("id"), rs.getInt("deck"), rs.getString("type"), rs.getString("name"),rs.getInt("status")));
		}
		
		rs.close();
		ps.close();
		
		return cardList;
	}
	
	
	public static ArrayList<Card> findAllInHand(long deckID) throws SQLException {

		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM card WHERE deck = ? AND status = 1;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckID);
		ResultSet rs = ps.executeQuery();
		
		//temp user
		ArrayList<Card> cardList = new ArrayList<Card>(); 
		while (rs.next()) {
			cardList.add(new Card(rs.getInt("id"), rs.getInt("deck"), rs.getString("type"), rs.getString("name"),rs.getInt("status")));
		}
		
		rs.close();
		ps.close();
		
		return cardList;
	}
	
	public static ArrayList<Card> findAllBenched(long deckID) throws SQLException {

		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM card WHERE deck = ? AND status = 3;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckID);
		ResultSet rs = ps.executeQuery();
		
		//temp user
		ArrayList<Card> cardList = new ArrayList<Card>(); 
		while (rs.next()) {
			cardList.add(new Card(rs.getInt("id"), rs.getInt("deck"), rs.getString("type"), rs.getString("name"),rs.getInt("status")));
		}
		
		rs.close();
		ps.close();
		
		return cardList;
		
	}

}
