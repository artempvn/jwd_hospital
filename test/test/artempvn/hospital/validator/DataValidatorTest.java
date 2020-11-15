package test.artempvn.hospital.validator;

import static org.testng.Assert.assertEquals;
import java.util.Collections;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.hospital.validator.DataValidator;

/**
 * The Class DataValidatorTest.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class DataValidatorTest {

	@Test(dataProvider = "isCorrectLogin")
	public void isCorrectLoginTest(String login, boolean expected) {
		boolean actual = DataValidator.isCorrectLogin(login);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isCorrectLogin() {
		return new Object[][] { { "artempvn", true }, { "artem_123", true },
				{ "arte-m", true }, { "log", false }, { "artempvn1234", false },
				{ "логин", false }, { null, false } };
	}

	@Test(dataProvider = "isCorrectEmail")
	public void isCorrectEmailTest(String email, boolean expected) {
		boolean actual = DataValidator.isCorrectEmail(email);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isCorrectEmail() {
		return new Object[][] { { "artempvn@mail.ru", true },
				{ "artem_pvn1-23@mail.ru", true }, { "xxxmailxxx@gmail.com", true },
				{ "artempvnmail.ru", false }, { "почта@mail.ru", false },
				{ "mail@g_mail.com", false }, { "mail@gmailcom", false },
				{ "mail@gmail.1com", false }, { null, false } };
	}

	@Test(dataProvider = "isCorrectName")
	public void isCorrectNameTest(String name, boolean expected) {
		boolean actual = DataValidator.isCorrectName(name);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isCorrectName() {
		return new Object[][] { { "Artem", true }, { "Артем", true },
				{ "123", false }, { "not_name", false }, { "two words", false },
				{ null, false } };
	}

	@Test(dataProvider = "isCorrectPassword")
	public void isCorrectPasswordTest(String password, boolean expected) {
		boolean actual = DataValidator.isCorrectPassword(password);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isCorrectPassword() {
		return new Object[][] { { "password", true }, { "~14#$@sdf", true },
				{ "123", false }, { "пароль", false }, { "qwerty12345", false },
				{ " adada", false }, { null, false } };
	}

	@Test(dataProvider = "isCorrectSpeciality")
	public void isCorrectSpecialityTest(String role, String speciality,
			List<String> specialities, boolean expected) {
		boolean actual = DataValidator.isCorrectSpeciality(role, speciality,
				specialities);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isCorrectSpeciality() {
		return new Object[][] { { "doctor", "surgeon",
				List.of("surgeon", "dentist", "physician"), true },
				{ "nurse", "nurse", Collections.emptyList(), true },
				{ "guest", "surgeon", List.of("surgeon", "dentist", "physician"),
						false },
				{ "doctor", "nurse",
						List.of("surgeon", "dentist", "physician", "nurse"), false },
				{ "doctor", "junior",
						List.of("surgeon", "dentist", "physician", "nurse"), false },
				{ null, "surgeon", List.of("surgeon", "dentist", "physician"), false },
				{ "nurse", null, List.of("surgeon", "dentist", "physician"), false },
				{ "doctor", "surgeon", null, false } };
	}

	@Test(dataProvider = "isCorrectDateTime")
	public void isCorrectDateTimeTest(String date, boolean expected) {
		boolean actual = DataValidator.isCorrectDateTime(date);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isCorrectDateTime() {
		return new Object[][] { { "2020-11-14T20:56:00", true },
				{ "2020-11-14", false }, { "not_time", false } };
	}

	@Test(dataProvider = "isCorrectDate")
	public void isCorrectDateTest(String date, boolean expected) {
		boolean actual = DataValidator.isCorrectDate(date);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isCorrectDate() {
		return new Object[][] { { "2020-11-14", true },
				{ "2020-11-14T20:56:00", false }, { "not_time", false } };
	}

	@Test(dataProvider = "isTextFieldCorrect")
	public void isTextFieldCorrectTest(String text, boolean expected) {
		boolean actual = DataValidator.isTextFieldCorrect(text);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isTextFieldCorrect() {
		return new Object[][] { { "some text", true }, { " ", false },
				{ null, false }, { new String(new char[1001]), false } };
	}

}
