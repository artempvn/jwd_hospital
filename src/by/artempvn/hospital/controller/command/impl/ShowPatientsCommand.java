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
import by.artempvn.hospital.model.entity.PatientData;
import by.artempvn.hospital.model.service.PatientService;
import by.artempvn.hospital.model.service.impl.PatientServiceImpl;

/**
 * The Class ShowPatientsCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class ShowPatientsCommand implements Command {
	private static final Logger logger = LogManager
			.getLogger(ShowPatientsCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		PatientService service = PatientServiceImpl.getInstance();
		List<PatientData> patients;
		try {
			patients = service.takePatients();
			data.putRequestAttribute(RequestAttributeName.PATIENTS, patients);
			pageData.setPage(JspPage.SHOW_PATIENTS);
		} catch (ServiceException ex) {
			logger.log(Level.ERROR, ex);
			data.putRequestAttribute(RequestAttributeName.ERROR, ex);
			pageData.setPage(JspPage.ERROR);
		}
		return pageData;
	}

}
