package by.tc.auction.service.exception;

public class BetException extends Exception {

	private static final long serialVersionUID = 1L;

	public BetException() {
		super();
	}

	public BetException(String arg0) {
		super(arg0);
	}

	public BetException(Throwable arg0) {
		super(arg0);
	}

	public BetException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BetException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
