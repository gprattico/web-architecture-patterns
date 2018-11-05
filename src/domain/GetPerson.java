package domain;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetPerson")
public class GetPerson extends AbstractPageController {
	private static final long serialVersionUID = 1L;
       
    public GetPerson() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			UserRDG user = UserRDG.find(1);
			
			request.setAttribute("user", user);
			//request.getRequestDispatcher("/WEB-INF/jsp/person.jsp").forward(request, response);
			System.out.println(user.getUsername());
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeDb();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
