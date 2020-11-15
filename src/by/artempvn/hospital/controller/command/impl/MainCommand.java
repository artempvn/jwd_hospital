package by.artempvn.hospital.controller.command.impl;

import by.artempvn.hospital.controller.JspPage;
import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.RequestAttributeName;
import by.artempvn.hospital.controller.command.Router;

/**
 * The Class MainCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class MainCommand implements Command {

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		String role = null;
		if (data.getSessionAttributeValue(RequestAttributeName.ROLE) != null) {
			role = data.getSessionAttributeValue(RequestAttributeName.ROLE)
					.toString();
		}
		if (role != null) {
			switch (role) {
			case RequestAttributeName.ROLE_ADMIN_VALUE:
			case RequestAttributeName.ROLE_DOCTOR_VALUE:
			case RequestAttributeName.ROLE_NURSE_VALUE:
				pageData.setPage(JspPage.MAIN);
				break;
			default:
				pageData.setPage(JspPage.INDEX);
			}
		}
		return pageData;
	}

}
