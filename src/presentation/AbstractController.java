package presentation;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.service.MySQLConnectionFactory;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.dsrg.soenea.service.threadLocal.ThreadLocalTracker;

import dataSrc.DeckRDG;
import dataSrc.GameRDG;

/*
 * Useful when extended, child servlet calls this init and loads the db. StartDb() and CloseDB(), as well as some other
 * methods such as the connection factories are taken from masters thesis of Stuart Thiel, available at:
 * https://users.encs.concordia.ca/~sthiel/soen387/Thesis_Stuart.pdf  
*/
@WebServlet("/AbstractController")
public class AbstractController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AbstractController() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	
    	startDb();
    	
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected boolean checkIfLoggedIn(HttpServletRequest request){
	
		try{
		if(request.getSession(true).getAttribute("id")==null)
				return false;
		else
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	protected boolean hasDeck(HttpServletRequest request) {
		
		try {
			DeckRDG rdg = DeckRDG.findByUserID((long)request.getSession(true).getAttribute("id"));
			if(rdg == null)
				return false;
			else
				return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	protected boolean IsInGame(HttpServletRequest request) {
		
		try {
			GameRDG rdg = GameRDG.find((long)request.getSession(true).getAttribute("id"));
			if(rdg == null)
				return false;
			else
				return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	

}
