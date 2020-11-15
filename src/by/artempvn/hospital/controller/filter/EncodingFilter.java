package by.artempvn.hospital.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * The Class EncodingFilter.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class EncodingFilter implements Filter {
	private static final String PARAMETER_ENCODING = "encoding";
	private String code;

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
		String codeRequest = request.getCharacterEncoding();
		if (code != null && !code.equalsIgnoreCase(codeRequest)) {
			request.setCharacterEncoding(code);
			response.setCharacterEncoding(code);
		}
		chain.doFilter(request, response);
	}

	/**
	 * Inits.
	 *
	 * @param fConfig the f config
	 * @throws ServletException the servlet exception
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		code = fConfig.getInitParameter(PARAMETER_ENCODING);
	}

	/**
	 * Destroy.
	 */
	public void destroy() {
		code = null;
	}

}
