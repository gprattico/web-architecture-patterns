package domain.command;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;

import domain.card.Card;
import domain.card.CardInputMapper;
import domain.card.CardOutputMapper;
import domain.deck.Deck;
import domain.deck.DeckInputMapper;
import domain.deck.DeckOutputMapper;

public class UploadDeckCommand extends AbstractCommand {

	public UploadDeckCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void process() throws CommandException {
		try {
			
					String deckString = helper.getString("deck");
					//check if empty
					if(deckString.equals("") || deckString ==null){
						throw new CommandException("Please enter 40 cards");
						//						request.setAttribute("message", "Please enter 40 cards.");
//						request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
					}
					//check if exactly 40
					String deckArray[] = deckString.trim().split("\n");
					if(deckArray.length!=40){
						throw new CommandException("Please enter 40 cards. You have entered only "+deckArray.length);
//						r.setAttribute("message", "Please enter 40 cards. You have entered "+deckArray.length);
//						request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
					}
					else{
						//check first character is e, p or t
						//check if 3 charachter onward is a
						try{
						String type="";
						String name = "";
						
						//HashMap <String, String> cardList = new HashMap<String,String>();
						for(int i=0; i<deckArray.length; i++){
							if(deckArray[i].startsWith("e")||deckArray[i].startsWith("p")||deckArray[i].startsWith("t")){
								 type = deckArray[i].substring(0, 1);
								 name = deckArray[i].substring(3).replace("\"", "");
								 deckArray[i] = type+" "+name;
							}
							else{
								throw new CommandException("Problem with upload format");
//								request.setAttribute("message", "Problem with upload format.");
//								request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
							}
						}
						
						
						//insert
						Long deckID = DeckInputMapper.getMaxDeckID();
						Deck newDeck = new Deck(deckID,(long)helper.getSessionAttribute("id"));
						DeckOutputMapper.insert(newDeck);
						//rdg.insert();
						//CardRDG card=null;
						Card card =null;
							for(String i : deckArray){
							card = new Card(CardInputMapper.getMaxCardID(),deckID,i.substring(0, 1),i.substring(2),0);//0 because in deck
							CardOutputMapper.insert(card);
							}
							helper.setRequestAttribute("message", "Added new deck");
							//request.getRequestDispatcher("WEB-INF/jsp/Success.jsp").forward(request, response);	
							
						}catch(Exception e){
							e.printStackTrace();
							throw new CommandException("Problem with upload format");

//							request.setAttribute("message", "Problem with upload format.");
//							request.getRequestDispatcher("WEB-INF/jsp/Failure.jsp").forward(request, response);
						}
					}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);

		}
	}

}
