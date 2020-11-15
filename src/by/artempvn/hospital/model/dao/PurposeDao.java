package by.artempvn.hospital.model.dao;

import java.util.List;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.model.entity.DrugData;
import by.artempvn.hospital.model.entity.ProcedureData;

/**
 * The Interface PurposeDao.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface PurposeDao {

	/**
	 * Change attending doctor.
	 *
	 * @param purposeId the purpose id
	 * @param attendingDoctorId the attending doctor id
	 * @throws DaoException the dao exception
	 */
	void changeAttendingDoctor(String purposeId, long attendingDoctorId)
			throws DaoException;

	/**
	 * Adds the drug purpose.
	 *
	 * @param purposeId the purpose id
	 * @param drugId the drug id
	 * @throws DaoException the dao exception
	 */
	void addDrugPurpose(long purposeId, long drugId) throws DaoException;

	/**
	 * Adds the procedure purpose.
	 *
	 * @param purposeId the purpose id
	 * @param procedureId the procedure id
	 * @throws DaoException the dao exception
	 */
	void addProcedurePurpose(long purposeId, long procedureId)
			throws DaoException;

	/**
	 * Adds the purpose.
	 *
	 * @return the long
	 * @throws DaoException the dao exception
	 */
	long addPurpose() throws DaoException;

	/**
	 * Checks if is any operation in purpose.
	 *
	 * @param purposeId the purpose id
	 * @return true, if is any operation in purpose
	 * @throws DaoException the dao exception
	 */
	boolean isAnyOperationInPurpose(long purposeId) throws DaoException;

	/**
	 * Take attending doctor id.
	 *
	 * @param purposeId the purpose id
	 * @return the long
	 * @throws DaoException the dao exception
	 */
	long takeAttendingDoctorId(long purposeId) throws DaoException;

	/**
	 * Removes the attending doctor.
	 *
	 * @param purposeId the purpose id
	 * @throws DaoException the dao exception
	 */
	void removeAttendingDoctor(long purposeId) throws DaoException;

	/**
	 * Take drugs.
	 *
	 * @param purposeId the purpose id
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	List<DrugData> takeDrugs(long purposeId) throws DaoException;

	/**
	 * Take procedures.
	 *
	 * @param purposeId the purpose id
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	List<ProcedureData> takeProcedures(long purposeId) throws DaoException;

	/**
	 * Take purpose id by attending doctor id.
	 *
	 * @param attendingDoctorId the attending doctor id
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	List<Long> takePurposeIdByAttendingDoctorId(long attendingDoctorId)
			throws DaoException;

	/**
	 * Delete drug purpose.
	 *
	 * @param drugId the drug id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean deleteDrugPurpose(long drugId) throws DaoException;

	/**
	 * Delete procedure purpose.
	 *
	 * @param procedureId the procedure id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean deleteProcedurePurpose(long procedureId) throws DaoException;

}
