package by.artempvn.hospital.controller.command.impl;

import by.artempvn.hospital.controller.JspPage;
import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.Router;
import by.artempvn.hospital.controller.command.SessionAttributeName;

/**
 * The Class BackToPreviousCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class BackToPreviousCommand implements Command {

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		String currentPage = (String) data
				.getSessionAttributeValue(SessionAttributeName.PREVIOUS_COMMAND);
		if (currentPage == null) {
			currentPage = JspPage.INDEX;
		} else {
			data.putSessionAttribute(SessionAttributeName.CURRENT_COMMAND,
					currentPage);
		}
		pageData.setPage(currentPage);
		return pageData;
	}

}
