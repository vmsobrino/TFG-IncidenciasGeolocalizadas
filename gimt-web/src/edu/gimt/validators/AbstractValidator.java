package edu.gimt.validators;

import javax.persistence.EntityManager;


/**
 * Abstract Class for Validator.
 * @author Victor M. Sobrino - TFG
 */
public abstract class AbstractValidator implements IValidator {

	protected String params;
	protected EntityManager em;
	
	public AbstractValidator() {
	}
	
	@Override
	public String getParams() {
		return params;
	}

	@Override
	public void setParams(String params) {
		this.params = params;
	}

}
