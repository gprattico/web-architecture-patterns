package domain.command;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;

import domain.challenge.Challenge;
import domain.challenge.ChallengeInputMapper;
import domain.challenge.ChallengeOutputMapper;
import domain.challenge.ChallengeStatus;
import domain.deck.DeckInputMapper;
import domain.game.Game;
import domain.game.GameInputMapper;
import domain.game.GameOutputMapper;

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
		if(challenge.getChallengee() != (long)helper.getSessionAttribute("id")) {
			throw new CommandException("This is not a challenge against you.");
		}
		
		if(challenge.getVersion() != (int)helper.getRequestAttribute("challengeVersion")) {
			throw new LostUpdateException("Incorrect Version. Try refreshing your browser and trying again.");
		}
		
		challenge.setStatus(ChallengeStatus.accepted.ordinal());
		ChallengeOutputMapper.update(challenge);
		
		//create new game
		Game game = new Game(GameInputMapper.getMaxGameID(), challenge.getChallenger(), challenge.getChallengee(), 0, 1); //0 for in progress, 1 for version
		GameOutputMapper.insert(game);
		
		
		}catch(Exception e) {
			e.printStackTrace();
			throw new CommandException(e.getMessage() + "IN PROCESS METHOD COMMAND");
		}
	}

}
