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
import by.artempvn.hospital.model.entity.DiagnosisData;
import by.artempvn.hospital.model.entity.PatientData;
import by.artempvn.hospital.model.service.DiagnosisService;
import by.artempvn.hospital.model.service.PatientService;
import by.artempvn.hospital.model.service.impl.DiagnosisServiceImpl;
import by.artempvn.hospital.model.service.impl.PatientServiceImpl;

/**
 * The Class EstablishDiagnosisCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class EstablishDiagnosisCommand
		implements Command, PatientIdParameterCheckable {
	private static final String SUCCESS_MESSAGE = "true";
	private static final String ERROR_MESSAGE_INCORRECT = "incorrect";
	private static final Logger logger = LogManager
			.getLogger(EstablishDiagnosisCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		pageData.setPage(JspPage.ESTABLISH_DIAGNOSIS);
		putPatientIdAttribute(data);
		if (isPresentPatientIdParameter(data)) {
			try {
				long patientId = Long.parseLong(((String) data
						.getSessionAttributeValue((SessionAttributeName.PATIENT_ID))));
				PatientService patientService = PatientServiceImpl.getInstance();
				PatientData patient = patientService.takePatient(patientId);
				data.putRequestAttribute(RequestAttributeName.PATIENT, patient);
				if (data.getParameterValue(RequestParameterName.NAME) != null
						&& data.getParameterValue(RequestParameterName.DATE) != null) {
					String name = data.getParameterValue(RequestParameterName.NAME)[0];
					String date = data.getParameterValue(RequestParameterName.DATE)[0];
					String establishedDoctor = (String) data
							.getSessionAttributeValue(SessionAttributeName.LOGIN);
					DiagnosisData diagnosisData = new DiagnosisData();
					diagnosisData.setName(name);
					diagnosisData.setDate(date);
					diagnosisData.setPatientId(patientId);
					diagnosisData.setEstablishedDoctor(establishedDoctor);
					DiagnosisService diagnosisService = DiagnosisServiceImpl
							.getInstance();
					DiagnosisData checkedDiagnosisData;
					checkedDiagnosisData = diagnosisService.addDiagnosis(diagnosisData);
					if (checkedDiagnosisData.isCorrectData()) {
						data.putSessionAttribute(SessionAttributeName.SUCCESS_MESSAGE,
								SUCCESS_MESSAGE);
						pageData.setRedirect();
					} else {
						logger.log(Level.WARN, "Incorrect input");
						putCorrectInputValuesBack(data, checkedDiagnosisData);
					}
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
			DiagnosisData checkedDiagnosisData) {
		if (checkedDiagnosisData.getName() != null) {
			data.putRequestAttribute(RequestAttributeName.NAME,
					checkedDiagnosisData.getName());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_NAME,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedDiagnosisData.getDate() != null) {
			data.putRequestAttribute(RequestAttributeName.DATE,
					checkedDiagnosisData.getDate());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_DATE,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedDiagnosisData.getPatientId() > 0) {
			data.putRequestAttribute(RequestAttributeName.PATIENT_ID,
					checkedDiagnosisData.getPatientId());
		}
	}

}
