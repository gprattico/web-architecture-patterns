package presentation.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.command.ListChallengeCommand;

public class ChallengeDispatcher extends AbstractDispatcher {

	public ChallengeDispatcher(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleGet() throws IOException, ServletException {
		// TODO Auto-generated method stub
		try{
			
		if(checkIfLoggedIn(myRequest)){
			
				ListChallengeCommand listCommand = new ListChallengeCommand(myHelper);
				listCommand.process();
				forward("/WEB-INF/jsp/ListChallenges.jsp");
				
			}else{
				myRequest.setAttribute("message", "You are not logged in.");
				forward("WEB-INF/jsp/Failure.jsp");
			}
		}catch(Exception e){
			e.printStackTrace();
			myRequest.setAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/Failure.jsp");
			
		}

	}

}
