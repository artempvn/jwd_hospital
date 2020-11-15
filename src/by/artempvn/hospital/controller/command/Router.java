package by.artempvn.hospital.controller.command;

import by.artempvn.hospital.controller.JspPage;

/**
 * The Class Router.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class Router {

	private String Page = JspPage.INDEX;
	private boolean isDispathForward = true;

	/**
	 * Gets the page.
	 *
	 * @return the page
	 */
	public String getPage() {
		return Page;
	}

	/**
	 * Sets the page.
	 *
	 * @param page the new page
	 */
	public void setPage(String page) {
		Page = page;
	}

	/**
	 * Sets the redirect.
	 */
	public void setRedirect() {
		this.isDispathForward = false;
	}

	/**
	 * Checks if is dispatch forward.
	 *
	 * @return true, if is dispatch forward
	 */
	public boolean isDispatchForward() {
		return isDispathForward;
	}

}
