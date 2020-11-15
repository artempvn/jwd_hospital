package by.artempvn.hospital.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.controller.JspPage;
import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.RequestAttributeName;
import by.artempvn.hospital.controller.command.RequestParameterName;
import by.artempvn.hospital.controller.command.Router;
import by.artempvn.hospital.controller.command.SessionAttributeName;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.entity.PatientData;
import by.artempvn.hospital.model.service.PatientService;
import by.artempvn.hospital.model.service.impl.PatientServiceImpl;

/**
 * The Class CreatePatientCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class CreatePatientCommand implements Command {
	private static final String SUCCESS_MESSAGE = "true";
	private static final String ERROR_MESSAGE_INCORRECT = "incorrect";
	private static final Logger logger = LogManager
			.getLogger(CreatePatientCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		pageData.setPage(JspPage.CREATE_PATIENT);
		if (data.getParameterValue(RequestParameterName.NAME) != null
				&& data.getParameterValue(RequestParameterName.SURNAME) != null
				&& data.getParameterValue(RequestParameterName.SECOND_NAME) != null
				&& data.getParameterValue(RequestParameterName.BIRTH_DATE) != null
				&& data.getParameterValue(RequestParameterName.ADMISSION_DATE) != null
				&& data.getParameterValue(
						RequestParameterName.ADMISSION_DIAGNOSIS) != null) {
			String name = data.getParameterValue(RequestParameterName.NAME)[0];
			String surname = data.getParameterValue(RequestParameterName.SURNAME)[0];
			String secondName = data
					.getParameterValue(RequestParameterName.SECOND_NAME)[0];
			String dateBirth = data
					.getParameterValue(RequestParameterName.BIRTH_DATE)[0];
			String admissionDate = data
					.getParameterValue(RequestParameterName.ADMISSION_DATE)[0];
			String admissionDiagnosis = data
					.getParameterValue(RequestParameterName.ADMISSION_DIAGNOSIS)[0];
			PatientData patientData = new PatientData();
			patientData.setName(name);
			patientData.setSurname(surname);
			patientData.setSecondName(secondName);
			patientData.setDateBirth(dateBirth);
			patientData.setAdmissionDate(admissionDate);
			patientData.setAdmissionDiagnosis(admissionDiagnosis);
			PatientService patientService = PatientServiceImpl.getInstance();
			PatientData checkedPatientData;
			try {
				checkedPatientData = patientService.addPatient(patientData);
				if (checkedPatientData.isCorrectData()) {
					data.putSessionAttribute(SessionAttributeName.SUCCESS_MESSAGE,
							SUCCESS_MESSAGE);
					pageData.setRedirect();
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
		if (checkedPatientData.getName() != null) {
			data.putRequestAttribute(RequestAttributeName.NAME,
					checkedPatientData.getName());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_NAME,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedPatientData.getSurname() != null) {
			data.putRequestAttribute(RequestAttributeName.SURNAME,
					checkedPatientData.getSurname());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_SURNAME,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedPatientData.getSecondName() != null) {
			data.putRequestAttribute(RequestAttributeName.SECOND_NAME,
					checkedPatientData.getSecondName());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_SECOND_NAME,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedPatientData.getDateBirth() != null) {
			data.putRequestAttribute(RequestAttributeName.BIRTH_DATE,
					checkedPatientData.getDateBirth());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_DATE,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedPatientData.getAdmissionDate() != null) {
			data.putRequestAttribute(RequestAttributeName.ADMISSION_DATE,
					checkedPatientData.getAdmissionDate());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_ADMISSION_DATE,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedPatientData.getAdmissionDiagnosis() != null) {
			data.putRequestAttribute(RequestAttributeName.ADMISSION_DIAGNOSIS,
					checkedPatientData.getAdmissionDiagnosis());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_ADMISSION_DIAGNOSIS,
					ERROR_MESSAGE_INCORRECT);
		}
	}

}
