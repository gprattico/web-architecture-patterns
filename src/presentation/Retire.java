package presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.game.Game;
import domain.game.GameInputMapper;
import domain.game.GameOutputMapper;

@WebServlet("/Retire")
public class Retire extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public Retire() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			if(checkIfLoggedIn(request) && IsInGame(request)) {
				
			Game game = GameInputMapper.find((long)request.getSession(true).getAttribute("id")); //returns a game where i was either challenger or challengee
			GameOutputMapper.delete(game);
			
			//rdg.delete();
			
			//find the challenge associated with this game and delete it
			//ChallengeRDG challengeRDG = ChallengeRDG.findByInProgress((long)request.getSession(true).getAttribute("id"));
			request.setAttribute("message", "You have retired.");
			request.getRequestDispatcher("WEB-INF/jsp/Success.jsp").forward(request, response);
			
			}else {
				//not logged in
				request.setAttribute("message", "You are not in a game.");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDb();
		}
	}

}
