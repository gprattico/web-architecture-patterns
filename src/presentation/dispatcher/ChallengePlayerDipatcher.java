package presentation.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.command.ChallengePlayerCommand;

public class ChallengePlayerDipatcher extends AbstractDispatcher {

	public ChallengePlayerDipatcher(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			if(checkIfLoggedIn(myRequest)&&hasDeck(myRequest)){
				
				String[] requestPath = myRequest.getServletPath().trim().split("/");
				Long playerID = Long.parseLong(requestPath[requestPath.length-2]);
				myHelper.setRequestAttribute("playerID", playerID);
				long deckUsedToChallenge = Long.parseLong(myRequest.getParameter("deck"));
				myHelper.setRequestAttribute("deckUsedToChallenge", deckUsedToChallenge);
				
				ChallengePlayerCommand challengePlayer = new ChallengePlayerCommand(myHelper);
				challengePlayer.process();
//				Challenge helper = new Challenge(ChallengeInputMapper.getMaxChallengeID(),
//				(long)myRequest.getSession().getAttribute("id"), (long)playerID,1,0,deckUsedToChallenge);//0 for challenge status, 1 for version
//				
//				ChallengeOutputMapper.insert(helper);
//				myRequest.setAttribute("message", "You have just challenged user #"+helper.getChallengee());
				forward("/WEB-INF/jsp/Success.jsp");
			}else{
				myRequest.setAttribute("message", "Challenge failed. You either don't have a deck, aren't logged in or tried challenging yourself!");
				forward("/WEB-INF/jsp/Failure.jsp");
			}
		
		
		}catch(Exception e){
			e.printStackTrace();
			myRequest.setAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/Failure.jsp");
		
		}
	}

	@Override
	public void handleGet() throws IOException, ServletException {
		// TODO Auto-generated method stub
		this.execute();
	}

}
