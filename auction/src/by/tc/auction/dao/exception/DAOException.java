package by.tc.auction.dao.exception;

/**
 * An exception of a DAO layer.
 * @author semenovich
 *
 */
public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public DAOException() {
		super();
	}

	/**
	 * Constructor with parameters.
	 * @param message - a message of an exception.
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * Constructor with parameters.
	 * @param cause - a cause of an exception. 
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor with parameters.
	 * @param message - a message of an exception. 
	 * @param cause - a cause of an exception.
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor with parameters.
	 * @param message - a message of exception.
	 * @param cause - a cause of exception.
	 * @param enableSuppression - enable a suppression.
	 * @param writableStackTrace - enable a writable stack trace. 
	 */
	public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
