package domain.command;

import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;

public class AbstractCommand extends ValidatorCommand {

	public AbstractCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setUp() throws CommandException {
		// TODO Auto-generated method stub

	}

	@Override
	public void process() throws CommandException {
		// TODO Auto-generated method stub

	}

	@Override
	public void tearDown() throws CommandError {
		// TODO Auto-generated method stub

	}

}
