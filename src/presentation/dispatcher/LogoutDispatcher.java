package presentation.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutDispatcher extends AbstractDispatcher {

	public LogoutDispatcher(HttpServletRequest request, HttpServletResponse response) {

		super.init(request, response);
	}

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			if(checkIfLoggedIn(myRequest)){	
				myRequest.getSession(true).invalidate();
				myRequest.setAttribute("message", "You have been successfully logged out.");
				forward("/WEB-INF/jsp/Failure.jsp");
			}else{
				myRequest.setAttribute("message", "You are not logged in, cannot log out.");
				forward("/WEB-INF/jsp/Failure.jsp");
			}
		
		}catch(Exception e){
			e.printStackTrace();
			myRequest.setAttribute("message", "You are not logged in, cannot log out.");
			forward("/WEB-INF/jsp/Failure.jsp");

		}
	}

	@Override
	public void handleGet() throws IOException, ServletException {
		this.execute();
	}

}
