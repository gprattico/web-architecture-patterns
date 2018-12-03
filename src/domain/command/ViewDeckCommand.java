package domain.command;

import java.util.ArrayList;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;

import domain.card.Card;
import domain.card.CardInputMapper;
import domain.deck.Deck;
import domain.deck.DeckInputMapper;

public class ViewDeckCommand extends AbstractCommand{

	public ViewDeckCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void process() throws CommandException {
		try {
			Deck deck = DeckInputMapper.find((long)helper.getRequestAttribute("deckID"));

			if(deck ==null) {
				throw new CommandException("You have no deck");
			}else {
				long deckID = deck.getId();
				ArrayList<Card> cardList = new ArrayList<Card>();
				cardList = CardInputMapper.findAll(deckID);
				
				helper.setRequestAttribute("cardList", cardList);
				//helper.setRequestAttribute("deckID", deckID);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);

		}
	}


}
