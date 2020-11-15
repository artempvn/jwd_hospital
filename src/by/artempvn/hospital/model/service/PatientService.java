package by.artempvn.hospital.model.service;

import java.util.List;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.entity.PatientData;

/**
 * The Interface PatientService.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface PatientService {

	/**
	 * Adds the patient.
	 *
	 * @param data the data
	 * @return the patient data
	 * @throws ServiceException the service exception
	 */
	PatientData addPatient(PatientData data) throws ServiceException;

	/**
	 * Take patients.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	List<PatientData> takePatients() throws ServiceException;

	/**
	 * Discharge patient.
	 *
	 * @param data the data
	 * @return the patient data
	 * @throws ServiceException the service exception
	 */
	PatientData dischargePatient(PatientData data) throws ServiceException;

	/**
	 * Take patient by id.
	 *
	 * @param patientId the patient id
	 * @return the patient data
	 * @throws ServiceException the service exception
	 */
	PatientData takePatient(long patientId) throws ServiceException;

}
