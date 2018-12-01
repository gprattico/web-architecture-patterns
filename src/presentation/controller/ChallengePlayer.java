package presentation.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.challenge.Challenge;
import domain.challenge.ChallengeInputMapper;
import domain.challenge.ChallengeOutputMapper;
import domain.deck.DeckInputMapper;
import domain.user.User;
import domain.user.UserInputMapper;

/**
 * Servlet implementation class ChallengePlayer
 */
@WebServlet("/ChallengePlayer")
public class ChallengePlayer extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public ChallengePlayer() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try{
			
			if(checkIfLoggedIn(request)){
				ArrayList<User> Users = UserInputMapper.findAll();
//				ArrayList<UserHelper> user = new ArrayList<UserHelper>();
//							
//				for(User userIterator : Users){
//					//if(rdg.getId()!=(long)request.getSession().getAttribute("id"))
//					user.add(new UserHelper(rdg.getId(),rdg.getVersion(),rdg.getUsername(),rdg.getPassword()));
//				}
				//check if they have a deck
				if(DeckInputMapper.findByUserID((long)request.getSession(true).getAttribute("id"))==null){
					request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
					
				}else {
					
					request.setAttribute("player", Users);
					request.getRequestDispatcher("WEB-INF/jsp/ChallengePlayer.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("message", "You are not logged in or have no deck!");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			}
		}catch(Exception e){
			request.setAttribute("message", "Exception caught");
			request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			
		}finally{
			closeDb();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		try{
			if(checkIfLoggedIn(request)&&hasDeck(request)&&(Integer.parseInt(request.getParameter("player"))!=(long)request.getSession(true).getAttribute("id"))){
				
				Challenge helper = new Challenge(ChallengeInputMapper.getMaxChallengeID(),
				(long)request.getSession().getAttribute("id"), Integer.parseInt(request.getParameter("player")),0,1,DeckInputMapper.findByUserID((long)request.getSession(true).getAttribute("id")).getId() );
				
//				Challenge rdg = new Challenge(helper.getId(),helper.getChallenger(),helper.getChallengee(),
//						helper.getStatus());
				
				ChallengeOutputMapper.insert(helper);
				//rdg.insert();
				request.setAttribute("message", "You have just challenged user #"+helper.getChallengee());
				request.getRequestDispatcher("WEB-INF/jsp/Success.jsp").forward(request, response);
			}else{
				request.setAttribute("message", "Challenge failed. You either don't have a deck, aren't logged in or tried challenging yourself!");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			}
		
		
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "Challenge failed");
			request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
		
		}finally{
			closeDb();
		}
	}

}
