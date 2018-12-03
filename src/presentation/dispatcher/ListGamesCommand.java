package presentation.dispatcher;

import java.util.ArrayList;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;

import domain.command.AbstractCommand;
import domain.game.Game;
import domain.game.GameInputMapper;

public class ListGamesCommand extends AbstractCommand {

	public ListGamesCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		
		try {
		ArrayList<Game> gameList = GameInputMapper.findAll();
		helper.setRequestAttribute("gameList", gameList);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CommandException(e.getMessage());
			
		}
	}
	
}
