package by.artempvn.hospital.model.service;

import java.util.List;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.entity.UserData;

/**
 * The Interface MedicalStaffService.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface MedicalStaffService {

	/**
	 * Take medical staff. 
	 *
	 * @param takeOnlyActive if {@code true} method will return list
	 * of staff with activated status, else - all
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	List<UserData> takeMedicalStaff(boolean takeOnlyActive)
			throws ServiceException;

	/**
	 * Take medical staff by login.
	 *
	 * @param login the login
	 * @return the user data
	 * @throws ServiceException the service exception
	 */
	UserData takeMedicalStaffByLogin(String login) throws ServiceException;

}
