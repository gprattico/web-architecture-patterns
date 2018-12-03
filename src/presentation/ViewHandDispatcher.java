package presentation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.card.Card;
import domain.card.CardInputMapper;
import domain.game.Game;
import domain.game.GameInputMapper;
import presentation.dispatcher.AbstractDispatcher;

public class ViewHandDispatcher extends AbstractDispatcher {

	public ViewHandDispatcher(HttpServletRequest request, HttpServletResponse response) {
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
				Long gameID = Long.parseLong(requestPath[requestPath.length-2]);
				
				Game game = GameInputMapper.find(gameID);
				
				long deckOfCurrentUser;

				if(CurrentSession(myRequest)==game.getChallengerID()) {

					deckOfCurrentUser = game.getDeckOfChallenger();
				} else {

					deckOfCurrentUser = game.getDeckOfChallengee();
				}
				
				ArrayList<Card> handList = CardInputMapper.findAllInHand(deckOfCurrentUser, game.getId());
				
				myHelper.setRequestAttribute("handList", handList);
				
				forward("/WEB-INF/jsp/ViewHand.jsp");
				
				//myHelper.setRequestAttribute("game", challengeID);
//
//				
				
				
			}else {
				myHelper.setRequestAttribute("message", "Not logged in.");
				forward("/WEB-INF/jsp/Failure.jsp");
				
				
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			forward("/WEB-INf/jsp/Failure.jsp");
		}
	}

}
