package presentation.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.command.ListPlayersCommand;

public class ListPlayersDispatcher extends AbstractDispatcher {

	public ListPlayersDispatcher(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated constructor stub
		super.init(request, response);
	}

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		handleGet();
	}

	@Override
	public void handleGet() throws IOException, ServletException {
		// TODO Auto-generated method stub
		try {
		if(checkIfLoggedIn(myRequest)){
			
			ListPlayersCommand listPlayers = new ListPlayersCommand(myHelper);
			listPlayers.process();	
			forward("/WEB-INF/jsp/ListPlayers.jsp");
			
		}else{
			myRequest.setAttribute("message", "You are not logged in.");
			forward("/WEB-INF/jsp/Failure.jsp");
		}
		}catch(Exception e){
		
		}
	}

}
