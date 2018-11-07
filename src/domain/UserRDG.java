package domain;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

/*
The use of MaxID to auto increment instead of allowing the db to do this comes from the thesis of stuart thiel
https://users.encs.concordia.ca/~sthiel/soen387/Thesis_Stuart.pdf page 61
*/
public class UserRDG {

	private long id;
	private int version;
	private String username;
	private String password;
	public static long maxID = 0;
	
	public UserRDG(long id, int version, String username, String password){
		
		this.id = id;
		this.version = version;
		this.username = username;
		this.password = password;
		
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}
	
	//method mae synchronized because we dont want users simulataneously getting the same ID
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

	
	public int insert() throws SQLException{
	
		Connection con = DbRegistry.getDbConnection();
		
		//version auto set to 1
		String query = "INSERT INTO users (id, version,username, password) VALUES (?,1,?,?)";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setString(2, username); //set username
		ps.setString(3, password); //set pass
		int dbcount = ps.executeUpdate();//returns 1 if DML, 0 if SQL
		
		ps.close();
		return dbcount;
	}
	
	public int update() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "UPDATE users SET version = version+ 1, username = ?, password= ? WHERE id=? AND version =?;"; 
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setLong(3, id);
		ps.setInt(4, version);
		
		int count = ps.executeUpdate();
		
		ps.close();
		return count;
		
	}
	
	public int delete() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "DELETE FROM users WHERE id=? AND version=?;";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setInt(2, version);
		
		int count = ps.executeUpdate();
		
		ps.close();
		
		return count;
		
		
	}
	
	public static UserRDG find(long id) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT id, version, username, password FROM users WHERE id = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		
		//temp user
		UserRDG user = null;
		if (rs.next()) {
			user = new UserRDG(rs.getLong("id"), rs.getInt("version"), rs.getString("username"), rs.getString("password"));
		}
		
		rs.close();
		ps.close();
		
		return user;
	}
	
	
	//returns a list of UserRDG
	public static UserRDG find(String username) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT id, version, username, password FROM users WHERE username = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<UserRDG> userList = new ArrayList<UserRDG>();
		UserRDG user = null;
		while(rs.next()){
			user = new UserRDG(rs.getLong(1),rs.getInt(2),rs.getString(3),rs.getString(4));
			userList.add(user);
		}
		
		rs.close();
		ps.close();
		
		//because the UML_solution.pdf asks for only one User, we return only the first instance in ArrayList
		if(userList.get(0) != null)
		return userList.get(0);
		else
			return null;
		
		
	}
	
	public static UserRDG find(String username, String password) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT id, version, username, password FROM users WHERE username=? AND password=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		
		//temp user
		UserRDG user = null;
		if (rs.next()) {
			user = new UserRDG(rs.getLong("id"), rs.getInt("version"), rs.getString("username"), rs.getString("password"));
		}
		
		rs.close();
		ps.close();
		
		return user;
		
		
	}
	
	public static ArrayList<UserRDG> findAll() throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "SELECT * FROM users;";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<UserRDG> userList = new ArrayList<UserRDG>();
		UserRDG user = null;
		while(rs.next()){
			user = new UserRDG(rs.getLong(1),rs.getInt(2),rs.getString(3),rs.getString(4));
			userList.add(user);
		}
		
		rs.close();
		ps.close();
		
		return userList;	
	}
	
}
