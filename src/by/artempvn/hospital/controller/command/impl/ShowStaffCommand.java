package by.artempvn.hospital.controller.command.impl;

import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.controller.JspPage;
import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.RequestAttributeName;
import by.artempvn.hospital.controller.command.Router;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.entity.UserData;
import by.artempvn.hospital.model.service.impl.MedicalStaffServiceImpl;

/**
 * The Class ShowStaffCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class ShowStaffCommand implements Command {
	private static final Logger logger = LogManager
			.getLogger(ShowStaffCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		MedicalStaffServiceImpl service = MedicalStaffServiceImpl.getInstance();
		List<UserData> staff;
		try {
			staff = service.takeMedicalStaff(false);
			data.putRequestAttribute(RequestAttributeName.STAFF, staff);
			pageData.setPage(JspPage.SHOW_STAFF);
		} catch (ServiceException ex) {
			logger.log(Level.ERROR, ex);
			data.putRequestAttribute(RequestAttributeName.ERROR, ex);
			pageData.setPage(JspPage.ERROR);
		}
		return pageData;
	}

}
