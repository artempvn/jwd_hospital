package by.artempvn.hospital.controller.command.impl;

import by.artempvn.hospital.controller.JspPage;
import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.Router;

/**
 * The Class LogoutCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class LogoutCommand implements Command {

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		pageData.setPage(JspPage.INDEX);
		data.terminateSession();
		return pageData;
	}

}
