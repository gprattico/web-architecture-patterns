package presentation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.service.MySQLConnectionFactory;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.dsrg.soenea.service.threadLocal.ThreadLocalTracker;

/**
 * When we access this path, we truncate the tables.
 * Removes foreign keys.
 * The SET_FOREIGN_KEYS to truncate the tables is taken from stack overflow
 */
@WebServlet("/TableManager")
public class TableManager extends FrontController{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableManager() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		startDb();
		// TODO Auto-generated method stub
		response.getWriter().append("Truncating tables ").append(request.getContextPath());
		try {
			//key check to 0
			Connection con = DbRegistry.getDbConnection();
			String query = "SET FOREIGN_KEY_CHECKS = 0;"; 
			PreparedStatement ps = con.prepareStatement(query);
			int rs = ps.executeUpdate();
			
			String query2="truncate table users;";
			ps = con.prepareStatement(query2);
			rs = ps.executeUpdate();
			
			String query3="truncate table deck;";
			ps = con.prepareStatement(query3);
			rs = ps.executeUpdate();
			
			String query4="truncate table challenge;";
			ps = con.prepareStatement(query4);
			rs = ps.executeUpdate();
			
			String query5="truncate table card;";
			ps =  con.prepareStatement(query5);
			rs = ps.executeUpdate();
			
			String query7="truncate table game;";
			ps = con.prepareStatement(query7);
			rs = ps.executeUpdate();
			
			String query6 = "SET FOREIGN_KEY_CHECKS = 1;"; 
			ps = con.prepareStatement(query6);
			rs = ps.executeUpdate();
			
			ps.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDb();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static synchronized void startDb() {
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    		
    		String blank = "";
    		
    		MySQLConnectionFactory connection = new MySQLConnectionFactory(null, null, null, null);
    		
    		connection.defaultInitialization();
    		
    		DbRegistry.setConFactory(blank, connection);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
	}
	 public static void closeDb() {
	    	try {
	    		//thesis uses both of these methods, no idea why
	    		DbRegistry.getDbConnection().close();
	    		DbRegistry.closeDbConnectionIfNeeded();
	    		}
	    	catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    	ThreadLocalTracker.purgeThreadLocal();
	}
	
	

}
