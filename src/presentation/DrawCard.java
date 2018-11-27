package presentation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.card.Card;
import domain.card.CardInputMapper;
import domain.card.CardOutputMapper;
import domain.deck.Deck;
import domain.deck.DeckInputMapper;

@WebServlet("/DrawCard")
public class DrawCard extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public DrawCard() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{//add check if deck size is grater than 0 (cards still remaining to draw)
			if(checkIfLoggedIn(request)&&hasDeck(request)){
				
				//Game game = GameInputMapper.find((long)request.getSession(true).getAttribute("id"));
				Deck deck = DeckInputMapper.findByUserID((long)request.getSession(true).getAttribute("id"));
				ArrayList<Card> cardList = CardInputMapper.findAll(deck.getId());
				//get all the cards
				//set the first card that isnt a 0 to status 1
				//insert that card into the handRDG
				//HandRDG hand = null;
				//Boolean cardDrawn = false;
				for(Card cardIterator:cardList) {
					//if its 0,  make it a 1
					if(cardIterator.getStatus()==0) {
//						hand = new HandRDG(cardIterator.getId(),game.getId(),(long)request.getSession(true).getAttribute("id"));
//						hand.insert();
						cardIterator.setStatus(1);
						//cardIterator.update();
						CardOutputMapper.update(cardIterator);
						//cardDrawn =true;
						break;
					}
				}
				
				request.setAttribute("message", "card drawn!");
				request.getRequestDispatcher("WEB-INF/jsp/Success.jsp").forward(request, response);
				
				
			}else {
				request.setAttribute("message", "Not logged in or no deck");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDb();
		}
	}

}
