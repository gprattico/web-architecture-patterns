package domain.command;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;

import domain.challenge.Challenge;
import domain.challenge.ChallengeInputMapper;
import domain.challenge.ChallengeOutputMapper;
import domain.challenge.ChallengeStatus;
import domain.deck.DeckInputMapper;

public class AcceptChallengeCommand extends AbstractCommand {

	public AcceptChallengeCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void process() throws CommandException {
		
		try {
			
			Challenge challenge = ChallengeInputMapper.find((long)helper.getRequestAttribute("challengeID"));
		//check if deck exists
		if(DeckInputMapper.find((long)helper.getRequestAttribute("deckUsedToAcceptChallenge")) == null) {
			throw new CommandException("Deck id does not exist");
		}
		if(challenge.getChallenger() == (long)helper.getSessionAttribute("id")) {
			throw new CommandException("Cannot challenge yourself");
		}
		
		if(challenge.getVersion() != (int)helper.getRequestAttribute("challengeVersion")) {
			throw new LostUpdateException("Incorrect Version. Try refreshing your browser and trying again.");
		}
		
		Challenge updatedChallenge = new Challenge(challenge.getId(), challenge.getChallenger(),challenge.getChallengee(),ChallengeStatus.accepted.ordinal(), challenge.getVersion(),challenge.getDeckOfChallenger());
		ChallengeOutputMapper.update(updatedChallenge);
		
		
		}catch(Exception e) {
			e.printStackTrace();
			throw new CommandException(e.getMessage() + "IN PROCESS METHOD COMMAND");
		}
	}

}
