package presentation.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.challenge.ChallengeInputMapper;
import domain.command.WithdrawChallengeCommand;

public class WithdrawChallengeDispatcher extends AbstractDispatcher {

	public WithdrawChallengeDispatcher(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			String[] requestPath = myRequest.getServletPath().trim().split("/");

			//check if im logged in, i have a deck and the challenge i am accepting exists
			if(checkIfLoggedIn(myRequest)&&hasDeck(myRequest)&&(ChallengeInputMapper.find(Long.parseLong(requestPath[requestPath.length-2]))!=null)){
				
				Long challengeID = Long.parseLong(requestPath[requestPath.length-2]);
				myHelper.setRequestAttribute("challengeID", challengeID);

				int challengeVersion = Integer.parseInt(myRequest.getParameter("version"));
				myHelper.setRequestAttribute("challengeVersion", challengeVersion);
			
				
				//ChallengePlayerCommand challengePlayer = new ChallengePlayerCommand(myHelper);
				WithdrawChallengeCommand withdrawChallenge = new WithdrawChallengeCommand(myHelper);
				withdrawChallenge.process();
				forward("/WEB-INF/jsp/Success.jsp");
			}else{
				myRequest.setAttribute("message", "Withdraw failed. You either don't have a deck, aren't logged in or the version is incorrect!");
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
