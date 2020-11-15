package by.artempvn.hospital.model.dao;

import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.model.entity.DiagnosisData;

/**
 * The Interface DiagnosisDao.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface DiagnosisDao {

	/**
	 * Adds the diagnosis.
	 *
	 * @param data the data
	 * @return the long
	 * @throws DaoException the dao exception
	 */
	long addDiagnosis(DiagnosisData data) throws DaoException;

	/**
	 * Take diagnosis.
	 *
	 * @param diagnosisId the diagnosis id
	 * @return the diagnosis data
	 * @throws DaoException the dao exception
	 */
	DiagnosisData takeDiagnosis(long diagnosisId) throws DaoException;

	/**
	 * Update diagnosis.
	 *
	 * @param diagnosisData the diagnosis data
	 * @throws DaoException the dao exception
	 */
	void updateDiagnosis(DiagnosisData diagnosisData) throws DaoException;

}
