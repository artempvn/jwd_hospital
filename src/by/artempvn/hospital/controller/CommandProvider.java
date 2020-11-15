package by.artempvn.hospital.controller;

import java.util.Arrays;
import java.util.Optional;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.CommandType;
import by.artempvn.hospital.controller.command.RequestParameterName;
import by.artempvn.hospital.controller.command.impl.EmptyCommand;

/**
 * The Class CommandProvider.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class CommandProvider {

	private CommandProvider() {
	}

	/**
	 * Define command.
	 *
	 * @param data the data
	 * @return the command
	 */
	public static Command defineCommand(RequestData data) {
		if (data.getParameterValue(RequestParameterName.COMMAND) == null) {
			return new EmptyCommand();
		}
		String requestedCommand = data
				.getParameterValue(RequestParameterName.COMMAND)[0];
		Optional<Command> command = Arrays.stream(CommandType.values()).filter(
				commandType -> commandType.name().equalsIgnoreCase(requestedCommand))
				.findFirst().map(CommandType::getCommand);
		return command.isPresent() ? command.get() : new EmptyCommand();
	}

}
