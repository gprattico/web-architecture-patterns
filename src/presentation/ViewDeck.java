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
import domain.Card;

/**
 * Servlet implementation class ViewDeck
 */
@WebServlet("/ViewDeck")
public class ViewDeck extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public ViewDeck() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try{
			if(checkIfLoggedIn(request)){
				long userID = (long)request.getSession(true).getAttribute("id");
				DeckRDG rdg = DeckRDG.findByUserID(userID);
				
				//check if deck null
				if(rdg == null){
					request.setAttribute("message", "You don't have a deck.");
					request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
				}else {
				
				//find all cards associated with this deck
				long deckID = rdg.getId();
				ArrayList<Card> cardList = new ArrayList<Card>();
				cardList = CardFinder.findAll(deckID);
				
				
				
				request.setAttribute("cardList", cardList);
				request.setAttribute("deckID", deckID);
				request.getRequestDispatcher("WEB-INF/jsp/ViewDeck.jsp").forward(request, response);
				
				}
				
			}else{
			
				request.setAttribute("message", "Not logged in.");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			
			
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "Something went wrong.");
			request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			
		}finally{
			closeDb();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
