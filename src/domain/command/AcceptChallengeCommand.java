package domain.command;

import java.util.ArrayList;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;

import domain.card.Card;
import domain.card.CardInputMapper;
import domain.card.CardOutputMapper;
import domain.challenge.Challenge;
import domain.challenge.ChallengeInputMapper;
import domain.challenge.ChallengeOutputMapper;
import domain.challenge.ChallengeStatus;
import domain.deck.Deck;
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
		 //0 for in progress, 1 for version, challenger id for currentTurn
		Game game = new Game(GameInputMapper.getMaxGameID(), challenge.getChallenger(), challenge.getChallengee(), 0, 1, challenge.getChallenger());
		GameOutputMapper.insert(game);
		//draw card
		//end turn will let players draw afterward
		Deck deck = DeckInputMapper.findByUserID(challenge.getChallenger());
		ArrayList<Card> cardList = CardInputMapper.findAll(deck.getId());
		cardList.get(0).setStatus(1);//drawing the first card is equivalent to setting its status to in the hand (1)
		CardOutputMapper.update(cardList.get(0));
		
		
		}catch(Exception e) {
			e.printStackTrace();
			throw new CommandException(e.getMessage() + "IN PROCESS METHOD COMMAND");
		}
	}

}
