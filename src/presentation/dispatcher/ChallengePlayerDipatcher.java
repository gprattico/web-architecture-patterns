package presentation.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.command.ChallengePlayerCommand;
import domain.deck.DeckInputMapper;

public class ChallengePlayerDipatcher extends AbstractDispatcher {

	public ChallengePlayerDipatcher(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			String[] requestPath = myRequest.getServletPath().trim().split("/");

			if(checkIfLoggedIn(myRequest)&&hasDeck(myRequest)&&(Long.parseLong(requestPath[requestPath.length-2])!=CurrentSession(myRequest))){
				
//				String[] requestPath = myRequest.getServletPath().trim().split("/");
				Long playerID = Long.parseLong(requestPath[requestPath.length-2]);
				myHelper.setRequestAttribute("playerID", playerID);
				long deckUsedToChallenge = Long.parseLong(myRequest.getParameter("deck"));
				
				System.out.println(deckUsedToChallenge);
				//check if deck exists
				if(DeckInputMapper.find(deckUsedToChallenge) == null) {
					throw new Exception("Deck id does not exist");
				}
				
				myHelper.setRequestAttribute("deckUsedToChallenge", deckUsedToChallenge);
				
				ChallengePlayerCommand challengePlayer = new ChallengePlayerCommand(myHelper);
				challengePlayer.process();
				forward("/WEB-INF/jsp/Success.jsp");
			}else{
				myRequest.setAttribute("message", "Challenge failed. You either don't have a deck, aren't logged in or tried challenging yourself!");
				forward("/WEB-INF/jsp/Failure.jsp");
			}
		
		
		}catch(Exception e){
			e.printStackTrace();
			myRequest.setAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/Failure.jsp");
		
		}
	}

	@Override
	public void handleGet() throws IOException, ServletException {
		// TODO Auto-generated method stub
		this.execute();
	}

}
