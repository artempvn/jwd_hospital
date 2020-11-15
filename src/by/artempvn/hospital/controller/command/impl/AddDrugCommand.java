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
import by.artempvn.hospital.model.entity.DrugData;
import by.artempvn.hospital.model.service.DrugService;
import by.artempvn.hospital.model.service.impl.DrugServiceImpl;

/**
 * The Class AddDrugCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class AddDrugCommand implements Command, PatientIdParameterCheckable {
	private static final String SUCCESS_MESSAGE = "true";
	private static final String ERROR_MESSAGE_INCORRECT = "incorrect";
	private static final Logger logger = LogManager
			.getLogger(AddDrugCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		pageData.setPage(JspPage.ADD_PURPOSE);
		putPatientIdAttribute(data);
		if (data.getParameterValue(RequestParameterName.DRUG_NAME) != null
				&& data.getParameterValue(RequestParameterName.AMOUNT) != null
				&& data.getParameterValue(RequestParameterName.APPOINTMENT_TIME) != null
				&& isPresentPatientIdParameter(data)) {
			String name = data.getParameterValue(RequestParameterName.DRUG_NAME)[0];
			String amount = data.getParameterValue(RequestParameterName.AMOUNT)[0];
			String appointmentTime = data
					.getParameterValue(RequestParameterName.APPOINTMENT_TIME)[0];
			long patientId = Long.valueOf((String) data
					.getSessionAttributeValue((SessionAttributeName.PATIENT_ID)));
			DrugData drugData = new DrugData();
			drugData.setName(name);
			drugData.setAmount(amount);
			drugData.setAppointmentTime(appointmentTime);
			drugData.setPatientId(patientId);
			DrugService drugService = DrugServiceImpl.getInstance();
			DrugData checkedDrugData;
			try {
				checkedDrugData = drugService.addDrug(drugData);
				if (checkedDrugData.isCorrectData()) {
					data.putSessionAttribute(SessionAttributeName.SUCCESS_MESSAGE,
							SUCCESS_MESSAGE);
					pageData.setRedirect();
				} else {
					logger.log(Level.WARN, "Incorrect input");
					putCorrectInputValuesBack(data, checkedDrugData);
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
			DrugData checkedDrugData) {
		if (checkedDrugData.getName() != null) {
			data.putRequestAttribute(RequestAttributeName.DRUG_NAME,
					checkedDrugData.getName());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_DRUG_NAME,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedDrugData.getAmount() != null) {
			data.putRequestAttribute(RequestAttributeName.AMOUNT,
					checkedDrugData.getAmount());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_AMOUNT,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedDrugData.getAppointmentTime() != null) {
			data.putRequestAttribute(RequestAttributeName.APPOINTMENT_TIME,
					checkedDrugData.getAppointmentTime());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_APPOINTMENT_TIME,
					ERROR_MESSAGE_INCORRECT);
		}
		if (!checkedDrugData.isDiagnosisEstablished()) {
			data.putRequestAttribute(RequestAttributeName.ERROR_DIAGNOSIS,
					ERROR_MESSAGE_INCORRECT);
		}
	}

}
