package domain.command;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;

import domain.challenge.Challenge;
import domain.challenge.ChallengeInputMapper;
import domain.challenge.ChallengeOutputMapper;

public class ChallengePlayerCommand extends AbstractCommand {

	public ChallengePlayerCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void process() throws CommandException{
		try {	
			Challenge challenge = new Challenge(ChallengeInputMapper.getMaxChallengeID(),
			(long)helper.getRequestAttribute("id"), (long)helper.getRequestAttribute("playerID"),1,0,(long)helper.getRequestAttribute("deckUsedToChallenge"));//0 for challenge status, 1 for version
			
			ChallengeOutputMapper.insert(challenge);
			//myRequest.setAttribute();
			helper.setRequestAttribute("message", "You have just challenged user #"+challenge.getChallengee());
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			throw new CommandException(e.getMessage());
		}
	}
}
