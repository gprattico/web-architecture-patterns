package presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.card.Card;
import domain.card.CardInputMapper;
import domain.card.CardOutputMapper;
import domain.deck.Deck;
import domain.deck.DeckInputMapper;
import domain.deck.DeckOutputMapper;

/**
 * Servlet implementation class UploadDeck
 * UploadDeck replace function: https://stackoverflow.com/questions/19316394/removing-double-quotes-from-a-string-in-java
 */
@WebServlet("/UploadDeck")
public class UploadDeck extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public UploadDeck() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try{
			if(checkIfLoggedIn(request)){
				
				Deck deck = DeckInputMapper.findByUserID((long)request.getSession(true).getAttribute("id"));
							
				
				if(deck ==null){
					request.getRequestDispatcher("WEB-INF/jsp/DeckUpload.jsp").forward(request, response);
				}else{
					
					request.setAttribute("message", "You already have a deck");
					request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
					
				}
			}else{
				request.setAttribute("message", "You are not logged in.");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			}
			
		}catch(Exception e){
			request.setAttribute("message", "Something went wrong.");
			request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			
		}finally{
			closeDb();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		try{
			
			if(checkIfLoggedIn(request)){
				
				Deck deck = DeckInputMapper.findByUserID((long)request.getSession(true).getAttribute("id"));
				if(deck ==null){
					String deckString = request.getParameter("deck");
					//check if empty
					if(deckString.equals("") || deckString ==null){
						request.setAttribute("message", "Please enter 40 cards.");
						request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
					}
					//check if exactly 40
					String deckArray[] = deckString.trim().split("\n");
					if(deckArray.length!=40){
						request.setAttribute("message", "Please enter 40 cards. You have entered "+deckArray.length);
						request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
					}
					else{
						//check first character is e, p or t
						//check if 3 charachter onward is a
						try{
						String type="";
						String name = "";
						
						//HashMap <String, String> cardList = new HashMap<String,String>();
						for(int i=0; i<deckArray.length; i++){
							if(deckArray[i].startsWith("e")||deckArray[i].startsWith("p")||deckArray[i].startsWith("t")){
								 type = deckArray[i].substring(0, 1);
								 name = deckArray[i].substring(3).replace("\"", "");
								 deckArray[i] = type+" "+name;
							}
							else{
								request.setAttribute("message", "Problem with upload format.");
								request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
							}
						}
						
						
						//insert
						Long deckID = DeckInputMapper.getMaxDeckID();
						Deck newDeck = new Deck(deckID,(long)request.getSession(true).getAttribute("id"));
						DeckOutputMapper.insert(newDeck);
						//rdg.insert();
						//CardRDG card=null;
						Card card =null;
							for(String i : deckArray){
							card = new Card(CardInputMapper.getMaxCardID(),deckID,i.substring(0, 1),i.substring(2),0);//0 because in deck
							CardOutputMapper.insert(card);
							}
							request.setAttribute("message", "You now have a deck!");
							request.getRequestDispatcher("WEB-INF/jsp/Success.jsp").forward(request, response);	
							
						}catch(Exception e){
							e.printStackTrace();
							request.setAttribute("message", "Problem with upload format.");
							request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
						}
					}
				}else{
					request.setAttribute("message", "You already have a deck");
					request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("message", "You are not logged in.");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			}
		}catch(Exception e){
			request.setAttribute("message", "Something went wrong.");
			request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			
			
		}finally{
			closeDb();
		}
		
		
		
	}

}
