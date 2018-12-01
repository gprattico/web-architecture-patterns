package presentation.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.challenge.Challenge;
import domain.challenge.ChallengeInputMapper;
import domain.challenge.ChallengeOutputMapper;

public class ChallengePlayerDipatcher extends AbstractDispatcher {

	public ChallengePlayerDipatcher(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			if(checkIfLoggedIn(myRequest)&&hasDeck(myRequest)&&(Integer.parseInt(myRequest.getParameter("player"))!=(long)request.getSession(true).getAttribute("id"))){
				
				Challenge helper = new Challenge(ChallengeInputMapper.getMaxChallengeID(),
				(long)request.getSession().getAttribute("id"), Integer.parseInt(request.getParameter("player")),0,1 );
				
//				Challenge rdg = new Challenge(helper.getId(),helper.getChallenger(),helper.getChallengee(),
//						helper.getStatus());
				
				ChallengeOutputMapper.insert(helper);
				//rdg.insert();
				request.setAttribute("message", "You have just challenged user #"+helper.getChallengee());
				request.getRequestDispatcher("WEB-INF/jsp/Success.jsp").forward(request, response);
			}else{
				request.setAttribute("message", "Challenge failed. You either don't have a deck, aren't logged in or tried challenging yourself!");
				request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
			}
		
		
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "Challenge failed");
			request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
		
		}
	}

	@Override
	public void handleGet() throws IOException, ServletException {
		// TODO Auto-generated method stub
		this.execute();
	}

}
