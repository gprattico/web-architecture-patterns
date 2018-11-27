package domain.user;

import java.sql.SQLException;

import dataSrc.user.UserTDG;

public class UserOutputMapper {

	public static void insert(User user) throws SQLException {

		UserTDG.insert(user.getId(), user.getUsername(), user.getPassword());
	
	}
	
	public static void update(User user) throws SQLException {
		
		UserTDG.update(user.getId(), user.getUsername(), user.getPassword(), user.getVersion());
	
	}
	
	public static void delete(User user) throws SQLException {
		
		UserTDG.delete(user.getId(),user.getVersion());
		
	}
	
}
