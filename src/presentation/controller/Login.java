package presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.user.User;
import domain.user.UserInputMapper;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends AbstractController {
	private static final long serialVersionUID = 1L;
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		try {
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		
		if(username==null || username.isEmpty() || password==null || password.isEmpty()){
			request.setAttribute("message", "You must enter a username and password");
			request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
		}
		else{
			
			//UserRDG user = UserRDG.find(username, password);
			
				User user = UserInputMapper.find(username);
				
				if(user ==null){
					request.setAttribute("message", "That username or password is incorrect.");
					request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);

				} else {
					long id = user.getId();
					
					request.getSession(true).setAttribute("id", id);
					request.setAttribute("message", "Hi "+username+", you have been logged in!");
					request.getRequestDispatcher("WEB-INF/jsp/Success.jsp").forward(request, response);
					//UserHelper helper = new UserHelper(id, user.getVersion(),user.getUsername(), user.getPassword());
					//request.setAttribute("helper", helper);
				}
			} 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			closeDb();
		}
		
	}

}
