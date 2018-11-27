package dataSrc.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class UserFinder {
	
	public static long maxID = 0;

	public static synchronized long getMaxID() throws SQLException{
		
		//need the if 0 or else can't insert more than one at a time
		if (maxID == 0) {
			Connection con = DbRegistry.getDbConnection();
			String query = "Select MAX(id) as id FROM users;";
			
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				maxID = rs.getLong("id");
			
			ps.close();
			rs.close();
		}
		
		return ++maxID;
	}
	
	public static ResultSet find(long id) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT id, version, username, password FROM users WHERE id = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
//		
//		//temp user
//		UserRDG user = null;
//		if (rs.next()) {
//			user = new UserRDG(rs.getLong("id"), rs.getInt("version"), rs.getString("username"), rs.getString("password"));
//		}
//		
//		rs.close();
//		ps.close();
		
		return rs;
	}
	
	public static ResultSet find(String username) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT id, version, username, password FROM users WHERE username = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		
//		ArrayList<UserRDG> userList = new ArrayList<UserRDG>();
//		UserRDG user = null;
//		while(rs.next()){
//			user = new UserRDG(rs.getLong(1),rs.getInt(2),rs.getString(3),rs.getString(4));
//			userList.add(user);
//		}
//		
//		rs.close();
//		ps.close();
//		
//		//because the UML_solution.pdf asks for only one User, we return only the first instance in ArrayList
//		if(userList.size() ==0)
//			return null;
//		else
//			return userList.get(0);
		return rs;
		
	}
	
	public static ResultSet find(String username, String password) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT id, version, username, password FROM users WHERE username=? AND password=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		
//		//temp user
//		UserRDG user = null;
//		if (rs.next()) {
//			user = new UserRDG(rs.getLong("id"), rs.getInt("version"), rs.getString("username"), rs.getString("password"));
//		}
//		
//		rs.close();
//		ps.close();
//		
//		return user;
		
		return rs;
		
	}
	
	public static ResultSet findAll() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM users;";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
//		ArrayList<UserRDG> userList = new ArrayList<UserRDG>();
//		UserRDG user = null;
//		while(rs.next()){
//			user = new UserRDG(rs.getLong(1),rs.getInt(2),rs.getString(3),rs.getString(4));
//			userList.add(user);
//		}
//		
//		rs.close();
//		ps.close();
//		
//		return userList;
		return rs;
	}
	
}
