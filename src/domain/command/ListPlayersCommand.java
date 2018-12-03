package domain.command;

import java.util.ArrayList;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;

import domain.user.User;
import domain.user.UserInputMapper;

public class ListPlayersCommand extends AbstractCommand {

	public ListPlayersCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void process() throws CommandException {
		try {
			
			ArrayList<User> users = UserInputMapper.findAll();
			helper.setRequestAttribute("players", users);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);

		}
	}

}
