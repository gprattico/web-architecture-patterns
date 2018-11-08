package presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataSrc.ChallengeRDG;
import dataSrc.UserRDG;
import domain.ChallengeHelper;

@WebServlet("/AcceptChallenge")
public class AcceptChallenge extends AbstractController {
	private static final long serialVersionUID = 1L;

	public AcceptChallenge() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try{
			
			if(checkIfLoggedIn(request)){
				ArrayList<ChallengeRDG> rdgchallenge = ChallengeRDG.findAllOpen();
				ArrayList<ChallengeHelper> challenge = new ArrayList<ChallengeHelper>();
//			
//				//get challengers
				for(ChallengeRDG rdg : rdgchallenge){
					if(rdg.getChallengee()==(long)request.getSession().getAttribute("id"))
					challenge.add(new ChallengeHelper(rdg.getId(),rdg.getChallenger(),rdg.getChallengee(),rdg.getStatus()));
				}
				
				PrintWriter out = response.getWriter();
				out.println(challenge.get(0).findChallengerUsername());
				out.close();
//					request.setAttribute("challenge", challenge);
//					request.getRequestDispatcher("WEB-INF/jsp/AcceptChallenges.jsp").forward(request, response);
//				}
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
		doGet(request, response);
	}

}
