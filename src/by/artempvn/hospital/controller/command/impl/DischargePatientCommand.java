package by.artempvn.hospital.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.controller.JspPage;
import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.PatientIdParameterCheckable;
import by.artempvn.hospital.controller.command.RequestAttributeName;
import by.artempvn.hospital.controller.command.RequestParameterName;
import by.artempvn.hospital.controller.command.Router;
import by.artempvn.hospital.controller.command.SessionAttributeName;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.entity.PatientData;
import by.artempvn.hospital.model.service.PatientService;
import by.artempvn.hospital.model.service.impl.PatientServiceImpl;

/**
 * The Class DischargePatientCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class DischargePatientCommand
		implements Command, PatientIdParameterCheckable {
	private static final String ERROR_MESSAGE_INCORRECT = "incorrect";
	private static final String TRUE_MESSAGE = "true";
	private static final Logger logger = LogManager
			.getLogger(DischargePatientCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		pageData.setPage(JspPage.DISCHARGE_PATIENT);
		putPatientIdAttribute(data);
		if (data.getParameterValue(RequestParameterName.TREATMENT_RESULT) != null
				&& data.getParameterValue(RequestParameterName.DATE) != null
				&& isPresentPatientIdParameter(data)) {
			long patientId = Long.valueOf((String) data
					.getSessionAttributeValue((SessionAttributeName.PATIENT_ID)));
			String treatmentResult = data
					.getParameterValue(RequestParameterName.TREATMENT_RESULT)[0];
			String date = data.getParameterValue(RequestParameterName.DATE)[0];
			PatientData patientData = new PatientData();
			patientData.setId(patientId);
			patientData.setTreatmentResult(treatmentResult);
			patientData.setDischargeDate(date);
			PatientService patientService = PatientServiceImpl.getInstance();
			PatientData checkedPatientData;
			try {
				checkedPatientData = patientService.dischargePatient(patientData);
				if (checkedPatientData.isCorrectData()) {
					if (checkedPatientData.isDischarged()) {
						data.putSessionAttribute(SessionAttributeName.SUCCESS_MESSAGE,
								TRUE_MESSAGE);
						pageData.setRedirect();
					} else {
						data.putRequestAttribute(RequestAttributeName.FAIL_MESSAGE,
								TRUE_MESSAGE);
					}
				} else {
					logger.log(Level.WARN, "Incorrect input");
					putCorrectInputValuesBack(data, checkedPatientData);
				}
			} catch (ServiceException ex) {
				logger.log(Level.ERROR, ex);
				data.putRequestAttribute(RequestAttributeName.ERROR, ex);
				pageData.setPage(JspPage.ERROR);
			}
		}
		return pageData;
	}

	private void putCorrectInputValuesBack(RequestData data,
			PatientData checkedPatientData) {
		if (checkedPatientData.getTreatmentResult() != null) {
			data.putRequestAttribute(RequestAttributeName.TREATMENT_RESULT,
					checkedPatientData.getTreatmentResult());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_TREATMENT_RESULT,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedPatientData.getDischargeDate() != null) {
			data.putRequestAttribute(RequestAttributeName.DATE,
					checkedPatientData.getDischargeDate());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_DATE,
					ERROR_MESSAGE_INCORRECT);
		}
	}

}
