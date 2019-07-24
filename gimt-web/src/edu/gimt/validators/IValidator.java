package edu.gimt.validators;

import edu.gimt.validators.exception.ValidationException;

/**
 * Interface for Validators.
 * @author Victor M. Sobrino - TFG
 */
public interface IValidator {

	public void validate() throws ValidationException;
	public String getParams();
	public void setParams(String Params);
	
}
