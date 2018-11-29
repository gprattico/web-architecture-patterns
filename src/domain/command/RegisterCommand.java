package domain.command;

import java.sql.SQLException;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;

import domain.user.UserInputMapper;

public class RegisterCommand extends ValidatorCommand{

	public RegisterCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		try {
			
			String username = helper.getString("user");
			String password = helper.getString("password");
			
			if(username == null || password == null || username.isEmpty() || password.isEmpty())
				throw new CommandException("Missing username or password.");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);

		}
	}
	
}
