package presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public Logout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try{
			if(checkIfLoggedIn(request)){	
		request.getSession(true).invalidate();
		request.setAttribute("message", "You have been successfully logged out.");
		request.getRequestDispatcher("WEB-INF/jsp/Success.jsp").forward(request, response);
			}else{
				request.setAttribute("message", "You are not logged in, cannot log out.");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			}
		
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "You are not logged in, cannot log out.");
			request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			

		}finally{
			
			closeDb();
		}
	}

}
