package by.artempvn.hospital.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.artempvn.hospital.controller.JspPage;
import by.artempvn.hospital.controller.command.RequestParameterName;
import by.artempvn.hospital.controller.command.SessionAttributeName;
import static by.artempvn.hospital.controller.command.CommandType.*;

/**
 * The Class RoleFilter.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class RoleFilter implements Filter {
	private static final String ROLE_ADMIN = "admin";
	private static final String ROLE_DOCTOR = "doctor";
	private static final String ROLE_NURSE = "nurse";
	private static final String EMPTY_COMMAND = "";

	/**
	 * Do filter.
	 *
	 * @param request the request
	 * @param response the response
	 * @param chain the chain
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String role = (String) session.getAttribute(SessionAttributeName.ROLE);
		String command = req.getParameter(RequestParameterName.COMMAND);
		if (command== null) {
			command=EMPTY_COMMAND;
		}
		if (isCommandForbidden(role, command)) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher(JspPage.INDEX);
			dispatcher.forward(req, resp);
			return;
		}
		chain.doFilter(request, response);
	}

	private boolean isCommandForbidden(String role, String command) {
		if (role != null) {
			switch (role) {
			case ROLE_ADMIN:
				return false;
			case ROLE_DOCTOR:
				return isOnlyAdminCommand(command);
			case ROLE_NURSE:
				return (!isNurseCanDoCommand(command) && !isCommonCommand(command));
			default:
				return true;
			}
		} else {
			return !isCommonCommand(command);
		}
	}

	private boolean isCommonCommand(String command) {
		return (command.equalsIgnoreCase(LOGIN.name())
				|| command.equalsIgnoreCase(SET_LOCALE_EN.name())
				|| command.equalsIgnoreCase(SET_LOCALE_RU.name())
				|| command.equalsIgnoreCase(ACTIVATE_USER.name())
				|| command.equalsIgnoreCase(LOGOUT.name())
				|| command.equalsIgnoreCase(BACK_TO_PREVIOUS.name()));
	}

	private boolean isOnlyAdminCommand(String command) {
		return (command.equalsIgnoreCase(CREATE_USER.name())
				|| command.equalsIgnoreCase(SHOW_STAFF.name())
				|| command.equalsIgnoreCase(BAN_USER.name())
				|| command.equalsIgnoreCase(UNBAN_USER.name()));
	}

	private boolean isNurseCanDoCommand(String command) {
		return (command.equalsIgnoreCase(CREATE_PATIENT.name())
				|| command.equalsIgnoreCase(MAIN.name())
				|| command.equalsIgnoreCase(SHOW_PATIENTS.name())
				|| command.equalsIgnoreCase(SHOW_DIAGNOSIS.name())
				|| command.equalsIgnoreCase(SHOW_PATIENT_PURPOSE.name())
				|| command.equalsIgnoreCase(SHOW_STAFF_PURPOSE.name())
				|| command.equalsIgnoreCase(SHOW_DOCTOR.name())
				|| command.equalsIgnoreCase(SHOW_PATIENT.name()));
	}

}
