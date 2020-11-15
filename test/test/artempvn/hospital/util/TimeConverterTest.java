package test.artempvn.hospital.util;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.hospital.util.TimeConverter;

/**
 * The Class DataValidatorTest.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class TimeConverterTest {

	@Test(dataProvider = "convertFromIsoToSecondsDateTime")
	public void convertFromIsoToSecondsDateTimeTest(String timeFormatIso,
			String expected) {
		String actual = TimeConverter
				.convertFromIsoToSecondsDateTime(timeFormatIso);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] convertFromIsoToSecondsDateTime() {
		return new Object[][] { { "1970-01-01T23:59:59", "86399" } };
	}

	@Test(dataProvider = "convertFromIsoToSecondsDate")
	public void convertFromIsoToSecondsDateTest(String timeFormatIso,
			String expected) {
		String actual = TimeConverter.convertFromIsoToSecondsDate(timeFormatIso);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] convertFromIsoToSecondsDate() {
		return new Object[][] { { "1970-01-02", "1" } };
	}

}
