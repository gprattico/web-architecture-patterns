package dataSrc.card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

/**
 * The TDG architectural pattern contains the insert, update and delete methods.
 * Delete takes only an id.
 * To find records, see the Finder class for this object.
 * @author Giovanni
 *
 */
public class CardTDG {
	
	public static int insert(long id, long deck, String type, String name, int status, long game) throws SQLException {
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "INSERT INTO card VALUES (?,?,?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setLong(2, deck);
		ps.setString(3, type);
		ps.setString(4, name);
		ps.setInt(5, status);
		ps.setLong(6, game);
		
		int count = ps.executeUpdate();
		ps.close();
		
		return count;
	}
	
	public static int update(long id, long deck, String type, String name, int status, long game) throws SQLException {
		
		Connection con = DbRegistry.getDbConnection();
		
		String query = "UPDATE card SET deck= ?, type = ?, name= ?, status=? WHERE id=? AND game=?;"; 
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deck);
		ps.setString(2, type);
		ps.setString(3, name);
		ps.setInt(4, status);
		ps.setLong(5, id);
		ps.setLong(6, game);
		
		int count = ps.executeUpdate();
		
		ps.close();
		return count;
		
	}
	
	public static int delete(long id) throws SQLException {
		
		Connection con = DbRegistry.getDbConnection();
		String query = "DELETE FROM card WHERE id =?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		int count = ps.executeUpdate();
		ps.close();
		return count;
		
	}
	
	
}
