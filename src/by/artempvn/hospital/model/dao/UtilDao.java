package by.artempvn.hospital.model.dao;

import java.util.List;
import by.artempvn.hospital.exception.DaoException;

/**
 * The Interface UtilDao.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface UtilDao {

	/**
	 * Take staff roles.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	List<String> takeStaffRoles() throws DaoException;

	/**
	 * Take staff specialities.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	List<String> takeStaffSpecialities() throws DaoException;

	/**
	 * Take role id.
	 *
	 * @param role the role
	 * @return the string
	 * @throws DaoException the dao exception
	 */
	String takeRoleId(String role) throws DaoException;

	/**
	 * Take speciality id.
	 *
	 * @param speciality the speciality
	 * @return the string
	 * @throws DaoException the dao exception
	 */
	String takeSpecialityId(String speciality) throws DaoException;

	/**
	 * Take staff status id.
	 *
	 * @param status the status
	 * @return the string
	 * @throws DaoException the dao exception
	 */
	String takeStaffStatusId(String status) throws DaoException;

	/**
	 * Take patient status id.
	 *
	 * @param status the status
	 * @return the string
	 * @throws DaoException the dao exception
	 */
	String takePatientStatusId(String status) throws DaoException;

	/**
	 * Take procedure type id.
	 *
	 * @param procedureType the procedure type
	 * @return the string
	 * @throws DaoException the dao exception
	 */
	String takeProcedureTypeId(String procedureType) throws DaoException;

	/**
	 * Take procedure types.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	List<String> takeProcedureTypes() throws DaoException;

	/**
	 * Take staff id by login.
	 *
	 * @param login the login
	 * @return the string
	 * @throws DaoException the dao exception
	 */
	String takeStaffIdByLogin(String login) throws DaoException;

	/**
	 * Take purpose id by patient id.
	 *
	 * @param patientId the patient id
	 * @return the long
	 * @throws DaoException the dao exception
	 */
	long takePurposeIdByPatientId(long patientId) throws DaoException;

	/**
	 * Take patient id by purpose id.
	 *
	 * @param purposeId the purpose id
	 * @return the long
	 * @throws DaoException the dao exception
	 */
	long takePatientIdByPurposeId(long purposeId) throws DaoException;

}
