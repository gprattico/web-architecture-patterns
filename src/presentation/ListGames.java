package presentation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.game.Game;
import domain.game.GameInputMapper;

/**
 * Servlet implementation class ListGames
 */
@WebServlet("/ListGames")
public class ListGames extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see AbstractController#AbstractController()
     */
    public ListGames() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try{
			
			if(checkIfLoggedIn(request)){
				ArrayList<Game> gameList = GameInputMapper.findAll();
				//ArrayList<GameHelper> helperList = new ArrayList<GameHelper>();
							
//				for(Game rdg : gameList)
//					helperList.add(new GameHelper(rdg.getId(),rdg.getChallengerID(),rdg.getChallengeeID(),rdg.getStatus()));
				
					request.setAttribute("challenge", gameList);
					request.getRequestDispatcher("WEB-INF/jsp/ListGames.jsp").forward(request, response);
				
			}else{
				request.setAttribute("message", "You are not logged in.");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "Caught an exception");
			request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			
		}finally{
			closeDb();
		}
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
