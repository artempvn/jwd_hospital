package by.artempvn.hospital.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.controller.JspPage;
import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.RequestAttributeName;
import by.artempvn.hospital.controller.command.RequestParameterName;
import by.artempvn.hospital.controller.command.Router;
import by.artempvn.hospital.controller.command.SessionAttributeName;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.service.impl.UserServiceImpl;

/**
 * The Class LoginCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class LoginCommand implements Command {
	private static final String DEFAULT_LOCALE = "ru";
	private static final String CONNECTION_ERROR_MESSAGE = "connection_error";
	private static final String INVALID_MESSAGE = "invalid";
	private static final String BANNED_STATUS_MESSAGE = "banned_status";
	private static final String REGISTERED_STATUS_MESSAGE = "registered_status";
	private static final String STATUS_ACTIVATED = "activated";
	private static final String STATUS_BANNED = "banned";
	private static final String STATUS_REGISTERED = "registered";
	private static final Logger logger = LogManager.getLogger(LoginCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		if (data.getParameterValue(RequestParameterName.LOGIN) != null
				&& data.getParameterValue(RequestParameterName.PASSWORD) != null) {
			String login = data.getParameterValue(RequestParameterName.LOGIN)[0];
			String password = data
					.getParameterValue(RequestParameterName.PASSWORD)[0];
			try {
				UserServiceImpl service = UserServiceImpl.getInstance();
				if (service.checkPassword(login, password)) {
					String status = service.takeUserStatus(login);
					switch (status) {
					case STATUS_ACTIVATED:
						String language = (String) data
								.getSessionAttributeValue(SessionAttributeName.LOCALE);
						if (language == null) {
							data.putSessionAttribute(SessionAttributeName.LOCALE,
									DEFAULT_LOCALE);
						}
						data.putSessionAttribute(SessionAttributeName.LOGIN, login);
						String role = service.takeRole(login);
						data.putSessionAttribute(SessionAttributeName.ROLE, role);
						pageData.setPage(JspPage.MAIN);
						break;
					case STATUS_REGISTERED:
						data.putRequestAttribute(RequestAttributeName.ERROR_LOGIN,
								REGISTERED_STATUS_MESSAGE);
						break;
					case STATUS_BANNED:
						data.putRequestAttribute(RequestAttributeName.ERROR_LOGIN,
								BANNED_STATUS_MESSAGE);
						break;
					}
				} else {
					data.putRequestAttribute(RequestAttributeName.ERROR_LOGIN,
							INVALID_MESSAGE);
				}
			} catch (ServiceException ex) {
				logger.log(Level.ERROR, ex);
				data.putRequestAttribute(RequestAttributeName.ERROR_LOGIN,
						CONNECTION_ERROR_MESSAGE);
			}
		}
		return pageData;
	}

}
