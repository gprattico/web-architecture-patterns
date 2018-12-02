package domain.command;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;

import domain.challenge.Challenge;
import domain.challenge.ChallengeInputMapper;
import domain.challenge.ChallengeOutputMapper;
import domain.challenge.ChallengeStatus;

public class WithdrawChallengeCommand extends AbstractCommand {

	public WithdrawChallengeCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public synchronized void process() throws CommandException {

		try {
			
			Challenge challenge = ChallengeInputMapper.find((long)helper.getRequestAttribute("challengeID"));
//		if((challenge.getChallenger()) != ((long)helper.getSessionAttribute("id"))) {
//			throw new CommandException("This is not your challenge");
//		}

		Long challengerID = challenge.getChallenger();
		Long userID = (long)helper.getSessionAttribute("id");
		
		if(challenge.getVersion() != (int)helper.getRequestAttribute("challengeVersion")) {
			throw new LostUpdateException("Incorrect Version. Try refreshing your browser and trying again.");
		}
		
		if((long)challengerID==(long)userID) {
			//throw new CommandException("This is not your challenge");
			
			challenge.setStatus(ChallengeStatus.withdrawn.ordinal());
			ChallengeOutputMapper.update(challenge);
			
		}else {
			
			throw new CommandException("You didn't make this challenge.");
			
		}
		
	
		
		
		}catch(Exception e) {
			e.printStackTrace();
			throw new CommandException(e.getMessage());
		}
	
	}

}
