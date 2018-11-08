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
import domain.ChallengeStatus;

//controller used to test

@WebServlet("/test")
public class testController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public testController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			PrintWriter out = response.getWriter();
			
			out.println(ChallengeStatus.open.ordinal());
		
			out.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.print("bad request");
			out.close();
		}
		finally {
			closeDb();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
