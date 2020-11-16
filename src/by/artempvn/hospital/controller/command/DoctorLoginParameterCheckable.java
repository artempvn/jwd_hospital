package by.artempvn.hospital.controller.command;

import by.artempvn.hospital.controller.RequestData;

/**
 * The Interface DoctorLoginParameterCheckable.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface DoctorLoginParameterCheckable {

	String ERROR_MESSAGE_INCORRECT = "incorrect";

	/**
	 * Checks if is present doctor login parameter.
	 *
	 * @param data the data
	 * @return true, if is present patient id parameter
	 */
	default boolean isPresentDoctorLoginParameter(RequestData data) {
		return (data.getParameterValue(RequestParameterName.LOGIN) != null
				&& !data.getParameterValue(RequestParameterName.LOGIN)[0].isBlank())
				|| (data
						.getSessionAttributeValue(SessionAttributeName.DOCTOR_LOGIN) != null
						&& (!((String) data
								.getSessionAttributeValue(SessionAttributeName.DOCTOR_LOGIN))
										.isBlank()));
	}

	/**
	 * Put doctor login attribute into session from request parameter, if it is
	 * present.
	 *
	 * @param data the data
	 */
	default void putDoctorLoginAttribute(RequestData data) {
		if (isPresentDoctorLoginParameter(data)) {
			if (data.getParameterValue(RequestParameterName.LOGIN) != null) {
				data.putSessionAttribute(SessionAttributeName.DOCTOR_LOGIN,
						data.getParameterValue(RequestParameterName.LOGIN)[0]);
			}
		}
	}

}
