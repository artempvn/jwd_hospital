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
import by.artempvn.hospital.model.entity.PurposeData;
import by.artempvn.hospital.model.service.MedicalStaffService;
import by.artempvn.hospital.model.service.PatientService;
import by.artempvn.hospital.model.service.PurposeService;
import by.artempvn.hospital.model.service.impl.MedicalStaffServiceImpl;
import by.artempvn.hospital.model.service.impl.PatientServiceImpl;
import by.artempvn.hospital.model.service.impl.PurposeServiceImpl;

/**
 * The Class AddAttendingDoctorCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class AddAttendingDoctorCommand
		implements Command, PatientIdParameterCheckable {
	private static final String SUCCESS_MESSAGE = "true";
	private static final String ERROR_MESSAGE_INCORRECT = "incorrect";
	private static final Logger logger = LogManager
			.getLogger(AddAttendingDoctorCommand.class);

	@Override
	public Router execute(RequestData data) {
		putPatientIdAttribute(data);
		Router pageData = new Router();
		pageData.setPage(JspPage.ADD_ATTENDING_DOCTOR);
		try {
			MedicalStaffService service = MedicalStaffServiceImpl.getInstance();
			var medicalStaffList = service.takeMedicalStaff(true);
			data.putRequestAttribute(RequestAttributeName.MEDICAL_STAFF,
					medicalStaffList);
			if (isPresentPatientIdParameter(data)) {
				long patientId = Long.parseLong(((String) data
						.getSessionAttributeValue((SessionAttributeName.PATIENT_ID))));
				PatientService patientService = PatientServiceImpl.getInstance();
				PatientData patient = patientService.takePatient(patientId);
				data.putRequestAttribute(RequestAttributeName.PATIENT, patient);
				if (data
						.getParameterValue(RequestParameterName.ATTENDING_DOCTOR) != null) {
					String attendingDoctor = data
							.getParameterValue(RequestParameterName.ATTENDING_DOCTOR)[0];
					PurposeService purposeService = PurposeServiceImpl.getInstance();
					if (attendingDoctor != null && !attendingDoctor.isBlank()) {
						PurposeData purposeData = purposeService
								.changeAttendingDoctor(attendingDoctor, patientId);
						if (purposeData.isDiagnosisEstablished()) {
							if (purposeData.isSucceed()) {
								data.putSessionAttribute(SessionAttributeName.SUCCESS_MESSAGE,
										SUCCESS_MESSAGE);
								pageData.setRedirect();
							} else {
								data.putRequestAttribute(
										RequestAttributeName.ERROR_ROLE_PURPOSE,
										ERROR_MESSAGE_INCORRECT);
							}
						} else {
							data.putRequestAttribute(
									RequestAttributeName.ERROR_DIAGNOSIS_PURPOSE,
									ERROR_MESSAGE_INCORRECT);
						}
					} else {
						data.putRequestAttribute(
								RequestAttributeName.ERROR_ATTENDING_DOCTOR,
								ERROR_MESSAGE_INCORRECT);
					}
				}
			}
		} catch (ServiceException ex) {
			logger.log(Level.ERROR, ex);
			data.putRequestAttribute(RequestAttributeName.ERROR, ex);
			pageData.setPage(JspPage.ERROR);
		}
		return pageData;
	}

}
