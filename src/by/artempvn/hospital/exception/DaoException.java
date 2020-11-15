package by.artempvn.hospital.exception;

/**
 * The Class DaoException.
 * 
 * @author Artem Piven
 * @version 1.0
 */
@SuppressWarnings("serial")
public class DaoException extends Exception {

	/**
	 * Instantiates a new dao exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates a new dao exception.
	 *
	 * @param message the message
	 */
	public DaoException(String message) {
		super(message);
	}

}
