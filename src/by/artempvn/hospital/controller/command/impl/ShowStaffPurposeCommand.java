package by.artempvn.hospital.controller.command.impl;

import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.controller.JspPage;
import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.RequestAttributeName;
import by.artempvn.hospital.controller.command.Router;
import by.artempvn.hospital.controller.command.SessionAttributeName;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.service.PurposeService;
import by.artempvn.hospital.model.service.impl.PurposeServiceImpl;

/**
 * The Class ShowStaffPurposeCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class ShowStaffPurposeCommand implements Command {
	private static final Logger logger = LogManager
			.getLogger(ShowStaffPurposeCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		pageData.setPage(JspPage.SHOW_STAFF_PURPOSE);
		String currentUser = (String) data
				.getSessionAttributeValue(SessionAttributeName.LOGIN);
		PurposeService service = PurposeServiceImpl.getInstance();
		try {
			Map<String, Object> purposeData = service.takePurposes(currentUser);
			data.putRequestAttribute(RequestAttributeName.PURPOSE, purposeData);
		} catch (ServiceException ex) {
			logger.log(Level.ERROR, ex);
			data.putRequestAttribute(RequestAttributeName.ERROR, ex);
			pageData.setPage(JspPage.ERROR);
		}
		return pageData;
	}

}
