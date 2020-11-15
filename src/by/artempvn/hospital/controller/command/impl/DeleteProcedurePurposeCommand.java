package by.artempvn.hospital.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.controller.JspPage;
import by.artempvn.hospital.controller.RequestData;
import by.artempvn.hospital.controller.command.Command;
import by.artempvn.hospital.controller.command.CurrentPageReturnable;
import by.artempvn.hospital.controller.command.RequestAttributeName;
import by.artempvn.hospital.controller.command.RequestParameterName;
import by.artempvn.hospital.controller.command.Router;
import by.artempvn.hospital.controller.command.SessionAttributeName;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.service.PurposeService;
import by.artempvn.hospital.model.service.impl.PurposeServiceImpl;

/**
 * The Class DeleteProcedurePurposeCommand.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class DeleteProcedurePurposeCommand
		implements Command, CurrentPageReturnable {
	private static final String SUCCESS_MESSAGE = "true";
	private static final String ERROR_MESSAGE_INCORRECT = "incorrect";
	private static final Logger logger = LogManager
			.getLogger(DeleteProcedurePurposeCommand.class);

	@Override
	public Router execute(RequestData data) {
		Router pageData = new Router();
		pageData.setPage(returnCurrentPage(data));
		if (data.getParameterValue(RequestParameterName.PROCEDURE_ID) != null) {
			long procedureId = Long.valueOf(
					data.getParameterValue(RequestParameterName.PROCEDURE_ID)[0]);
			PurposeService service = PurposeServiceImpl.getInstance();
			try {
				boolean isDeleted = service.deleteProcedurePurpose(procedureId);
				if (isDeleted) {
					data.putSessionAttribute(SessionAttributeName.SUCCESS_MESSAGE,
							SUCCESS_MESSAGE);
					pageData.setRedirect();
				} else {
					logger.log(Level.WARN, "Incorrect input");
					data.putRequestAttribute(RequestAttributeName.ERROR_NAME,
							ERROR_MESSAGE_INCORRECT);
				}
			} catch (ServiceException ex) {
				logger.log(Level.ERROR, ex);
				data.putRequestAttribute(RequestAttributeName.ERROR, ex);
				pageData.setPage(JspPage.ERROR);
			}
		}
		return pageData;
	}

}
