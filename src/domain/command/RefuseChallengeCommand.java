package domain.command;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;

import domain.challenge.Challenge;
import domain.challenge.ChallengeInputMapper;
import domain.challenge.ChallengeOutputMapper;
import domain.challenge.ChallengeStatus;

public class RefuseChallengeCommand extends AbstractCommand {

	public RefuseChallengeCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void process() throws CommandException {

		try {
			
			Challenge challenge = ChallengeInputMapper.find((long)helper.getRequestAttribute("challengeID"));
		//check if deck exists

		if(challenge.getChallengee() != (long)helper.getSessionAttribute("id")) {
			throw new CommandException("This is not a challenge against you.");
		}
		
		if(challenge.getVersion() != (int)helper.getRequestAttribute("challengeVersion")) {
			throw new LostUpdateException("Incorrect Version. Try refreshing your browser and trying again.");
		}
		challenge.setStatus(ChallengeStatus.refused.ordinal());
		
		ChallengeOutputMapper.update(challenge);
		
		
		}catch(Exception e) {
			e.printStackTrace();
			throw new CommandException(e.getMessage());
		}
	
	}

}
