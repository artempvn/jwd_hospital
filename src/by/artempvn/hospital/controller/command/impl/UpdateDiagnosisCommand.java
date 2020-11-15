package by.artempvn.hospital.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.controller.JspPage;
import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.CurrentPageReturnable;
import by.artempvn.hospital.controller.command.PatientIdParameterCheckable;
import by.artempvn.hospital.controller.command.RequestAttributeName;
import by.artempvn.hospital.controller.command.RequestParameterName;
import by.artempvn.hospital.controller.command.Router;
import by.artempvn.hospital.controller.command.SessionAttributeName;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.entity.DiagnosisData;
import by.artempvn.hospital.model.service.DiagnosisService;
import by.artempvn.hospital.model.service.impl.DiagnosisServiceImpl;

/**
 * The Class UpdateDiagnosisCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class UpdateDiagnosisCommand
		implements Command, PatientIdParameterCheckable, CurrentPageReturnable {
	private static final String SUCCESS_MESSAGE = "true";
	private static final String ERROR_MESSAGE_INCORRECT = "incorrect";
	private static final Logger logger = LogManager
			.getLogger(UpdateDiagnosisCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		pageData.setPage(returnCurrentPage(data));
		putPatientIdAttribute(data);
		if (data.getParameterValue(RequestParameterName.NAME) != null
				&& isPresentPatientIdParameter(data)) {
			String name = data.getParameterValue(RequestParameterName.NAME)[0];
			long patientId = Long.valueOf((String) data
					.getSessionAttributeValue((SessionAttributeName.PATIENT_ID)));
			DiagnosisData diagnosisData = new DiagnosisData();
			diagnosisData.setName(name);
			diagnosisData.setPatientId(patientId);
			DiagnosisService diagnosisService = DiagnosisServiceImpl.getInstance();
			try {
				boolean isUpdated = diagnosisService.updateDiagnosis(diagnosisData);
				if (isUpdated) {
					data.putSessionAttribute(SessionAttributeName.SUCCESS_MESSAGE,
							SUCCESS_MESSAGE);
					pageData.setRedirect();
				} else {
					logger.log(Level.WARN, "Incorrect input");
					data.putRequestAttribute(RequestAttributeName.ERROR_NAME,
							ERROR_MESSAGE_INCORRECT);
				}
			} catch (ServiceException ex) {
				logger.log(Level.ERROR, ex);
				data.putRequestAttribute(RequestAttributeName.ERROR, ex);
				pageData.setPage(JspPage.ERROR);
			}
		}
		return pageData;
	}

}
