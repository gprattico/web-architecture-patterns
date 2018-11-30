package presentation;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.application.servlet.Servlet;
import org.dsrg.soenea.service.MySQLConnectionFactory;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

import presentation.dispatcher.AbstractDispatcher;
import presentation.dispatcher.ListPlayersDispatcher;
import presentation.dispatcher.LoginDispatcher;
import presentation.dispatcher.LogoutDispatcher;
import presentation.dispatcher.RegisterDispatcher;
import presentation.dispatcher.ViewDeckDispatcher;

/**
 * Servlet implementation class FrontController
 * Uses SOENEA
 * Extends servlet class, inheriting the processRequest method
 */
@WebServlet("/")
public class FrontController extends Servlet {

	private static final long serialVersionUID = 1L;
       
    public FrontController() {
        super();
    }
    
    //this method called by Tomcat before servlet can accept requests
    public void init() throws ServletException {
    	
    	//start the Db
    	startDb();
    	//add UoW
    }

	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		
		//https://tomcat.apache.org/tomcat-5.5-doc/servletapi/javax/servlet/http/HttpServletRequest.html#getServletPath()
		//cannot use /* with this or else returns blank string
		try {
			
		//creates a threadLocal for arriving users
		super.preProcessRequest(request, response);
		
		String URL = request.getServletPath();
		
		AbstractDispatcher dispatcher = ProcessDispatcher(request, response, URL);

		if(request.getMethod().equals("GET")) {
			dispatcher.handleGet();
		}else {
		dispatcher.execute();
		}
		
		//close the db and purge the threadlocal
		super.postProcessRequest(request, response);
		
		}catch(Exception e) {
			//no URL
			request.setAttribute("message","The URL you provided does not exist");
			request.getRequestDispatcher("/WEB-INF/jsp/Failure.jsp").forward(request, response);
			
			//close the db in case we catch an exception im not aware of
			super.postProcessRequest(request, response);
		}
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
    
    public AbstractDispatcher ProcessDispatcher(HttpServletRequest request, HttpServletResponse response, String URL) {
    	
    	AbstractDispatcher dispatcher = null;
    	
    	String numberAfterSlash = "//d+";
    	Pattern numberCompiled = Pattern.compile(numberAfterSlash);
    	Matcher matcher = numberCompiled.matcher(URL);
    	
    	//matcher.matches()
    	
    	if(URL.equals("/Poke/Player/Register")) {
    		dispatcher = new RegisterDispatcher(request, response);
    	} else if(URL.equals("/Poke/Player/Login")) {
    		dispatcher = new LoginDispatcher(request,response);
    	} else if(URL.equals("/Poke/Player/Logout")) {
    		dispatcher = new LogoutDispatcher(request,response);
    	} else if(URL.equals("/Poke/Player")) {
    		dispatcher = new ListPlayersDispatcher(request, response);
    	} else if(URL.equals("/Poke/Deck/"+"\\d+")) {
    		dispatcher = new ViewDeckDispatcher(request, response);//view a single deck
    	}
    	
    	return dispatcher;
    	
    }

}
