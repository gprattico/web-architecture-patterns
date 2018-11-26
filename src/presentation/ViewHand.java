package presentation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataSrc.CardFinder;
import dataSrc.CardRDG;
import dataSrc.DeckRDG;
import dataSrc.GameRDG;
import dataSrc.HandRDG;
import domain.Card;

/**
 * Servlet implementation class ViewHand
 */
@WebServlet("/ViewHand")
public class ViewHand extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public ViewHand() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			if(checkIfLoggedIn(request)&&hasDeck(request)) {
				
				GameRDG game = GameRDG.find((long)request.getSession(true).getAttribute("id")); //get the game id
				DeckRDG deck = DeckRDG.findByUserID((long)request.getSession(true).getAttribute("id")); //get the deck id
				ArrayList<Card> cardList = CardFinder.findAllInHand(deck.getId()); //get all the cards in my hand

				//edit Card table to have cardStatus. 0 in deck, 1 in hand, 2 on bench, 3 discard pile
				//edit hand rdg, simply remove headCardID
				ArrayList<HandRDG> handList = new ArrayList<HandRDG>();
				for(Card cardIterator:cardList) {
					handList.add(new HandRDG(cardIterator.getId(),game.getId(),(long)request.getSession(true).getAttribute("id")));
				}
				
//				
//				ArrayList<CardRDG> handList = new ArrayList<CardRDG>();
//				//handList.addAll((int)hand.getHeadCardID(), cardList);
//				for(int i=(int)hand.getHeadCardID()-1; i<hand.getHeadCardID()+4;i++) {
//					handList.add(cardList.get(i));
//					System.out.println(cardList.get(i).getId() + "/"+hand.getHeadCardID()+"/" + i);
//				}
				request.setAttribute("handList", handList);
				request.getRequestDispatcher("WEB-INF/jsp/ViewHand.jsp").forward(request, response);
			}else {
				
				request.setAttribute("message", "Not logged in or no deck.");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "caught an exception");
			request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			
		}finally {
			closeDb();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
