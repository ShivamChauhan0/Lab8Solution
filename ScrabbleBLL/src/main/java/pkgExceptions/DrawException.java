package pkgExceptions;

import eNumExceptions.eDrawExceptionType;

public class DrawException extends Exception {

	private eDrawExceptionType DrawExceptionType;

	public DrawException(eDrawExceptionType drawExceptionType) {
		super();
		DrawExceptionType = drawExceptionType;
	}

	public eDrawExceptionType getDrawExceptionType() {
		return DrawExceptionType;
	}
	
	
}
