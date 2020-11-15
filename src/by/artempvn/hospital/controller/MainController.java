package by.artempvn.hospital.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.RequestAttributeName;
import by.artempvn.hospital.controller.command.Router;
import by.artempvn.hospital.controller.command.SessionAttributeName;
import by.artempvn.hospital.exception.ConnectionException;
import by.artempvn.hospital.model.pool.ConnectionPool;

/**
 * The Class MainController.
 * 
 * @author Artem Piven
 * @version 1.0
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/controller")
public class MainController extends HttpServlet {
	private final static Logger logger = LogManager
			.getLogger(MainController.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestData requestData = new RequestData(request);
		Command command = CommandProvider.defineCommand(requestData);
		Router pageData = command.execute(requestData);
		requestData.insertAttributes();
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(pageData.getPage());
		if (pageData.isDispatchForward()) {
			dispatcher.forward(request, response);
			request.getSession()
					.removeAttribute(RequestAttributeName.SUCCESS_MESSAGE);
			request.getSession().removeAttribute(RequestAttributeName.STATUS_MESSAGE);
			request.getSession()
					.removeAttribute(RequestAttributeName.DOCTOR_REMOVED_MESSAGE);
			request.getSession()
					.removeAttribute(RequestAttributeName.DISCHARGED_MESSAGE);
		} else {
			response.sendRedirect(request.getContextPath() + (String) request
					.getSession().getAttribute(SessionAttributeName.CURRENT_COMMAND));
		}
	}

	/**
	 * Destroy.
	 */
	@Override
	public void destroy() {
		try {
			ConnectionPool.getInstance().destroyPool();
		} catch (ConnectionException ex) {
			logger.log(Level.ERROR, ex);
		}
	}

}
