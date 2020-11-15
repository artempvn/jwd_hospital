package by.artempvn.hospital.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import by.artempvn.hospital.controller.command.RequestAttributeName;
import by.artempvn.hospital.model.entity.PatientData;

/**
 * The Class ShowPatientTag. Shows clickable patient's button with name and surname
 * of patient on it with.
 * 
 * @author Artem Piven
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ShowPatientTag extends TagSupport {
	private String localePatient;

	/**
	 * Sets the locale patient.
	 *
	 * @param localePatient the new locale patient
	 */
	public void setLocalePatient(String localePatient) {
		this.localePatient = localePatient;
	}

	/**
	 * Do start tag.
	 *
	 * @return the int
	 * @throws JspException the jsp exception
	 */
	@Override
	public int doStartTag() throws JspException {
		PatientData patient = (PatientData) pageContext.getRequest()
				.getAttribute(RequestAttributeName.PATIENT);
		String name = patient.getName();
		String surname = patient.getSurname();
		long id = patient.getId();
		try {
			JspWriter out = pageContext.getOut();
			out.write(
					"<form name=\"ShowPatient\" method=\"POST\" action=\"controller\">"
							+ "<input type=\"hidden\" name=\"command\" value=\"show_patient\" />"
							+ "<input type=\"hidden\" name=\"patient_id\" value=\"" + id
							+ "\" />"
							+ "<button class=\"button_md form__btn\" type=\"submit\">"
							+ localePatient + ": " + name + " " + surname + "</button>"
							+ "</form>");
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