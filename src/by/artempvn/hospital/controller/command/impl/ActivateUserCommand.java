package by.artempvn.hospital.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.CurrentPageReturnable;
import by.artempvn.hospital.controller.command.RequestAttributeName;
import by.artempvn.hospital.controller.command.RequestParameterName;
import by.artempvn.hospital.controller.command.Router;
import by.artempvn.hospital.controller.command.SessionAttributeName;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.service.impl.UserServiceImpl;

/**
 * The Class ActivateUserCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class ActivateUserCommand implements Command, CurrentPageReturnable {
	private static final String CONNECTION_ERROR = "connection_error";
	private static final String STATUS_CHANGED = "true";
	private static final String STATUS_NOT_CHANGED = "false";
	private static final Logger logger = LogManager
			.getLogger(ActivateUserCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		pageData.setPage(returnCurrentPage(data));
		if (data.getParameterValue(RequestParameterName.LOGIN) != null) {
			String login = data.getParameterValue(RequestParameterName.LOGIN)[0];
			try {
				UserServiceImpl service = UserServiceImpl.getInstance();
				if (service.activateUser(login)) {
					data.putSessionAttribute(SessionAttributeName.STATUS_MESSAGE,
							STATUS_CHANGED);
				} else {
					data.putSessionAttribute(SessionAttributeName.STATUS_MESSAGE,
							STATUS_NOT_CHANGED);
				}
				pageData.setRedirect();
			} catch (ServiceException ex) {
				logger.log(Level.ERROR, ex);
				data.putRequestAttribute(RequestAttributeName.STATUS_MESSAGE,
						CONNECTION_ERROR);
			}
		}
		return pageData;
	}

}
