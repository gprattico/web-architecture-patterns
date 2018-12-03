package domain.command;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;

import domain.user.User;
import domain.user.UserInputMapper;

public class LoginCommand extends AbstractCommand {

	public LoginCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void process() throws CommandException {
		try {
			
			String username = helper.getString("user");
			String password = helper.getString("pass");
			
			if(username == null || password == null || username.isEmpty() || password.isEmpty()) {
				throw new CommandException("Missing username or password.");
			}else {
				
				User user = UserInputMapper.find(username);
				
				if(user ==null){
					throw new CommandException("Username or password is incorrect.");

				} else {
					helper.setSessionAttribute("id", user.getId());
					helper.setRequestAttribute("message", "You have successfully logged in.");
					
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);

		}
	}

}
