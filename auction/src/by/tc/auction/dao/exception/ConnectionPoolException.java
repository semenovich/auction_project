package by.tc.auction.dao.exception;

/**
 * An exception of a connection pool.
 * @author semenovich
 *
 */
public class ConnectionPoolException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ConnectionPoolException() {
		super();
	}

	/**
	 * Constructor with parameters.
	 * @param message - message of an exception.
	 */
	public ConnectionPoolException(String message) {
		super(message);
	}

	/**
	 * Constructor with parameters.
	 * @param cause - cause of an exception.
	 */
	public ConnectionPoolException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor with parameters.
	 * @param message - a message of an exception.
	 * @param cause - a cause of an exception. 
	 */
	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor with parameters.
	 * @param message - a message of exception.
	 * @param cause - a cause of exception.
	 * @param enableSuppression - enable a suppression.
	 * @param writableStackTrace - enable a writable stack trace. 
	 */
	public ConnectionPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
