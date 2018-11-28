package presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import domain.user.User;
import domain.user.UserInputMapper;
import domain.user.UserOutputMapper;

/**
 * Servlet implementation class Register
 * Servlet doPost() if statement is taken from Stuart Thiel RegisterPC.java
 * https://users.encs.concordia.ca/~sthiel/soen387/A1/A1_Pokemon_Solution_UML.pdf
 * in Failure.jsp, the page directive comes from stackoverflow
 * https://stackoverflow.com/questions/10595775/set-content-type-to-application-json-in-jsp-file
 */
@WebServlet("/Register")
public class Register extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public Register() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("WEB-INF/jsp/Register.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		
		if(username==null || username.isEmpty() || password==null || password.isEmpty()){
			request.setAttribute("message", "You must enter a username and password");
			request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
		}
		else{
			//check if user already exists, if not create new user

			User user = UserInputMapper.find(username);
				
				if(user !=null){
					request.setAttribute("message", "That username has been taken.");
					request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);

				} else {
					user = new User(UserInputMapper.getMaxID(),1,username,password);
					
					UserOutputMapper.insert(user);
					long id = user.getId();
					
					request.getSession(true).setAttribute("id", id);
					request.setAttribute("message", "Hi "+username+", you have been registered!");
					request.getRequestDispatcher("WEB-INF/jsp/Success.jsp").forward(request, response);
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
