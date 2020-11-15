package by.artempvn.hospital.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.controller.JspPage;
import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.RequestAttributeName;
import by.artempvn.hospital.controller.command.RequestParameterName;
import by.artempvn.hospital.controller.command.Router;
import by.artempvn.hospital.controller.command.SessionAttributeName;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.entity.UserData;
import by.artempvn.hospital.model.service.impl.UserServiceImpl;

/**
 * The Class CreateUserCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class CreateUserCommand implements Command {
	private static final String ROLE_DOCTOR = "doctor";
	private static final String ROLE_NURSE = "nurse";
	private static final String SUCCESS_MESSAGE = "true";
	private static final String ERROR_MESSAGE_NOT_UNIQUE = "not unique";
	private static final String ERROR_MESSAGE_INCORRECT = "incorrect";
	private static final Logger logger = LogManager
			.getLogger(CreateUserCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		pageData.setPage(JspPage.CREATE_USER);
		if (isParameterPresent(data)) {
			String login = data.getParameterValue(RequestParameterName.LOGIN)[0];
			String email = data.getParameterValue(RequestParameterName.EMAIL)[0];
			String name = data.getParameterValue(RequestParameterName.NAME)[0];
			String surname = data.getParameterValue(RequestParameterName.SURNAME)[0];
			String secondName = data
					.getParameterValue(RequestParameterName.SECOND_NAME)[0];
			String role = data.getParameterValue(RequestParameterName.ROLE)[0];
			String speciality;
			if (role.equals(ROLE_NURSE)) {
				speciality = ROLE_NURSE;
			} else {
				speciality = data.getParameterValue(RequestParameterName.SPECIALITY)[0];
			}
			String password = data
					.getParameterValue(RequestParameterName.PASSWORD)[0];
			UserData userData = new UserData();
			userData.setLogin(login);
			userData.setEmail(email);
			userData.setName(name);
			userData.setSurname(surname);
			userData.setSecondName(secondName);
			userData.setRole(role);
			userData.setSpeciality(speciality);
			userData.setPassword(password);
			UserServiceImpl userService = UserServiceImpl.getInstance();
			UserData checkedUserData;
			try {
				checkedUserData = userService.addUser(userData);
				if (checkedUserData.isCorrectData()) {
					if (checkedUserData.isAdded()) {
						logger.log(Level.INFO, "User '" + checkedUserData.getLogin()
								+ "' was added successfully");
						data.putSessionAttribute(SessionAttributeName.SUCCESS_MESSAGE,
								SUCCESS_MESSAGE);
						pageData.setRedirect();
					} else {
						logger.log(Level.WARN,
								"User '" + checkedUserData.getLogin() + "' wasn't added");
						putErrorAttributeLoginEmail(data, checkedUserData);
						putCorrectInputValuesBack(data, checkedUserData);
					}
				} else {
					logger.log(Level.WARN, "Incorrect input");
					putCorrectInputLoginEmailBack(data, checkedUserData);
					putCorrectInputValuesBack(data, checkedUserData);
				}
			} catch (ServiceException ex) {
				data.putRequestAttribute(RequestAttributeName.ERROR, ex);
				pageData.setPage(JspPage.ERROR);
			}
		}
		return pageData;
	}

	private boolean isParameterPresent(RequestData data) {
		boolean isParameterPresent = data
				.getParameterValue(RequestParameterName.LOGIN) != null
				&& data.getParameterValue(RequestParameterName.EMAIL) != null
				&& data.getParameterValue(RequestParameterName.NAME) != null
				&& data.getParameterValue(RequestParameterName.SURNAME) != null
				&& data.getParameterValue(RequestParameterName.SECOND_NAME) != null
				&& data.getParameterValue(RequestParameterName.PASSWORD) != null
				&& data.getParameterValue(RequestParameterName.ROLE) != null
				&& ((data.getParameterValue(RequestParameterName.ROLE)[0]
						.equals(ROLE_DOCTOR)
						&& data.getParameterValue(RequestParameterName.SPECIALITY) != null)
						|| (data.getParameterValue(RequestParameterName.ROLE)[0]
								.equals(ROLE_NURSE)));
		return isParameterPresent;
	}

	private void putCorrectInputLoginEmailBack(RequestData data,
			UserData checkedUserData) {
		if (checkedUserData.getLogin() != null) {
			data.putRequestAttribute(RequestAttributeName.NEW_LOGIN,
					checkedUserData.getLogin());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_LOGIN,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedUserData.getEmail() != null) {
			data.putRequestAttribute(RequestAttributeName.EMAIL,
					checkedUserData.getEmail());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_EMAIL,
					ERROR_MESSAGE_INCORRECT);
		}
	}

	private void putErrorAttributeLoginEmail(RequestData data,
			UserData checkedUserData) {
		data.putRequestAttribute(RequestAttributeName.ERROR_LOGIN,
				ERROR_MESSAGE_NOT_UNIQUE);
		data.putRequestAttribute(RequestAttributeName.ERROR_EMAIL,
				ERROR_MESSAGE_NOT_UNIQUE);
	}

	private void putCorrectInputValuesBack(RequestData data,
			UserData checkedUserData) {
		if (checkedUserData.getName() != null) {
			data.putRequestAttribute(RequestAttributeName.NAME,
					checkedUserData.getName());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_NAME,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedUserData.getSurname() != null) {
			data.putRequestAttribute(RequestAttributeName.SURNAME,
					checkedUserData.getSurname());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_SURNAME,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedUserData.getSecondName() != null) {
			data.putRequestAttribute(RequestAttributeName.SECOND_NAME,
					checkedUserData.getSecondName());
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_SECOND_NAME,
					ERROR_MESSAGE_INCORRECT);
		}
		String role = checkedUserData.getRole();
		if (role != null) {
			data.putRequestAttribute(RequestAttributeName.NEW_ROLE, role);
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_ROLE,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedUserData.getSpeciality() != null) {
			if (role.equals(ROLE_DOCTOR)) {
				data.putRequestAttribute(RequestAttributeName.SPECIALITY,
						checkedUserData.getSpeciality());
			}
		} else {
			data.putRequestAttribute(RequestAttributeName.ERROR_SPECIALITY,
					ERROR_MESSAGE_INCORRECT);
		}
		if (checkedUserData.getPassword() == null) {
			data.putRequestAttribute(RequestAttributeName.ERROR_PASSWORD,
					ERROR_MESSAGE_INCORRECT);
		}
	}

}
