package by.artempvn.hospital.model.service;

import java.util.List;
import by.artempvn.hospital.exception.ServiceException;

/**
 * The Interface UtilService.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface UtilService {

	/**
	 * Take staff roles.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	List<String> takeStaffRoles() throws ServiceException;

	/**
	 * Take staff specialities.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	List<String> takeStaffSpecialities() throws ServiceException;

}
