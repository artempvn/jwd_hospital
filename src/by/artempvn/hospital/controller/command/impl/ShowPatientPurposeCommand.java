package by.artempvn.hospital.controller.command.impl;

import java.util.Map;
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
import by.artempvn.hospital.model.service.PurposeService;
import by.artempvn.hospital.model.service.impl.PatientServiceImpl;
import by.artempvn.hospital.model.service.impl.PurposeServiceImpl;

/**
 * The Class ShowPatientPurposeCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class ShowPatientPurposeCommand
		implements Command, PatientIdParameterCheckable {
	private static final Logger logger = LogManager
			.getLogger(ShowPatientPurposeCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		pageData.setPage(JspPage.SHOW_PATIENT_PURPOSE);
		putPatientIdAttribute(data);
		if (isPresentPatientIdParameter(data)) {
			long patientId = Long.valueOf((String) data
					.getSessionAttributeValue((SessionAttributeName.PATIENT_ID)));
			PurposeService service = PurposeServiceImpl.getInstance();
			PatientService patientService = PatientServiceImpl.getInstance();
			try {
				PatientData patient = patientService.takePatient(patientId);
				data.putRequestAttribute(RequestAttributeName.PATIENT, patient);
				Map<String, Object> purposeData = service.takePurpose(patientId);
				data.putRequestAttribute(RequestAttributeName.PURPOSE, purposeData);
			} catch (ServiceException ex) {
				logger.log(Level.ERROR, ex);
				data.putRequestAttribute(RequestAttributeName.ERROR, ex);
				pageData.setPage(JspPage.ERROR);
			}
		}
		return pageData;
	}

}
