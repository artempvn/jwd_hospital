package by.artempvn.hospital.model.dao;

import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.model.entity.UserData;

/**
 * The Interface UserDao.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface UserDao {

	/**
	 * Take user password.
	 *
	 * @param login the login
	 * @return the string
	 * @throws DaoException the dao exception
	 */
	String takeUserPassword(String login) throws DaoException;

	/**
	 * Take user role.
	 *
	 * @param login the login
	 * @return the string
	 * @throws DaoException the dao exception
	 */
	String takeUserRole(String login) throws DaoException;

	/**
	 * Take user role.
	 *
	 * @param id the id
	 * @return the string
	 * @throws DaoException the dao exception
	 */
	String takeUserRole(long id) throws DaoException;

	/**
	 * Adds the user.
	 *
	 * @param data the data
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean addUser(UserData data) throws DaoException;

	/**
	 * Activate user.
	 *
	 * @param login the login
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean activateUser(String login) throws DaoException;

	/**
	 * Take user status.
	 *
	 * @param login the login
	 * @return the string
	 * @throws DaoException the dao exception
	 */
	String takeUserStatus(String login) throws DaoException;

	/**
	 * Update user status.
	 *
	 * @param login the login
	 * @param userStatusId the user status id
	 * @throws DaoException the dao exception
	 */
	void updateUserStatus(String login, byte userStatusId) throws DaoException;

}
