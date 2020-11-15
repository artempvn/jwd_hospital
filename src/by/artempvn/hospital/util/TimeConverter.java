package by.artempvn.hospital.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * The Class TimeConverter.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class TimeConverter {

	private TimeConverter() {
	}

	/**
	 * Convert from iso to seconds date time.
	 *
	 * @param timeFormatIso the time format iso
	 * @return the string
	 */
	public static String convertFromIsoToSecondsDateTime(String timeFormatIso) {
		LocalDateTime time = LocalDateTime.parse(timeFormatIso);
		long seconds = time.toEpochSecond(ZoneOffset.UTC);
		return String.valueOf(seconds);
	}

	/**
	 * Convert from iso to seconds date.
	 *
	 * @param timeFormatIso the time format iso
	 * @return the string
	 */
	public static String convertFromIsoToSecondsDate(String timeFormatIso) {
		LocalDate time = LocalDate.parse(timeFormatIso);
		long seconds = time.toEpochDay();
		return String.valueOf(seconds);
	}

}
