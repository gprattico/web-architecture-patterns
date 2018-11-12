package presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewBoard")
public class ViewBoard extends AbstractController {
	private static final long serialVersionUID = 1L;

	public ViewBoard() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			if(checkIfLoggedIn(request)&&IsInGame(request)) {
				
				//request.setAttribute("handList", handList);
				//request.getRequestDispatcher("WEB-INF/jsp/ViewBoard.jsp").forward(request, response);
				
				
			}else {
				request.setAttribute("message", "You are not logged in or not in a game.");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			
			
		}finally {
			closeDb();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
