package by.artempvn.hospital.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class JspRedirectFilter.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class JspRedirectFilter implements Filter {
	private static final String PARAMETER_INDEX_PATH = "INDEX_PATH";
	private String indexPath;

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
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
		chain.doFilter(request, response);
	}

	/**
	 * Inits.
	 *
	 * @param fConfig the f config
	 * @throws ServletException the servlet exception
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		indexPath = fConfig.getInitParameter(PARAMETER_INDEX_PATH);
	}

	/**
	 * Destroy.
	 */
	public void destroy() {
		indexPath = null;
	}

}
