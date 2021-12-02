package Exceptions;

@SuppressWarnings("serial")
public class IllegalInputException extends Exception {
	public IllegalInputException(String message, String obj) {
		super(message + ", " + obj);
	}
}
