package by.artempvn.hospital.controller.command.impl;

import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.CurrentPageReturnable;
import by.artempvn.hospital.controller.command.Router;
import by.artempvn.hospital.controller.command.SessionAttributeName;

/**
 * The Class SetLocaleEnCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class SetLocaleEnCommand implements Command, CurrentPageReturnable {
	private static final String LOCALE_EN = "en";

	@Override
	public Router execute(RequestData data) {
		data.putSessionAttribute(SessionAttributeName.LOCALE, LOCALE_EN);
		Router pageData = new Router();
		pageData.setPage(returnCurrentPage(data));
		return pageData;
	}

}
