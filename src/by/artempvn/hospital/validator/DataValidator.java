package by.artempvn.hospital.validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * The Class DataValidator.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class DataValidator {
	private static final int MAX_TEXT_LENGTH = 1000;
	private static final String ROLE_NURSE = "nurse";
	private static final String ROLE_DOCTOR = "doctor";
	private static final int MAX_CHAR_VALUE = 126;
	private static final int MIN_CHAR_VALUE = 33;
	private static final int MAX_PASSWORD_LENGTH = 10;
	private static final int MIN_PASSWORD_LENGTH = 4;
	private static final String LOGIN_REGEX = "[-\\w]{4,10}";
	private static final String EMAIL_REGEX = "[-.\\w]{1,20}@[^\\W_]{1,15}\\.[a-zA-z]{2,5}";
	private static final String NAME_REGEX = "[\\pL]{1,15}";

	private DataValidator() {
	}

	/**
	 * Checks if is correct login.
	 *
	 * @param login the login
	 * @return true, if is correct login
	 */
	public static boolean isCorrectLogin(String login) {
		boolean isCorrect = false;
		if (login != null) {
			isCorrect = login.matches(LOGIN_REGEX);
		}
		return isCorrect;
	}

	/**
	 * Checks if is correct email.
	 *
	 * @param email the email
	 * @return true, if is correct email
	 */
	public static boolean isCorrectEmail(String email) {
		boolean isCorrect = false;
		if (email != null) {
			isCorrect = email.matches(EMAIL_REGEX);
		}
		return isCorrect;
	}

	/**
	 * Checks if is correct name.
	 *
	 * @param name the name
	 * @return true, if is correct name
	 */
	public static boolean isCorrectName(String name) {
		boolean isCorrect = false;
		if (name != null) {
			isCorrect = name.matches(NAME_REGEX);
		}
		return isCorrect;
	}

	/**
	 * Checks if is correct password.
	 *
	 * @param password the password
	 * @return true, if is correct password
	 */
	public static boolean isCorrectPassword(String password) {
		boolean isCorrect = false;
		if (password != null) {
			var pass = password.toCharArray();
			if (pass.length >= MIN_PASSWORD_LENGTH
					&& pass.length <= MAX_PASSWORD_LENGTH) {
				isCorrect = true;
				int i = 0;
				while (i < pass.length && isCorrect) {
					if (pass[i] >= MIN_CHAR_VALUE && pass[i] <= MAX_CHAR_VALUE) {
						i++;
					} else {
						isCorrect = false;
					}
				}
			}
		}
		return isCorrect;
	}

	/**
	 * Checks if is correct speciality.
	 *
	 * @param role         the role
	 * @param speciality   the speciality
	 * @param specialities the specialities
	 * @return true, if is correct speciality
	 */
	public static boolean isCorrectSpeciality(String role, String speciality,
			List<String> specialities) {
		if (role == null || specialities == null || speciality == null) {
			return false;
		}
		switch (role) {
		case ROLE_DOCTOR:
			return specialities.contains(speciality)
					&& !speciality.equalsIgnoreCase(ROLE_NURSE);
		case ROLE_NURSE:
			return speciality.equalsIgnoreCase(ROLE_NURSE);
		default:
			return false;
		}
	}

	/**
	 * Checks if is correct date time.
	 *
	 * @param date the date
	 * @return true, if is correct date time
	 */
	public static boolean isCorrectDateTime(String date) {
		boolean isCorrect;
		try {
			LocalDateTime.parse(date);
			isCorrect = true;
		} catch (DateTimeParseException ex) {
			isCorrect = false;
		}
		return isCorrect;
	}

	/**
	 * Checks if is correct date.
	 *
	 * @param date the date
	 * @return true, if is correct date
	 */
	public static boolean isCorrectDate(String date) {
		boolean isCorrect;
		try {
			LocalDate.parse(date);
			isCorrect = true;
		} catch (DateTimeParseException ex) {
			isCorrect = false;
		}
		return isCorrect;
	}

	/**
	 * Checks if is text field correct.
	 *
	 * @param text the text
	 * @return true, if is text field correct
	 */
	public static boolean isTextFieldCorrect(String text) {
		return (text != null && !text.isBlank()
				&& text.length() <= MAX_TEXT_LENGTH);
	}

}
