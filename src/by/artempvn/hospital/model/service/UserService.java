package by.artempvn.hospital.model.service;

import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.entity.UserData;

/**
 * The Interface UserService.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface UserService {

	/**
	 * Check password.
	 *
	 * @param login the login
	 * @param password the password
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean checkPassword(String login, String password) throws ServiceException;

	/**
	 * Take role.
	 *
	 * @param login the login
	 * @return the string
	 * @throws ServiceException the service exception
	 */
	String takeRole(String login) throws ServiceException;

	/**
	 * Adds the user.
	 *
	 * @param data the data
	 * @return the user data
	 * @throws ServiceException the service exception
	 */
	UserData addUser(UserData data) throws ServiceException;

	/**
	 * Activate user.
	 *
	 * @param login the login
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean activateUser(String login) throws ServiceException;

	/**
	 * Take user status.
	 *
	 * @param login the login
	 * @return the string
	 * @throws ServiceException the service exception
	 */
	String takeUserStatus(String login) throws ServiceException;

	/**
	 * Ban user.
	 *
	 * @param login the login
	 * @throws ServiceException the service exception
	 */
	void banUser(String login) throws ServiceException;

	/**
	 * Unban user.
	 *
	 * @param login the login
	 * @throws ServiceException the service exception
	 */
	void unbanUser(String login) throws ServiceException;

}
