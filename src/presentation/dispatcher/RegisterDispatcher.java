package presentation.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterDispatcher extends AbstractDispatcher {

	public RegisterDispatcher(HttpServletRequest request, HttpServletResponse response) {

		super.init(request, response);
	}

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doGet() throws IOException, ServletException {
		forward("/WEB-INF/jsp/Register.jsp");
	}

}
