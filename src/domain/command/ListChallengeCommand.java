package domain.command;

import java.util.ArrayList;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;

import domain.challenge.Challenge;
import domain.challenge.ChallengeInputMapper;
import domain.deck.Deck;
import domain.deck.DeckInputMapper;

public class ListChallengeCommand extends AbstractCommand {

	public ListChallengeCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void process() throws CommandException {
		
		try {
		ArrayList<Challenge> challenge = ChallengeInputMapper.findAll();
		
		helper.setRequestAttribute("challenge", challenge);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CommandException(e);

		}
	}

}
