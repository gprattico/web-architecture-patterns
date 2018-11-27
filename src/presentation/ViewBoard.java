package presentation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataSrc.game.GameRDG;
import domain.card.Card;
import domain.card.CardInputMapper;
import domain.deck.Deck;
import domain.deck.DeckInputMapper;

@WebServlet("/ViewBoard")
public class ViewBoard extends AbstractController {
	private static final long serialVersionUID = 1L;

	public ViewBoard() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			if(checkIfLoggedIn(request)&&IsInGame(request)) {
				
				//game
				GameRDG game = GameRDG.find((long)request.getSession(true).getAttribute("id"));
				
				//deck ids
				Deck challengerDeck = DeckInputMapper.findByUserID(game.getChallengerID());
				Deck challengeeDeck = DeckInputMapper.findByUserID(game.getChallengeeID());
				
				//arraylist of cards in hand
				ArrayList<Card> challengerHandList = CardInputMapper.findAllInHand(DeckInputMapper.findByUserID(game.getChallengerID()).getId());
				ArrayList<Card> challengeeHandList = CardInputMapper.findAllInHand(DeckInputMapper.findByUserID(game.getChallengeeID()).getId());
				
				//arrayList of all cards attributed to them
				ArrayList<Card> challengerDeckList = CardInputMapper.findAll(challengerDeck.getId());
				ArrayList<Card> challengeeDeckList = CardInputMapper.findAll(challengeeDeck.getId());
				
				ArrayList<Card> benchedListChallenger = CardInputMapper.findAllBenched(DeckInputMapper.findByUserID(game.getChallengerID()).getId());
				ArrayList<Card> benchedListChallengee = CardInputMapper.findAllBenched(DeckInputMapper.findByUserID(game.getChallengeeID()).getId());
				//deck size is number of cards status =0
				int deckSize1=0;
				int deckSize2=0;
				int discardSize1=0;
				int discardSize2=0;
				int benchSize1=0;
				int benchSize2=0;
				
				for(Card cardIterator:challengerDeckList) {
					if(cardIterator.getStatus()==0) {
						deckSize1++;						
					}else if(cardIterator.getStatus()==2) {
						discardSize1++;
					}else if(cardIterator.getStatus()==3) {
						benchSize1++;
					}						
				}
				for(Card cardIterator:challengeeDeckList) {
					if(cardIterator.getStatus()==0) {
						deckSize2++;						
					}else if(cardIterator.getStatus()==2) {
						discardSize2++;
					}else if(cardIterator.getStatus()==3) {
						benchSize2++;
					}
				}
				
//								
//				String gameStatus="";
//				if(game.getStatus()==0)
//					gameStatus="playing";
//				else
//					gameStatus="ended";
				
				request.setAttribute("game", game);
				request.setAttribute("challengerDeck", challengerDeck);
				request.setAttribute("challengeeDeck", challengeeDeck);
				request.setAttribute("challengerHandList", challengerHandList.size());
				request.setAttribute("challengeeHandList", challengeeHandList.size());
				request.setAttribute("deckSize1", deckSize1);
				request.setAttribute("deckSize2", deckSize2);
				request.setAttribute("discardSize1", discardSize1);
				request.setAttribute("discardSize2", discardSize2);
				request.setAttribute("benchSize1", benchSize1);
				request.setAttribute("benchSize2", benchSize2);
				request.setAttribute("benchedListChallenger", benchedListChallenger);
				request.setAttribute("benchedListChallengee", benchedListChallengee);
				request.getRequestDispatcher("WEB-INF/jsp/ViewBoard.jsp").forward(request, response);
				
				
			}else {
				request.setAttribute("message", "You are not logged in or not in a game.");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			
			
		}finally {
			closeDb();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
