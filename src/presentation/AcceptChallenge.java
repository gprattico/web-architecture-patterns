package presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataSrc.ChallengeRDG;
import dataSrc.GameRDG;
import dataSrc.UserRDG;
import domain.ChallengeHelper;
import domain.ChallengeStatus;

@WebServlet("/AcceptChallenge")
public class AcceptChallenge extends AbstractController {
	private static final long serialVersionUID = 1L;
	HashMap <Integer,String> display = new HashMap <Integer,String>();

	public AcceptChallenge() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			if(checkIfLoggedIn(request)){
				ArrayList<ChallengeRDG> rdgchallenge = ChallengeRDG.findAllOpen();
				ArrayList<ChallengeHelper> challenge = new ArrayList<ChallengeHelper>();
				
				for(ChallengeRDG rdg : rdgchallenge){
					if(rdg.getStatus()==0){
						challenge.add(new ChallengeHelper(rdg.getId(),rdg.getChallenger(),rdg.getChallengee(),rdg.getStatus()));
					}
				}
				
				for(ChallengeHelper helper : challenge){
					display.put((int)helper.getId(), helper.findChallengerUsername());
					
				}
				
					request.setAttribute("challenge", display);
					request.getRequestDispatcher("WEB-INF/jsp/AcceptChallenges.jsp").forward(request, response);
			}else{
				request.setAttribute("message", "You are not logged in.");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			}
			
		}catch(Exception e){
			
			request.setAttribute("message", "You are not logged in.");
			request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			
		}finally{
			closeDb();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			if(checkIfLoggedIn(request)){
//				
//				PrintWriter out = response.getWriter();
//				out.println("jarjar binx");
//				out.println(Integer.parseInt(request.getParameter("challenges")));
//				out.close();
//				
				ChallengeRDG fetch = ChallengeRDG.find(Integer.parseInt(request.getParameter("challenge")));
				
				if(fetch.getChallengee()!=(long)request.getSession(true).getAttribute("id")) {
					request.setAttribute("message", "You cannot accept a challenge not intended for you");
					request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
				}else {
				
				fetch.setStatus(ChallengeStatus.accepted.ordinal());
				fetch.update();
				
				
				
				display.remove(Integer.parseInt(request.getParameter("challenge")));
				
				//Create Game
				GameRDG game = new GameRDG(GameRDG.getMaxGameID(),fetch.getChallenger(),fetch.getChallengee(),0);
				game.insert();
				
				request.setAttribute("message", "You accepted the challenge!");
				request.getRequestDispatcher("WEB-INF/jsp/Success.jsp").forward(request, response);
				}
			}else{

				request.setAttribute("message", "not logged in.");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
				
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "exception caught");
			request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
		}finally{
			closeDb();
		}
		
	}

}
