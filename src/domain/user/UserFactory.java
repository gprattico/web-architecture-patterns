package domain.user;

import java.sql.SQLException;

public class UserFactory {

	public static User createClean(long id, int version, String username, String password) throws SQLException {
		
		User user = new User(id, version, username, password);
		return user;
		
	}
	
	public static User createNew (String username, String password) throws SQLException {
		
		User user = new User(UserInputMapper.getMaxID(), 1, username, password);
		return user;
	}
	

}
