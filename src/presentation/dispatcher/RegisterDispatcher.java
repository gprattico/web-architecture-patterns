package presentation.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.command.RegisterCommand;

public class RegisterDispatcher extends AbstractDispatcher {

	public RegisterDispatcher(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			myRequest.getSession(true).invalidate();
			RegisterCommand register = new RegisterCommand(myHelper);
			register.process();
			forward("/WEB-INF/jsp/Success.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
			myRequest.setAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/Failure.jsp");


		}	
	}

	@Override
	public void handleGet() throws IOException, ServletException {
		forward("/WEB-INF/jsp/Register.jsp");
	}

}
