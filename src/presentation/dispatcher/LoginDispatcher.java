package presentation.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginDispatcher extends AbstractDispatcher{

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	
	
	public LoginDispatcher(HttpServletRequest request, HttpServletResponse response) {
		//super.init(request, response);
		this.request = request;
		this.response = response;
	}
	
	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	public void doGet() throws IOException, ServletException {
		//forward("/WEB-INF/jsp/Success.jsp");
		//request.getRequestDispatcher("///W")
		request.getRequestDispatcher("WEB-INF/jsp/Success.jsp").forward(request, response);

	}

}
