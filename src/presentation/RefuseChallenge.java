package presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataSrc.ChallengeRDG;
import domain.ChallengeHelper;
import domain.ChallengeStatus;

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
				ArrayList<ChallengeRDG> rdgchallenge = ChallengeRDG.findAllOpen();
				ArrayList<ChallengeHelper> challenge = new ArrayList<ChallengeHelper>();
				
				for(ChallengeRDG rdg : rdgchallenge){
					if((rdg.getChallenger()!=(long)request.getSession(true).getAttribute("id"))&&(rdg.getChallengee() == (long)request.getSession(true).getAttribute("id"))&&(rdg.getStatus()==0)){
						challenge.add(new ChallengeHelper(rdg.getId(),rdg.getChallenger(),rdg.getChallengee(),rdg.getStatus()));
					}
				}
				
				//up till here we are good. challenge is a lost of challenges against ONLY us and not made by US
				for(ChallengeHelper helper : challenge){
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
				ChallengeRDG fetch = ChallengeRDG.find(Integer.parseInt(request.getParameter("challenges")));
				
				fetch.setStatus(ChallengeStatus.refused.ordinal());
				fetch.update();
				display.remove(Integer.parseInt(request.getParameter("challenge")));
				request.setAttribute("message", "You refused the challenge!");
				request.getRequestDispatcher("WEB-INF/jsp/Success.jsp").forward(request, response);
				
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
