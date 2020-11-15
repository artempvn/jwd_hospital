package by.artempvn.hospital.controller.command;

import by.artempvn.hospital.controller.JspPage;
import by.artempvn.hospital.controller.RequestData;

/**
 * The Interface CurrentPageReturnable.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface CurrentPageReturnable {

	/**
	 * Return current page. It is used for commands without their own page. 
	 * In this case page will be set as it was before.
	 *
	 * @param data the data
	 * @return the string
	 */
	default String returnCurrentPage(RequestData data) {
		String currentPage = (String) data
				.getSessionAttributeValue(SessionAttributeName.CURRENT_COMMAND);
		if (currentPage == null) {
			currentPage = JspPage.INDEX;
		}
		return currentPage;
	}

}
