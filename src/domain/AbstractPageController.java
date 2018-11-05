package domain;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.service.MySQLConnectionFactory;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.dsrg.soenea.service.threadLocal.ThreadLocalTracker;

@WebServlet("/AbstractPageController")
public class AbstractPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AbstractPageController() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	startDb();
    }
    
    public static synchronized void startDb() {
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    		String key = "";
    		MySQLConnectionFactory connectionFactory = new MySQLConnectionFactory(null, null, null, null);
    		connectionFactory.defaultInitialization();
    		DbRegistry.setConFactory(key, connectionFactory);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public static void closeDb() {
    	try {
    		DbRegistry.closeDbConnectionIfNeeded();
    		ThreadLocalTracker.purgeThreadLocal();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
