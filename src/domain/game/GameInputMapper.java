package domain.game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataSrc.game.GameFinder;

public class GameInputMapper {
	
	
	public static long getMaxGameID() throws SQLException {
		
		return GameFinder.getMaxGameID();
	}
	
	public static ArrayList<Game> findAll() throws SQLException{
		
		ResultSet rs = GameFinder.findAll();
		
		ArrayList<Game> gameList = new ArrayList<Game>();
		Game game = null;
		while(rs.next()){
			game = new Game(rs.getLong("id"),rs.getLong("challengerID"),rs.getLong("challengeeID"),rs.getLong("status"), rs.getInt("version"),rs.getLong("currentTurn"), rs.getLong("deckOfChallenger"), rs.getLong("deckOfChallengee"));
			gameList.add(game);
		}
		
		rs.close();
		
		return gameList;
	}
	
	public static Game find(long attribute) throws SQLException {
		
		ResultSet rs = GameFinder.find(attribute);
		
		Game game =null;
		if(rs.next()) {
			game = new Game(rs.getLong("id"),rs.getLong("challengerID"),rs.getLong("challengeeID"),rs.getLong("status"), rs.getInt("version"),rs.getLong("currentTurn"), rs.getLong("deckOfChallenger"), rs.getLong("deckOfChallengee"));
		}
		
		rs.close();
		
		return game;
	}

}
