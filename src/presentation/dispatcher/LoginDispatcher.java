package presentation.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.command.LoginCommand;

public class LoginDispatcher extends AbstractDispatcher{
	
	public LoginDispatcher(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws ServletException, IOException {
		
		try {
			myRequest.getSession(true).invalidate();
			LoginCommand login = new LoginCommand(myHelper);
			login.process();
			forward("/WEB-INF/jsp/Success.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
			myRequest.setAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/Failure.jsp");


		}	
		
		
		
	}
	
	public void handleGet() throws IOException, ServletException {
		forward("/WEB-INF/jsp/Login.jsp");
	}

}
