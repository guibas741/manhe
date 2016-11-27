package br.org.catolicasc.manhe.exceptions;

public class ExistentRecordException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExistentRecordException(){
		super();
	}
	
	public ExistentRecordException(String message) {
		super(message);
	}

	public ExistentRecordException(Throwable cause) {
		super(cause);
	}

	public ExistentRecordException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExistentRecordException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
