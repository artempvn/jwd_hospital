package by.artempvn.hospital.controller.command;

import by.artempvn.hospital.controller.RequestData;

/**
 * The Interface PatientIdParameterCheckable.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface PatientIdParameterCheckable {

	String ERROR_MESSAGE_INCORRECT = "incorrect";

	/**
	 * Checks if is present patient id parameter.
	 *
	 * @param data the data
	 * @return true, if is present patient id parameter
	 */
	default boolean isPresentPatientIdParameter(RequestData data) {
		return (data.getParameterValue(RequestParameterName.PATIENT_ID) != null
				&& !data.getParameterValue(RequestParameterName.PATIENT_ID)[0]
						.isBlank())
				|| (data
						.getSessionAttributeValue(RequestParameterName.PATIENT_ID) != null
						&& (!((String) data
								.getSessionAttributeValue(RequestParameterName.PATIENT_ID))
										.isBlank()));
	}

	/**
	 * Put patient id attribute into session from request parameter,
	 * if it is present.
	 *
	 * @param data the data
	 */
	default void putPatientIdAttribute(RequestData data) {
		if (isPresentPatientIdParameter(data)) {
			if (data.getParameterValue(RequestParameterName.PATIENT_ID) != null) {
				data.putSessionAttribute(RequestParameterName.PATIENT_ID,
						data.getParameterValue(RequestParameterName.PATIENT_ID)[0]);
			}
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_PATIENT,
					ERROR_MESSAGE_INCORRECT);
		}
	}

}
