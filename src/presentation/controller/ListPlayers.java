package presentation.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import domain.user.User;
import domain.user.UserInputMapper;

/**
 * Servlet implementation class ListPlayers
 */
@WebServlet("/ListPlayers")
public class ListPlayers extends AbstractController {
	private static final long serialVersionUID = 1L;
    public ListPlayers() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			if(checkIfLoggedIn(request)){
				ArrayList<User> users = UserInputMapper.findAll();
//				ArrayList<UserHelper> user = new ArrayList<UserHelper>();
//							
//				for(UserRDG rdg : rdgUsers)
//					user.add(new UserHelper(rdg.getId(),rdg.getVersion(),rdg.getUsername(),rdg.getPassword()));
				
					request.setAttribute("players", users);
					request.getRequestDispatcher("WEB-INF/jsp/ListPlayers.jsp").forward(request, response);
				
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
		doGet(request, response);
	}

}
