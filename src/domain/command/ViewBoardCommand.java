package domain.command;

import java.util.ArrayList;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;

import domain.card.Card;
import domain.card.CardInputMapper;
import domain.deck.Deck;
import domain.deck.DeckInputMapper;
import domain.game.Game;
import domain.game.GameInputMapper;

public class ViewBoardCommand extends AbstractCommand {

	public ViewBoardCommand(Helper helper) {
		super(helper);
	}
	
	@Override
	public void process() throws CommandException {
		try {
			
			long gameID = (long)helper.getRequestAttribute("swagID");
			Game game = GameInputMapper.find(gameID);
		if(game.getChallengerID() == (long)helper.getSessionAttribute("id") || game.getChallengeeID() == (long)helper.getSessionAttribute("id")) {

			Deck challengerDeck = DeckInputMapper.find(game.getDeckOfChallenger());
			Deck challengeeDeck = DeckInputMapper.find(game.getDeckOfChallengee());
			//arraylist of cards in hand
			ArrayList<Card> challengerHandList = CardInputMapper.findAllInHand(DeckInputMapper.find(game.getDeckOfChallenger()).getId(),game.getId());
			ArrayList<Card> challengeeHandList = CardInputMapper.findAllInHand(DeckInputMapper.find(game.getDeckOfChallengee()).getId(),game.getId());
			
			//arrayList of all cards attributed to them
			ArrayList<Card> challengerDeckList = CardInputMapper.findAll(challengerDeck.getId());
			ArrayList<Card> challengeeDeckList = CardInputMapper.findAll(challengeeDeck.getId());
			
			ArrayList<Card> benchedListChallenger = CardInputMapper.findAllBenched(DeckInputMapper.find(game.getDeckOfChallenger()).getId(),game.getId());
			ArrayList<Card> benchedListChallengee = CardInputMapper.findAllBenched(DeckInputMapper.find(game.getDeckOfChallengee()).getId(),game.getId());
			//deck size is number of cards status =0
			int deckSize1=0;
			int deckSize2=0;
			int discardSize1=0;
			int discardSize2=0;
			int benchSize1=0;
			int benchSize2=0;
			
			for(Card cardIterator:challengerDeckList) {
				if(cardIterator.getStatus()==0) {
					deckSize1++;						
				}else if(cardIterator.getStatus()==2) {
					discardSize1++;
				}else if(cardIterator.getStatus()==3) {
					benchSize1++;
				}						
			}
			for(Card cardIterator:challengeeDeckList) {
				if(cardIterator.getStatus()==0) {
					deckSize2++;						
				}else if(cardIterator.getStatus()==2) {
					discardSize2++;
				}else if(cardIterator.getStatus()==3) {
					benchSize2++;
				}
			}
			
			//SET ATTRIBUTES
			helper.setRequestAttribute("game", game);
			helper.setRequestAttribute("challengerDeck", challengerDeck);
			helper.setRequestAttribute("challengeeDeck", challengeeDeck);
			helper.setRequestAttribute("challengerHandList", challengerHandList.size());
			helper.setRequestAttribute("challengeeHandList", challengeeHandList.size());
			helper.setRequestAttribute("deckSize1", deckSize1);
			helper.setRequestAttribute("deckSize2", deckSize2);
			helper.setRequestAttribute("discardSize1", discardSize1);
			helper.setRequestAttribute("discardSize2", discardSize2);
			helper.setRequestAttribute("benchSize1", benchSize1);
			helper.setRequestAttribute("benchSize2", benchSize2);
			helper.setRequestAttribute("benchedListChallenger", benchedListChallenger);
			helper.setRequestAttribute("benchedListChallengee", benchedListChallengee);
			//request.getRequestDispatcher("WEB-INF/jsp/ViewBoard.jsp").forward(request, response);
			
			
			
			
		}else {
			throw new CommandException("cant look at a game that is not yours");
		}
		}catch(Exception e) {
			e.printStackTrace();
			helper.setRequestAttribute("message", e.getMessage());
			throw new CommandException(e.getMessage());
		}
		
		
		
	}

}
