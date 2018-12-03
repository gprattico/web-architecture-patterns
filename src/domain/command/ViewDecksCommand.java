package domain.command;

import java.util.ArrayList;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;

import domain.deck.Deck;
import domain.deck.DeckInputMapper;

public class ViewDecksCommand extends AbstractCommand {

	public ViewDecksCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void process() throws CommandException {
		try {
			ArrayList<Deck> deckList = DeckInputMapper.findAll();
			
			helper.setRequestAttribute("deckList", deckList);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);

		}
	}

}
