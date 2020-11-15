package by.artempvn.hospital.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.controller.JspPage;
import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.PatientIdParameterCheckable;
import by.artempvn.hospital.controller.command.RequestAttributeName;
import by.artempvn.hospital.controller.command.Router;
import by.artempvn.hospital.controller.command.SessionAttributeName;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.entity.PatientData;
import by.artempvn.hospital.model.service.PatientService;
import by.artempvn.hospital.model.service.impl.PatientServiceImpl;

/**
 * The Class AddPurposeCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class AddPurposeCommand implements Command, PatientIdParameterCheckable {
	private static final Logger logger = LogManager
			.getLogger(AddPurposeCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		pageData.setPage(JspPage.ADD_PURPOSE);
		putPatientIdAttribute(data);
		if (isPresentPatientIdParameter(data)) {
			long patientId = Long.parseLong(((String) data
					.getSessionAttributeValue((SessionAttributeName.PATIENT_ID))));
			PatientService patientService = PatientServiceImpl.getInstance();
			try {
				PatientData patient = patientService.takePatient(patientId);
				data.putRequestAttribute(RequestAttributeName.PATIENT, patient);
			} catch (ServiceException ex) {
				logger.log(Level.ERROR, ex);
				data.putRequestAttribute(RequestAttributeName.ERROR, ex);
				pageData.setPage(JspPage.ERROR);
			}
		}
		return pageData;
	}

}
