package by.artempvn.hospital.model.dao;

import java.util.List;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.model.entity.PatientData;

/**
 * The Interface PatientDao.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface PatientDao {

	/**
	 * Adds the patient.
	 *
	 * @param data the data
	 * @throws DaoException the dao exception
	 */
	void addPatient(PatientData data) throws DaoException;

	/**
	 * Take patients.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	List<PatientData> takePatients() throws DaoException;

	/**
	 * Change patient status.
	 *
	 * @param patientId the patient id
	 * @param status the status
	 * @throws DaoException the dao exception
	 */
	void changePatientStatus(long patientId, String status) throws DaoException;

	/**
	 * Change diagnosis.
	 *
	 * @param patientId the patient id
	 * @param diagnosisId the diagnosis id
	 * @throws DaoException the dao exception
	 */
	void changeDiagnosis(long patientId, long diagnosisId) throws DaoException;

	/**
	 * Take patient.
	 *
	 * @param patientId the patient id
	 * @return the patient data
	 * @throws DaoException the dao exception
	 */
	PatientData takePatient(long patientId) throws DaoException;

	/**
	 * Discharge patient.
	 *
	 * @param data the data
	 * @throws DaoException the dao exception
	 */
	void dischargePatient(PatientData data) throws DaoException;;

}
