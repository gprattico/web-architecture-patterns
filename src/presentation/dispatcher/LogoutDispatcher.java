package presentation.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutDispatcher extends AbstractDispatcher {

	public LogoutDispatcher(HttpServletRequest request, HttpServletResponse response) {

		super.init(request, response);
	}

	@Override
	public void handleGet() throws IOException, ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
