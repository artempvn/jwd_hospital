package by.artempvn.hospital.controller.command.impl;

import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.Router;

/**
 * The Class EmptyCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class EmptyCommand implements Command {

	@Override
	public Router execute(RequestData data) {
		return new Router();
	}

}
