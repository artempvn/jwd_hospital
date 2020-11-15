package by.artempvn.hospital.controller.command.impl;

import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.CurrentPageReturnable;
import by.artempvn.hospital.controller.command.Router;
import by.artempvn.hospital.controller.command.SessionAttributeName;

/**
 * The Class SetLocaleRuCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class SetLocaleRuCommand implements Command, CurrentPageReturnable {
	private static final String LOCALE_RU = "ru";

	@Override
	public Router execute(RequestData data) {
		data.putSessionAttribute(SessionAttributeName.LOCALE, LOCALE_RU);
		Router pageData = new Router();
		pageData.setPage(returnCurrentPage(data));
		return pageData;
	}

}
