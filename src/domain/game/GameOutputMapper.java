package domain.game;

import java.sql.SQLException;

import dataSrc.game.GameTDG;

public class GameOutputMapper {
	
	public static void insert(Game game) throws SQLException {

		GameTDG.insert(game.getId(), game.getChallengerID(), game.getChallengeeID(), game.getStatus());
	
	}
	
	public static void update(Game game) throws SQLException {
		
		GameTDG.update(game.getId(), game.getChallengerID(), game.getChallengeeID(), game.getStatus());
	
	}
	
	public static void delete(Game game) throws SQLException {
		
		GameTDG.delete(game.getId());
		
	}

}
