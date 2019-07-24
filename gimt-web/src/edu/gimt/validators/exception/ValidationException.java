/**
 * 
 */
package edu.gimt.validators.exception;

/**
 * Exception Class for Validation process.
 * @author Victor M. Sobrino - TFG
 */
public class ValidationException extends Exception {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -7717446533864515672L;
	
	/**
	 * Generated Exception with message only.
	 * @param message Description of our message exception.
	 */
	public ValidationException(String message) {
		super(message);
	}

	/**
	 * Generated Exception with message and Exception Object.
	 * @param message Description of out message exception.
	 * @param exception Exeption object.
	 */
	public ValidationException(String message, Exception exception) {
		super(message, exception);
	}
}
