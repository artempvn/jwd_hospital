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
import by.artempvn.hospital.model.entity.ProcedureData;
import by.artempvn.hospital.model.service.ProcedureService;
import by.artempvn.hospital.model.service.impl.ProcedureServiceImpl;

/**
 * The Class AddProcedureCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class AddProcedureCommand
		implements Command, PatientIdParameterCheckable {
	private static final String SUCCESS_MESSAGE = "true";
	private static final String ERROR_MESSAGE_INCORRECT = "incorrect";
	private static final Logger logger = LogManager
			.getLogger(AddProcedureCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		pageData.setPage(JspPage.ADD_PURPOSE);
		putPatientIdAttribute(data);
		if (data.getParameterValue(RequestParameterName.TYPE) != null
				&& data.getParameterValue(RequestParameterName.PROCEDURE_NAME) != null
				&& data.getParameterValue(RequestParameterName.DATE_START) != null
				&& data.getParameterValue(RequestParameterName.DATE_END) != null
				&& isPresentPatientIdParameter(data)) {
			String type = data.getParameterValue(RequestParameterName.TYPE)[0];
			String name = data
					.getParameterValue(RequestParameterName.PROCEDURE_NAME)[0];
			String dateStart = data
					.getParameterValue(RequestParameterName.DATE_START)[0];
			String dateEnd = data.getParameterValue(RequestParameterName.DATE_END)[0];
			long patientId = Long.valueOf((String) data
					.getSessionAttributeValue((SessionAttributeName.PATIENT_ID)));
			ProcedureData procedureData = new ProcedureData();
			procedureData.setName(name);
			procedureData.setDateStart(dateStart);
			procedureData.setDateEnd(dateEnd);
			procedureData.setType(type);
			procedureData.setPatientId(patientId);
			ProcedureService procedureService = ProcedureServiceImpl.getInstance();
			ProcedureData checkedProcedureData;
			try {
				checkedProcedureData = procedureService.addProcedure(procedureData);
				if (checkedProcedureData.isCorrectData()) {
					data.putSessionAttribute(SessionAttributeName.SUCCESS_MESSAGE,
							SUCCESS_MESSAGE);
					pageData.setRedirect();
					if (checkedProcedureData.isDoctorRemoved()) {
						data.putSessionAttribute(
								SessionAttributeName.DOCTOR_REMOVED_MESSAGE, SUCCESS_MESSAGE);
					}
				} else {
					logger.log(Level.WARN, "Incorrect input");
					putCorrectInputValuesBack(data, checkedProcedureData);
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
			ProcedureData checkedProcedureData) {
		if (checkedProcedureData.getType() != null) {
			data.putRequestAttribute(RequestAttributeName.TYPE,
					checkedProcedureData.getType());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_TYPE,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedProcedureData.getName() != null) {
			data.putRequestAttribute(RequestAttributeName.PROCEDURE_NAME,
					checkedProcedureData.getName());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_PROCEDURE_NAME,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedProcedureData.getDateStart() != null) {
			data.putRequestAttribute(RequestAttributeName.DATE_START,
					checkedProcedureData.getDateStart());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_DATE_START,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedProcedureData.getDateEnd() != null) {
			data.putRequestAttribute(RequestAttributeName.DATE_END,
					checkedProcedureData.getDateEnd());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_DATE_END,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedProcedureData.getPatientId() > 0) {
			data.putRequestAttribute(RequestAttributeName.PATIENT_ID,
					checkedProcedureData.getPatientId());
		}
		if (!checkedProcedureData.isDiagnosisEstablished()) {
			data.putRequestAttribute(RequestAttributeName.ERROR_DIAGNOSIS,
					ERROR_MESSAGE_INCORRECT);
		}
	}

}
