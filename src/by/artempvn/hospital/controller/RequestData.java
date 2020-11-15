package by.artempvn.hospital.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The Class RequestData.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class RequestData {
	private HttpServletRequest request;
	private HashMap<String, Object> requestAttributes;
	private Map<String, String[]> requestParameters;
	private HashMap<String, Object> sessionAttributes;

	/**
	 * Instantiates a new request data.
	 *
	 * @param request the request
	 */
	public RequestData(HttpServletRequest request) {
		this.request = request;
		extractValues();
	}

	private void extractValues() {
		Enumeration<String> enumeratorAttribute = request.getAttributeNames();
		requestAttributes = new HashMap<>();
		while (enumeratorAttribute.hasMoreElements()) {
			String attributeName = enumeratorAttribute.nextElement();
			requestAttributes.put(attributeName, request.getAttribute(attributeName));
		}
		HttpSession session = request.getSession();
		sessionAttributes = new HashMap<>();
		Enumeration<String> enumeratorSession = session.getAttributeNames();
		while (enumeratorSession.hasMoreElements()) {
			String attributeName = enumeratorSession.nextElement();
			sessionAttributes.put(attributeName, session.getAttribute(attributeName));
		}
		requestParameters = request.getParameterMap();
	}

	/**
	 * Insert attributes.
	 */
	public void insertAttributes() {
		requestAttributes.entrySet().stream().forEach(attribute -> request
				.setAttribute(attribute.getKey(), attribute.getValue()));
		sessionAttributes.entrySet().stream().forEach(attribute -> request
				.getSession().setAttribute(attribute.getKey(), attribute.getValue()));
	}

	/**
	 * Gets the parameter value.
	 *
	 * @param parameterName the parameter name
	 * @return the parameter value
	 */
	public String[] getParameterValue(String parameterName) {
		return requestParameters.get(parameterName);
	}

	/**
	 * Gets the request attribute value.
	 *
	 * @param attributeName the attribute name
	 * @return the request attribute value
	 */
	public Object getRequestAttributeValue(String attributeName) {
		return requestAttributes.get(attributeName);
	}

	/**
	 * Gets the session attribute value.
	 *
	 * @param attributeName the attribute name
	 * @return the session attribute value
	 */
	public Object getSessionAttributeValue(String attributeName) {
		return sessionAttributes.get(attributeName);
	}

	/**
	 * Put request attribute.
	 *
	 * @param attribute the attribute
	 * @param value the value
	 */
	public void putRequestAttribute(String attribute, Object value) {
		requestAttributes.put(attribute, value);
	}

	/**
	 * Put session attribute.
	 *
	 * @param attribute the attribute
	 * @param value the value
	 */
	public void putSessionAttribute(String attribute, Object value) {
		sessionAttributes.put(attribute, value);
	}

	/**
	 * Terminate session.
	 */
	public void terminateSession() {
		sessionAttributes = new HashMap<>();
		request.getSession().invalidate();
	}
}
