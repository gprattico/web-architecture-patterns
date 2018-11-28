package presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlayPokemonToBench
 */
@WebServlet("/PlayPokemonToBench")
public class PlayPokemonToBench extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public PlayPokemonToBench() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(checkIfLoggedIn(request)&&hasDeck(request)&&IsInGame(request)) {
				
				
				
			}else {
				request.setAttribute("message", "You are not logged in or not in a game or have no deck");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDb();			
		}
		
	}

}
