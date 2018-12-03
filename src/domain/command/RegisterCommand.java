package domain.command;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;

import domain.user.User;
import domain.user.UserInputMapper;
import domain.user.UserOutputMapper;

public class RegisterCommand extends AbstractCommand{

	public RegisterCommand(Helper helper) {
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
				
				if(user != null) {
					throw new CommandException("This username already exists.");
				}else {
				
				user = new User(UserInputMapper.getMaxID(),1,username,password);//initial version set to 1
				UserOutputMapper.insert(user);
				
				helper.setSessionAttribute("id", user.getId());
				helper.setRequestAttribute("message", "You have successfully registered");
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);

		}
	}
	
}
