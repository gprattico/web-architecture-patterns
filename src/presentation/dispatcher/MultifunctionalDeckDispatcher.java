package presentation.dispatcher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.command.UploadDeckCommand;
import domain.command.ViewDecksCommand;

public class MultifunctionalDeckDispatcher extends AbstractDispatcher {

	public MultifunctionalDeckDispatcher(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	//handle POST (upload)
	@Override
	public void execute() throws ServletException, IOException {

		try {
		if(checkIfLoggedIn(myRequest)) {
			
			UploadDeckCommand uploadDeck = new UploadDeckCommand(myHelper);
			uploadDeck.process();
			forward("/WEB-INF/jsp/Success.jsp");
			
			
		}else {
			myRequest.setAttribute("message", "Not logged in.");
			forward("/WEB-INF/jsp/Failure.jsp");
		}
		}catch(Exception e) {
			e.printStackTrace();
			myRequest.setAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/Failure.jsp");
		}
		
		
	}

	//handle GET ViewDecks and Get Deck Form
	@Override
	public void handleGet() throws IOException, ServletException {
		//for now just going to do viewDecks
		
		try {
		if(checkIfLoggedIn(myRequest)) {
			
			ViewDecksCommand viewDecks = new ViewDecksCommand(myHelper);
			viewDecks.process();
			forward("/WEB-INF/jsp/ViewDecks.jsp");
			
			
		}else {
			myRequest.setAttribute("message", "Not logged in.");
			forward("/WEB-INF/jsp/Failure.jsp");
		}
		}catch(Exception e) {
			e.printStackTrace();
			myRequest.setAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/Failure.jsp");
		}
	}

}
