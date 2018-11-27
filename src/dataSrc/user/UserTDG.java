package dataSrc.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class UserTDG {
	
	public static int insert(long id, String username, String password) throws SQLException{
		
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

	public static int update(long id, String username, String password, int version) throws SQLException{
		
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
	
	public static int delete(long id, int version) throws SQLException{
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "DELETE FROM users WHERE id=? AND version=?;";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setInt(2, version);
		
		int count = ps.executeUpdate();
		
		ps.close();
		
		return count;
		
	}
}
