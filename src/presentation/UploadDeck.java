package presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataSrc.CardRDG;
import dataSrc.DeckRDG;

/**
 * Servlet implementation class UploadDeck
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
				
				DeckRDG deck = DeckRDG.findByUserID((long)request.getSession(true).getAttribute("id"));
							
				
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
				
				DeckRDG deckRdg = DeckRDG.find((long)request.getSession(true).getAttribute("id"));
				if(deckRdg ==null){
					String deckString = request.getParameter("deckbox");
					//check if empty
					if(deckString.equals("") || deckString ==null){
						request.setAttribute("message", "Please enter 40 cards.");
						request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
					}
					//check if exactly 40
					String deckArray[] = deckString.replace("\r", "").trim().split("\n");
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
						Long deckID = DeckRDG.getMaxDeckID();
						DeckRDG rdg = new DeckRDG(deckID,(long)request.getSession(true).getAttribute("id"));
						rdg.insert();
						CardRDG card=null;
							for(String i : deckArray){
							card = new CardRDG(CardRDG.getMaxCardID(),deckID,i.substring(0, 1),i.substring(2));
							card.insert();
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
