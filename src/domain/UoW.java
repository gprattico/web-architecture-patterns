package domain;

import java.sql.SQLException;
import java.util.ArrayList;

import domain.card.Card;
import domain.challenge.Challenge;
import domain.challenge.ChallengeOutputMapper;
import domain.deck.Deck;
import domain.game.Game;
import domain.user.User;
import domain.user.UserOutputMapper;

/**
 * Exemplary hard coded Uow
 * The hard coded lists can be changed for mapper factories, whereby we dynamically create an object list and assign a register
 * new and register dirty method to it
 * @author Giovanni
 *
 */
public class UoW {
	
	static ArrayList<User> userListNew= new ArrayList<User>();
	static ArrayList<User> userListDirty= new ArrayList<User>();
	static ArrayList<Challenge> challengeListNew= new ArrayList<Challenge>();
	static ArrayList<Challenge> challengeListDirty= new ArrayList<Challenge>();
	static ArrayList<Game> gameList= null;
	static ArrayList<Deck> deckList= null;
	static ArrayList<Card> cardList= null;
	
	public static void registerNew (Object obj) {
		if(obj.getClass().equals(User.class)) {
			userListNew.add((User) obj);
		}
		
		if(obj.getClass().equals(Challenge.class)) {
			challengeListNew.add((Challenge) obj);
		}

		
		
	}
	
	public static void registerDirty(Object obj) {
		if(obj.getClass().equals(User.class)) {
			userListDirty.add((User) obj);
		}
		
		if(obj.getClass().equals(Challenge.class)) {
			challengeListDirty.add((Challenge) obj);
		}
	
	}
	
	public static void commit() throws SQLException {
		for(User iterator : userListNew)
		UserOutputMapper.insert(iterator);
		
		for(User iterator : userListDirty)
		UserOutputMapper.update(iterator);
		
		for(Challenge iterator:challengeListNew)
		ChallengeOutputMapper.insert(iterator);
		
		for(Challenge iterator:challengeListDirty)
		ChallengeOutputMapper.update(iterator);
		
	}
	
	
	
	

}
