package domain.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataSrc.user.UserFinder;

public class UserInputMapper {
	
	public static User find(long id) throws SQLException {
		
		ResultSet rs = UserFinder.find(id);

		User user = null;
		if (rs.next()) {
			user = new User(rs.getLong("id"), rs.getInt("version"), rs.getString("username"), rs.getString("password"));
		}
		
		rs.close();
		
		return user;
		
	}
	
	public static User find(String username) throws SQLException{
		
		ResultSet rs = UserFinder.find(username);
		
		ArrayList<User> userList = new ArrayList<User>();
		User user = null;
		while(rs.next()){
			user = new User(rs.getLong(1),rs.getInt(2),rs.getString(3),rs.getString(4));
			userList.add(user);
		}
		
		rs.close();

//		//because the UML_solution.pdf asks for only one User, we return only the first instance in ArrayList
		if(userList.size() ==0)
			return null;
		else
			return userList.get(0);

	}
	
	public static User find(String username, String password) throws SQLException{
		
		ResultSet rs = UserFinder.find(username, password);
		
		User user = null;
		if (rs.next()) {
			user = new User(rs.getLong("id"), rs.getInt("version"), rs.getString("username"), rs.getString("password"));
		}
		
		rs.close();
		
		return user;
		
	}
	
	public static ArrayList<User> findAll() throws SQLException {
		
		ResultSet rs = UserFinder.findAll();
		
		ArrayList<User> userList = new ArrayList<User>();
		User user = null;
		while(rs.next()){
			user = new User(rs.getLong(1),rs.getInt(2),rs.getString(3),rs.getString(4));
			userList.add(user);
		}

		rs.close();

		return userList;
		
		
	}

}
