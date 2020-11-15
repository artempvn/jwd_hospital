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
import by.artempvn.hospital.model.service.DiagnosisService;
import by.artempvn.hospital.model.service.PatientService;
import by.artempvn.hospital.model.service.impl.DiagnosisServiceImpl;
import by.artempvn.hospital.model.service.impl.PatientServiceImpl;

/**
 * The Class ShowDiagnosisCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class ShowDiagnosisCommand
		implements Command, PatientIdParameterCheckable {
	private static final Logger logger = LogManager
			.getLogger(ShowDiagnosisCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		pageData.setPage(JspPage.SHOW_DIAGNOSIS);
		putPatientIdAttribute(data);
		if (isPresentPatientIdParameter(data)) {
			long patientId = Long.parseLong(((String) data
					.getSessionAttributeValue((SessionAttributeName.PATIENT_ID))));
			PatientService patientService = PatientServiceImpl.getInstance();
			DiagnosisService service = DiagnosisServiceImpl.getInstance();
			try {
				PatientData patient = patientService.takePatient(patientId);
				data.putRequestAttribute(RequestAttributeName.PATIENT, patient);
				Map<String, Object> diagnosisData = service
						.takeDiagnosisDoctorData(patientId);
				data.putRequestAttribute(RequestAttributeName.DIAGNOSIS, diagnosisData);
			} catch (ServiceException ex) {
				logger.log(Level.ERROR, ex);
				data.putRequestAttribute(RequestAttributeName.ERROR, ex);
				pageData.setPage(JspPage.ERROR);
			}
		}
		return pageData;
	}

}
