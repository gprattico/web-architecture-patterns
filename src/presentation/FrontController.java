package presentation;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.application.servlet.Servlet;
import org.dsrg.soenea.service.MySQLConnectionFactory;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

import presentation.dispatcher.AbstractDispatcher;
import presentation.dispatcher.AcceptChallengeDispatcher;
import presentation.dispatcher.ChallengeDispatcher;
import presentation.dispatcher.ChallengePlayerDipatcher;
import presentation.dispatcher.ListGamesDispatcher;
import presentation.dispatcher.ListPlayersDispatcher;
import presentation.dispatcher.LoginDispatcher;
import presentation.dispatcher.LogoutDispatcher;
import presentation.dispatcher.MultifunctionalDeckDispatcher;
import presentation.dispatcher.RefuseChallengeDispatcher;
import presentation.dispatcher.RegisterDispatcher;
import presentation.dispatcher.ViewBoardDispatcher;
import presentation.dispatcher.ViewDeckDispatcher;
import presentation.dispatcher.WithdrawChallengeDispatcher;

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
		
		AbstractDispatcher dispatcher = ProcessDispatcher(request, response);

		if(request.getMethod().equals("GET")) {
			dispatcher.handleGet();
		}else {
		dispatcher.execute();
		}
		
		//close the db and purge the threadlocal
		super.postProcessRequest(request, response);
		
		}catch(Exception e) {
			//no URL
			e.printStackTrace();
			request.setAttribute("message","The URL you provided does not exist." + e.getMessage());
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
    
    public AbstractDispatcher ProcessDispatcher(HttpServletRequest request, HttpServletResponse response) {
    	
    	String URL = request.getServletPath();
    	AbstractDispatcher dispatcher = null;
    	
    	final String viewDeckURL = "/Poke/Deck";
    	final String challengePlayerURL = "/Poke/Player";
    	
    	if(URL.equals("/Poke/Player/Register")) {
    		dispatcher = new RegisterDispatcher(request, response);
    	} else if(URL.equals("/Poke/Player/Login")) {
    		dispatcher = new LoginDispatcher(request,response);
    	} else if(URL.equals("/Poke/Player/Logout")) {
    		dispatcher = new LogoutDispatcher(request,response);
    	} else if(URL.equals("/Poke/Player")) {
    		dispatcher = new ListPlayersDispatcher(request, response);
    	} else if(Pattern.compile(viewDeckURL+"/\\d+").matcher(URL).matches()) {
    		dispatcher = new ViewDeckDispatcher(request, response);//view a single deck
    	} else if (URL.equals("/Poke/Deck")) {
    		dispatcher = new MultifunctionalDeckDispatcher(request, response);
    	} else if (URL.equals("/Poke/Challenge")) {
    		dispatcher = new ChallengeDispatcher(request,response);
    	} else if (Pattern.compile(challengePlayerURL+"/\\d+"+"/Challenge").matcher(URL).matches()) {
    		dispatcher = new ChallengePlayerDipatcher(request,response);
    	} else if(Pattern.compile("/Poke/Challenge"+"/\\d+"+"/Accept").matcher(URL).matches()) {
    		dispatcher = new AcceptChallengeDispatcher(request,response);
    	} else if(Pattern.compile("/Poke/Challenge"+"/\\d+"+"/Refuse").matcher(URL).matches()) {
    		dispatcher = new RefuseChallengeDispatcher(request,response);
    	} else if (Pattern.compile("/Poke/Challenge"+"/\\d+"+"/Withdraw").matcher(URL).matches()) {
    		dispatcher = new WithdrawChallengeDispatcher(request,response);
    	} else if(Pattern.compile("/Poke/Game"+"/\\d+").matcher(URL).matches()) {
    		dispatcher = new ViewBoardDispatcher(request,response); 	
    	} else if(URL.equals("/Poke/Game")) {
    		dispatcher = new ListGamesDispatcher(request,response);
    	}
    	return dispatcher;
    	//TestSuite.URL_BASE+"Poke/Challenge/" + challenge + "/Withdraw"), HttpMethod.POST)
    }

}
