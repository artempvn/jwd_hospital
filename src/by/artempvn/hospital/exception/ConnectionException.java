package by.artempvn.hospital.exception;

/**
 * The Class ConnectionException.
 * 
 * @author Artem Piven
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ConnectionException extends Exception {

	/**
	 * Instantiates a new connection exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new connection exception.
	 *
	 * @param message the message
	 */
	public ConnectionException(String message) {
		super(message);
	}

}
