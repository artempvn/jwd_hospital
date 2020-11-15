package by.artempvn.hospital.controller.filter;

import static by.artempvn.hospital.controller.command.CommandType.*;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.controller.command.RequestParameterName;
import by.artempvn.hospital.controller.command.SessionAttributeName;

/**
 * The Class CurrentPageFilter.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class CurrentPageFilter implements Filter {
	private static final String MAIN_COMMAND = "main";
	private static final String COMMAND_FULL_NAME = "/controller?command=%s";
	private static final String EMPTY_COMMAND = "";
	private final Logger logger = LogManager.getLogger(CurrentPageFilter.class);

	/**
	 * Do filter.
	 *
	 * @param req      the req
	 * @param response the response
	 * @param chain    the chain
	 * @throws IOException      Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession(true);
		String command = request.getParameter(RequestParameterName.COMMAND);
		if (command != null) {
			if (!isCommandHasNoPage(command)) {
				if (command.equalsIgnoreCase(LOGIN.name())) {
					command = MAIN_COMMAND;
				}
				command = addFullPath(command);
			} else {
				command = (String) session
						.getAttribute(SessionAttributeName.CURRENT_COMMAND);
			}
		}
		if (command == null) {
			command = (String) session
					.getAttribute(SessionAttributeName.CURRENT_COMMAND);
		}
		String previousCommand = (String) session
				.getAttribute(SessionAttributeName.PREVIOUS_COMMAND);
		if (previousCommand != null) {
			if (command != null && !command
					.equals(session.getAttribute(SessionAttributeName.CURRENT_COMMAND))) {
				previousCommand = (String) session
						.getAttribute(SessionAttributeName.CURRENT_COMMAND);
			}
		} else {
			previousCommand = addFullPath(EMPTY_COMMAND);
		}
		logger.log(Level.DEBUG,
				"current: " + command + "; prev: " + previousCommand);
		session.setAttribute(SessionAttributeName.PREVIOUS_COMMAND,
				previousCommand);
		session.setAttribute(SessionAttributeName.CURRENT_COMMAND, command);
		chain.doFilter(request, response);
	}

	private String addFullPath(String command) {
		String path = String.format(COMMAND_FULL_NAME, command);
		return path;
	}

	private boolean isCommandHasNoPage(String command) {
		return (command.equalsIgnoreCase(SET_LOCALE_EN.name())
				|| command.equalsIgnoreCase(SET_LOCALE_RU.name())
				|| command.equalsIgnoreCase(ACTIVATE_USER.name())
				|| command.equalsIgnoreCase(BAN_USER.name())
				|| command.equalsIgnoreCase(UNBAN_USER.name())
				|| command.equalsIgnoreCase(BACK_TO_PREVIOUS.name())
				|| command.equalsIgnoreCase(UPDATE_DIAGNOSIS.name())
				|| command.equalsIgnoreCase(DELETE_DRUG.name())
				|| command.equalsIgnoreCase(DELETE_PROCEDURE.name())
				|| command.equalsIgnoreCase(ADD_DRUG.name())
				|| command.equalsIgnoreCase(ADD_PROCEDURE.name()));
	}

}
