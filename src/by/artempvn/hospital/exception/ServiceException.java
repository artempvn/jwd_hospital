package by.artempvn.hospital.exception;

/**
 * The Class ServiceException.
 * 
 * @author Artem Piven
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ServiceException extends Exception {

	/**
	 * Instantiates a new service exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new service exception.
	 *
	 * @param message the message
	 */
	public ServiceException(String message) {
		super(message);
	}

}
