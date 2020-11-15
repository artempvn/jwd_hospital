package by.artempvn.hospital.controller.command;

import by.artempvn.hospital.controller.RequestData;

/**
 * The Interface Command.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface Command {

	/**
	 * Execute.
	 *
	 * @param data the data
	 * @return the router
	 */
	Router execute(RequestData data);

}
