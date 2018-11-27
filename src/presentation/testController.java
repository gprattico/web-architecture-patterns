package presentation;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataSrc.DeckRDG;
import dataSrc.card.CardFinder;
import dataSrc.card.CardTDG;
import domain.card.Card;

//controller used to test

@WebServlet("/test")
public class testController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public testController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			PrintWriter out = response.getWriter();
			
//			GameRDG card1 = new GameRDG(GameRDG.getMaxGameID(),1,2,0);
//			card1.insert();
		
			UserRDG user = new UserRDG(1,1,"john","hoehoe");
			user.insert();
			DeckRDG deck = new DeckRDG(1, 1);
			deck.insert();
			
			CardTDG tdg = new CardTDG();
			tdg.insert(1, 1, "pokemon", "charizard", 0);
			
			Card card = CardFinder.findAll(1).get(0);
			
			out.println(card.getName());
			
			
			out.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.print("bad request");
			out.close();
		}
		finally {
			closeDb();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
