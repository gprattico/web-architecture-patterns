package presentation.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.command.ViewDeckCommand;

public class ViewDeckDispatcher extends AbstractDispatcher {

	public ViewDeckDispatcher(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.handleGet();
	}

	@Override
	public void handleGet() throws IOException, ServletException {
		// TODO Auto-generated method stub
		try {
			if(checkIfLoggedIn(myRequest)) {
				
				String[] requestPath = myRequest.getServletPath().trim().split("/");
				int deckID = Integer.parseInt(requestPath[requestPath.length-1]);
				myHelper.setRequestAttribute("deckID", deckID);
				
				ViewDeckCommand viewDeck = new ViewDeckCommand(myHelper);
				viewDeck.process();
				forward("/WEB-INF/jsp/ViewDeck.jsp");
				
			}else {
//				throw new NotLoggedInExc
				myRequest.setAttribute("message", "Not Logged In");
				forward("/WEB-INF/jsp/Failure.jsp");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			myRequest.setAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/Failure.jsp");
		}
	}

}
