package by.artempvn.hospital.model.dao;

import java.util.List;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.model.entity.UserData;

/**
 * The Interface MedicalStaffDao.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface MedicalStaffDao {

	/**
	 * Take medical staff.
	 *
	 * @param takeOnlyActive the take only active
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	List<UserData> takeMedicalStaff(boolean takeOnlyActive) throws DaoException;

	/**
	 * Take medical staff by id.
	 *
	 * @param userId the user id
	 * @return the user data
	 * @throws DaoException the dao exception
	 */
	UserData takeMedicalStaffById(long userId) throws DaoException;

}
