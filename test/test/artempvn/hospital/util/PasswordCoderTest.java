package test.artempvn.hospital.util;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.hospital.util.PasswordCoder;

/**
 * The Class DataValidatorTest.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class PasswordCoderTest {

	@Test(dataProvider = "codePassword")
	public void codePasswordTest(String inputPassword, String expected) {
		String actual = PasswordCoder.codePassword(inputPassword);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] codePassword() {
		return new Object[][] { { "pass", "160141163163" },
				{ "@1im~", "10061151155176" }, { null, "" } };
	}

}
