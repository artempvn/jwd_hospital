package by.artempvn.hospital.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.controller.JspPage;
import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.DoctorLoginParameterCheckable;
import by.artempvn.hospital.controller.command.RequestAttributeName;
import by.artempvn.hospital.controller.command.Router;
import by.artempvn.hospital.controller.command.SessionAttributeName;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.entity.UserData;
import by.artempvn.hospital.model.service.MedicalStaffService;
import by.artempvn.hospital.model.service.impl.MedicalStaffServiceImpl;

/**
 * The Class ShowDoctorCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class ShowDoctorCommand
		implements Command, DoctorLoginParameterCheckable {
	private static final Logger logger = LogManager
			.getLogger(ShowDoctorCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		pageData.setPage(JspPage.SHOW_DOCTOR);
		putDoctorLoginAttribute(data);
		if (isPresentDoctorLoginParameter(data)) {
			String login = (String) data
					.getSessionAttributeValue(SessionAttributeName.DOCTOR_LOGIN);
			MedicalStaffService service = MedicalStaffServiceImpl.getInstance();
			try {
				UserData doctor = service.takeMedicalStaffByLogin(login);
				data.putRequestAttribute(RequestAttributeName.STAFF, doctor);
			} catch (ServiceException ex) {
				logger.log(Level.ERROR, ex);
				data.putRequestAttribute(RequestAttributeName.ERROR, ex);
				pageData.setPage(JspPage.ERROR);
			}
		}
		return pageData;
	}

}
