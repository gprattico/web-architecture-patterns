package presentation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.challenge.Challenge;
import domain.challenge.ChallengeInputMapper;
import domain.challenge.ChallengeOutputMapper;
import domain.challenge.ChallengeStatus;

/**
 * Servlet implementation class RefuseChallenge
 */
@WebServlet("/RefuseChallenge")
public class RefuseChallenge extends AbstractController {
	private static final long serialVersionUID = 1L;
	HashMap <Integer,String> display = new HashMap <Integer,String>();

    public RefuseChallenge() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			
			if(checkIfLoggedIn(request)){
				ArrayList<Challenge> challenge = ChallengeInputMapper.findAllOpen();
				//ArrayList<ChallengeHelper> challenge = new ArrayList<ChallengeHelper>();
				
//				for(ChallengeRDG rdg : rdgchallenge){
//					if(rdg.getStatus()==0){
//						challenge.add(new ChallengeHelper(rdg.getId(),rdg.getChallenger(),rdg.getChallengee(),rdg.getStatus()));
//					}
//				}
				
				for(Challenge helper : challenge){
					display.put((int)helper.getId(), helper.findChallengerUsername());
					
				}
				
					request.setAttribute("challenge", display);
					request.getRequestDispatcher("WEB-INF/jsp/RefuseChallenges.jsp").forward(request, response);
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
				Challenge fetch = ChallengeInputMapper.find(Integer.parseInt(request.getParameter("challenge")));
				if(fetch.getChallenger()==(long)request.getSession(true).getAttribute("id")) {
					fetch.setStatus(ChallengeStatus.withdrawn.ordinal());
					request.setAttribute("message", "You withdrew the challenge!");
					//fetch.update();
					ChallengeOutputMapper.update(fetch);
					display.remove(Integer.parseInt(request.getParameter("challenge")));
					request.getRequestDispatcher("WEB-INF/jsp/Success.jsp").forward(request, response);
				}else if(fetch.getChallengee()!=(long)request.getSession(true).getAttribute("id")) {
					request.setAttribute("message", "That challenge wasn't for you.");
					request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
				}else {
				fetch.setStatus(ChallengeStatus.refused.ordinal());
					request.setAttribute("message", "You refused the challenge!");
					ChallengeOutputMapper.update(fetch);
					//fetch.update();
					display.remove(Integer.parseInt(request.getParameter("challenge")));
					request.getRequestDispatcher("WEB-INF/jsp/Success.jsp").forward(request, response);
				}
				
				
				
				
				
			}else{

				request.setAttribute("message", "Something went wrong");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
				
			}
		}catch(Exception e){

			request.setAttribute("message", "Something went wrong");
			request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
		}finally{
			closeDb();
		}
	}

}
