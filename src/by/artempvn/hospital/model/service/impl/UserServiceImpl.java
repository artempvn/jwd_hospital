package by.artempvn.hospital.model.service.impl;

import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.dao.UserDao;
import by.artempvn.hospital.model.dao.UtilDao;
import by.artempvn.hospital.model.dao.impl.UserDaoImpl;
import by.artempvn.hospital.model.dao.impl.UtilDaoImpl;
import by.artempvn.hospital.model.entity.UserData;
import by.artempvn.hospital.model.service.UserService;
import by.artempvn.hospital.util.MailSender;
import by.artempvn.hospital.util.PasswordCoder;
import by.artempvn.hospital.validator.DataValidator;

/**
 * The Class UserServiceImpl.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class UserServiceImpl implements UserService {
	private static final String EMAIL_MESSAGE = "To activate your accaunt, please,"
			+ " follow this <a target=\"_blank\" href=\"http://localhost:8080/hospital/"
			+ "controller?command=activate_user&login=%s\">link</a>";
	private static final String USER_STATUS_ACTIVATED = "activated";
	private static final String USER_STATUS_BANNED = "banned";
	private static UserServiceImpl instance = new UserServiceImpl();
	private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	private UserServiceImpl() {
	}

	public static UserServiceImpl getInstance() {
		return instance;
	}

	public boolean checkPassword(String login, String password)
			throws ServiceException {
		UserDao dao = UserDaoImpl.getInstance();
		String correctPassword;
		try {
			correctPassword = dao.takeUserPassword(login);
		} catch (DaoException ex) {
			throw new ServiceException("Failed to take password", ex);
		}
		String codedPassword = PasswordCoder.codePassword(password);
		boolean isCorrectPassword = codedPassword.equals(correctPassword);
		logger.log(Level.DEBUG, "Password is correct: " + isCorrectPassword);
		return isCorrectPassword;
	}

	public String takeRole(String login) throws ServiceException {
		UserDao dao = UserDaoImpl.getInstance();
		String role;
		try {
			role = dao.takeUserRole(login);
		} catch (DaoException ex) {
			throw new ServiceException("Failed to take role", ex);
		}
		logger.log(Level.DEBUG, "Role for login '" + login + "' is '" + role + "'");
		return role;
	}

	public UserData addUser(UserData data) throws ServiceException {
		boolean isAdded = false;
		UserData checkedData;
		try {
			checkedData = checkUserData(data);
			if (checkedData.isCorrectData()) {
				UtilDao utilDao = UtilDaoImpl.getInstance();
				data.setRole(utilDao.takeRoleId(data.getRole()));
				data.setSpeciality(utilDao.takeSpecialityId(data.getSpeciality()));
				data.setPassword(PasswordCoder.codePassword(data.getPassword()));
				data.setStatus(utilDao.takeStaffStatusId(data.getStatus()));
				UserDao dao = UserDaoImpl.getInstance();
				isAdded = dao.addUser(data);
				checkedData.setAdded(isAdded);
			}
		} catch (DaoException ex) {
			throw new ServiceException("Failed to add user", ex);
		}
		logger.log(Level.INFO,
				"User '" + data.getLogin() + "' was added: " + isAdded);
		if (isAdded) {
			String mailTo = data.getEmail();
			String body = String.format(EMAIL_MESSAGE, data.getLogin());
			MailSender sender = new MailSender(mailTo, body);
			sender.send();
		}
		return checkedData;
	}

	private UserData checkUserData(UserData data) throws DaoException {
		UserData checkedData = new UserData();
		checkedData.setCorrectData(true);
		UtilDao utilDao = UtilDaoImpl.getInstance();
		if (DataValidator.isCorrectLogin(data.getLogin())) {
			checkedData.setLogin(data.getLogin());
		} else {
			logger.log(Level.WARN, "Incorrect login '" + data.getLogin() + "'");
			checkedData.setCorrectData(false);
		}
		if (DataValidator.isCorrectEmail(data.getEmail())) {
			checkedData.setEmail(data.getEmail());
		} else {
			logger.log(Level.WARN, "Incorrect email '" + data.getEmail() + "'");
			checkedData.setCorrectData(false);
		}
		if (DataValidator.isCorrectName(data.getName())) {
			checkedData.setName(data.getName());
		} else {
			logger.log(Level.WARN, "Incorrect name '" + data.getName() + "'");
			checkedData.setCorrectData(false);
		}
		if (DataValidator.isCorrectName(data.getSurname())) {
			checkedData.setSurname(data.getSurname());
		} else {
			logger.log(Level.WARN, "Incorrect surname '" + data.getSurname() + "'");
			checkedData.setCorrectData(false);
		}
		if ((data.getSecondName().length() > 0
				&& DataValidator.isCorrectName(data.getSecondName()))
				|| (data.getSecondName().length() == 0)) {
			checkedData.setSecondName(data.getSecondName());
		} else {
			logger.log(Level.WARN,
					"Incorrect second name '" + data.getSecondName() + "'");
			checkedData.setCorrectData(false);
		}
		if (DataValidator.isCorrectPassword(data.getPassword())) {
			checkedData.setPassword(data.getPassword());
		} else {
			logger.log(Level.WARN, "Incorrect password '" + data.getPassword() + "'");
			checkedData.setCorrectData(false);
		}
		List<String> roles = utilDao.takeStaffRoles();
		if (roles.contains(data.getRole())) {
			checkedData.setRole(data.getRole());
		} else {
			logger.log(Level.WARN, "Role is not correct");
			checkedData.setCorrectData(false);
		}
		List<String> specialities = utilDao.takeStaffSpecialities();
		if (DataValidator.isCorrectSpeciality(data.getRole(), data.getSpeciality(),
				specialities)) {
			checkedData.setSpeciality(data.getSpeciality());
		} else {
			logger.log(Level.WARN, "Speciality is not correct");
			checkedData.setCorrectData(false);
		}
		return checkedData;
	}

	@Override
	public boolean activateUser(String login) throws ServiceException {
		UserDao dao = UserDaoImpl.getInstance();
		boolean isChanged;
		try {
			isChanged = dao.activateUser(login);
			logger.log(Level.INFO, "'" + login + "' changed status: " + isChanged);
		} catch (DaoException ex) {
			throw new ServiceException("Failed to change user status", ex);
		}
		return isChanged;
	}

	@Override
	public String takeUserStatus(String login) throws ServiceException {
		UserDao dao = UserDaoImpl.getInstance();
		String status;
		try {
			status = dao.takeUserStatus(login);
		} catch (DaoException ex) {
			throw new ServiceException("Failed to take status", ex);
		}
		logger.log(Level.DEBUG,
				"Status for login '" + login + "' is '" + status + "'");
		return status;
	}

	@Override
	public void banUser(String login) throws ServiceException {
		UserDao dao = UserDaoImpl.getInstance();
		UtilDao utilDao = UtilDaoImpl.getInstance();
		try {
			String statusId = utilDao.takeStaffStatusId(USER_STATUS_BANNED);
			if (statusId == null) {
				throw new ServiceException("Incorrect status type");
			}
			dao.updateUserStatus(login, Byte.parseByte(statusId));
			logger.log(Level.INFO, "User was banned: " + login);
		} catch (DaoException ex) {
			throw new ServiceException("Failed to ban user", ex);
		}
	}

	@Override
	public void unbanUser(String login) throws ServiceException {
		UserDao dao = UserDaoImpl.getInstance();
		UtilDao utilDao = UtilDaoImpl.getInstance();
		try {
			String statusId = utilDao.takeStaffStatusId(USER_STATUS_ACTIVATED);
			if (statusId == null) {
				throw new ServiceException("Incorrect status type");
			}
			dao.updateUserStatus(login, Byte.parseByte(statusId));
			logger.log(Level.INFO, "User was unbanned: " + login);
		} catch (DaoException ex) {
			throw new ServiceException("Failed to unban user", ex);
		}
	}

}
