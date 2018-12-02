package presentation.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.command.ViewBoardCommand;

public class ViewBoardDispatcher extends AbstractDispatcher {

	public ViewBoardDispatcher(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws ServletException, IOException {
		this.handleGet();
	}

	@Override
	public void handleGet() throws IOException, ServletException {

		try{
			//if(checkIfLoggedIn(myRequest)) {
			String[] requestPath = myRequest.getServletPath().trim().split("/");
			
			long gameID = Long.parseLong(requestPath[requestPath.length-1]);
			
			myHelper.setRequestAttribute("gameID", gameID);
			
			//myHelper.setRequestAttribute("gameID", Long.parseLong(requestPath[requestPath.length-1]));

			System.out.println("game id in dispatcher is: "+gameID);
			
			ViewBoardCommand viewBoard = new ViewBoardCommand(myHelper);
			viewBoard.process();
			forward("/WEB-INF/jsp/ViewBoard.jsp");
			
//			}else{
//				myRequest.setAttribute("message", "Challenge failed. You either don't have a deck, aren't logged in or tried challenging yourself!");
//				forward("/WEB-INF/jsp/Failure.jsp");
//			}
		
		
		}catch(Exception e){
			e.printStackTrace();
			myRequest.setAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/Failure.jsp");
		
		}
	}

}
