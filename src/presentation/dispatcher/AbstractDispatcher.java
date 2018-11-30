package presentation.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

/**
 * An abstract class to make all dispatchers come with checkIfLoggedIn() and currentSession()
 * This class extends SOENEA's dispatcher, as to inherit execute
 * @author Giovanni
 *
 */
public abstract class AbstractDispatcher extends Dispatcher{
	
	public AbstractDispatcher(HttpServletRequest request, HttpServletResponse response)
	{
		super.init(request, response);
	}
	
	@Override
	public abstract void execute() throws ServletException, IOException;

	protected boolean checkIfLoggedIn(HttpServletRequest request){
		
		try{
		if(request.getSession(true).getAttribute("id")==null)
				return false;
		else
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	protected long CurrentSession(HttpServletRequest request) {
		
		//return (long)request.getSession(true).getAttribute("id");
		return (long) myRequest.getSession(true).getAttribute("id");
	}
	
	public abstract void handleGet() throws IOException, ServletException;
}
