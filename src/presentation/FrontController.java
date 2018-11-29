package presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.application.servlet.Servlet;
import org.dsrg.soenea.application.servlet.impl.SmartDispatcherServlet;
import org.dsrg.soenea.service.MySQLConnectionFactory;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.dsrg.soenea.service.threadLocal.ThreadLocalTracker;

import presentation.dispatcher.AbstractDispatcher;
import presentation.dispatcher.LoginDispatcher;

/**
 * Servlet implementation class FrontController
 * Uses SOENEA
 * Extends servlet class, inheriting the processRequest method
 */
@WebServlet("/FrontController")
public class FrontController extends Servlet {

	private static final long serialVersionUID = 1L;
       
    public FrontController() {
        super();
    }
    
    
    public void init() throws ServletException {
    	
    	startDb();
    	//add UoW
    }

	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		
		//https://tomcat.apache.org/tomcat-5.5-doc/servletapi/javax/servlet/http/HttpServletRequest.html#getServletPath()
		//cannot use /* with this or else returns blank string
		String URL = request.getServletPath();
		
		AbstractDispatcher dispatcher = this.getDispatcher(URL);
		
		dispatcher.init(request, response);
		dispatcher.execute();
		
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
    
    public AbstractDispatcher getDispatcher(String URL) {
    	
    	AbstractDispatcher dispatcher = null;
    	
    	if(URL.equals("/Poke/Player/Register")) {
    		dispatcher = new LoginDispatcher();
    	}
    	
    	return dispatcher;
    	
    }

}