package domain.game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataSrc.game.GameFinder;

public class GameInputMapper {
	
	public static ArrayList<Game> findAll() throws SQLException{
		
		ResultSet rs = GameFinder.findAll();
		
		ArrayList<Game> gameList = new ArrayList<Game>();
		Game game = null;
		while(rs.next()){
			game = new Game(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4));
			gameList.add(game);
		}
		
		rs.close();
		
		return gameList;
	}
	
	public static Game find(long attribute) throws SQLException {
		
		ResultSet rs = GameFinder.find(attribute);
		
		Game game =null;
		if(rs.next()) {
			game = new Game(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4));
		}
		
		rs.close();
		
		return game;
	}

}
