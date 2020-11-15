package by.artempvn.hospital.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import by.artempvn.hospital.controller.command.SessionAttributeName;

/**
 * The Class ShowUserTag. Shows User login and role
 * 
 * @author Artem Piven
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ShowUserTag extends TagSupport {
	private String localeLogin;
	private String localeRole;

	/**
	 * Sets the locale login.
	 *
	 * @param localeLogin the new locale login
	 */
	public void setLocaleLogin(String localeLogin) {
		this.localeLogin = localeLogin;
	}

	/**
	 * Sets the locale role.
	 *
	 * @param localeRole the new locale role
	 */
	public void setLocaleRole(String localeRole) {
		this.localeRole = localeRole;
	}

	/**
	 * Do start tag.
	 *
	 * @return the int
	 * @throws JspException the jsp exception
	 */
	@Override
	public int doStartTag() throws JspException {
		String login = (String) pageContext.getSession()
				.getAttribute(SessionAttributeName.LOGIN);
		String role = (String) pageContext.getSession()
				.getAttribute(SessionAttributeName.ROLE);
		try {
			JspWriter out = pageContext.getOut();
			out.write("<div class=\"right\">");
			out.write("<h4>" + localeLogin + ": " + login + "</h4>");
			out.write("<h4>" + localeRole + ": " + role + "</h4>");
			out.write("<div>");
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
