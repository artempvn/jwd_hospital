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
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.service.impl.UserServiceImpl;

/**
 * The Class UnbanUserCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class UnbanUserCommand implements Command, CurrentPageReturnable {
	private static final String CONNECTION_ERROR = "connection_error";
	private static final Logger logger = LogManager
			.getLogger(UnbanUserCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		pageData.setPage(returnCurrentPage(data));
		if (data.getParameterValue(RequestParameterName.LOGIN) != null) {
			String login = data.getParameterValue(RequestParameterName.LOGIN)[0];
			try {
				UserServiceImpl service = UserServiceImpl.getInstance();
				service.unbanUser(login);
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
