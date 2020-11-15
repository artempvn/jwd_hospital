package by.artempvn.hospital.tag;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import by.artempvn.hospital.controller.command.SessionAttributeName;

/**
 * The Class DateConverterTag. Convert date from number of days/seconds to
 * string representation
 * 
 * @author Artem Piven
 * @version 1.0
 */
@SuppressWarnings("serial")
public class DateConverterTag extends TagSupport {
	private static final int DATE_LENGTH = 5;
	private static final String DATE_PATTERN = "dd MMMM yyyy";
	private static final String DATE_TIME_PATTERN = "dd MMMM yyyy HH:mm";
	private long date;

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(long date) {
		this.date = date;
	}

	/**
	 * Do start tag.
	 *
	 * @return the int
	 * @throws JspException the jsp exception
	 */
	@Override
	public int doStartTag() throws JspException {
		String pattern;
		LocalDateTime localDateTime = null;
		LocalDate localDate = null;
		if (String.valueOf(date).length() == DATE_LENGTH) {
			pattern = DATE_PATTERN;
			localDate = LocalDate.ofEpochDay(date);
		} else {
			localDateTime = LocalDateTime.ofEpochSecond(date, 0, ZoneOffset.UTC);
			pattern = DATE_TIME_PATTERN;
		}
		Locale locale = new Locale((String) pageContext.getSession()
				.getAttribute(SessionAttributeName.LOCALE));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, locale);
		try {
			JspWriter out = pageContext.getOut();
			if (localDateTime != null) {
				out.write(formatter.format(localDateTime));
			} else if (localDate != null) {
				out.write(formatter.format(localDate));
			}
		} catch (IOException ex) {
			throw new JspException(ex);
		}
		return SKIP_BODY;
	}

	/**
	 * Do end tag.
	 *
	 * @return the int
	 * @throws JspException the jsp exception
	 */
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
