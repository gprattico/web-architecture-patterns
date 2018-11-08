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
import domain.UserHelper;

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
				ArrayList<UserRDG> rdgUsers = UserRDG.findAll();
				ArrayList<UserHelper> user = new ArrayList<UserHelper>();
							
				for(UserRDG rdg : rdgUsers){
					if(rdg.getId()!=(long)request.getSession().getAttribute("id"))
					user.add(new UserHelper(rdg.getId(),rdg.getVersion(),rdg.getUsername(),rdg.getPassword()));
				}
				System.out.println(user.get(0).getUsername());
					request.setAttribute("players", user);
					request.getRequestDispatcher("WEB-INF/jsp/ChallengePlayer.jsp").forward(request, response);
				
			}else{
				request.setAttribute("message", "You are not logged in.");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			}
		}catch(Exception e){
			
		}finally{
			closeDb();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		try{
			if(checkIfLoggedIn(request)){
				
				ChallengeHelper helper = new ChallengeHelper(ChallengeRDG.getMaxChallengeID(),
				(long)request.getSession().getAttribute("id"), Integer.parseInt(request.getParameter("players")),0 );
				
				ChallengeRDG rdg = new ChallengeRDG(helper.getId(),helper.getChallenger(),helper.getChallengee(),
						helper.getStatus());
				
				rdg.insert();
				request.setAttribute("message", "You have just challenged user #"+helper.getChallengee());
				request.getRequestDispatcher("WEB-INF/jsp/Success.jsp").forward(request, response);
			}else{
				request.setAttribute("message", "Challenge failed");
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
