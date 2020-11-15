package by.artempvn.hospital.model.service;

import java.util.Map;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.entity.DiagnosisData;

/**
 * The Interface DiagnosisService.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface DiagnosisService {

	/**
	 * Adds the diagnosis.
	 *
	 * @param data the data
	 * @return the diagnosis data
	 * @throws ServiceException the service exception
	 */
	DiagnosisData addDiagnosis(DiagnosisData data) throws ServiceException;

	/**
	 * Take diagnosis doctor data.
	 *
	 * @param patientId the patient id
	 * @return the map
	 * @throws ServiceException the service exception
	 */
	Map<String, Object> takeDiagnosisDoctorData(long patientId)
			throws ServiceException;

	/**
	 * Update diagnosis.
	 *
	 * @param diagnosisData the diagnosis data
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean updateDiagnosis(DiagnosisData diagnosisData) throws ServiceException;

}
