package domain;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class UserRDG {

	private long id;
	private int version;
	private String username;
	private String password;
	
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

	
	
	public static UserRDG find(long id) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		
		PreparedStatement ps = con.prepareStatement("SELECT id, version, username, password FROM users WHERE id = ?;");
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		
		UserRDG user = null;
		if (rs.next()) {
			user = new UserRDG(rs.getLong("id"), rs.getInt("version"), rs.getString("username"), rs.getString("password"));
		}
		
		rs.close();
		ps.close();
		
		return user;
	}
	
	
	
}
