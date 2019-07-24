package edu.gimt.validators;

import edu.gimt.validators.exception.ValidationException;


public class DateTimeRequiredValidator extends GeoIncidenceAbstractValidator implements IValidator {

	public DateTimeRequiredValidator(Object validationItem) {
		super(validationItem);
	}

	/**
	 * Check if incidence date-time is not empty or null.
	 */
	@Override
	public void validate() throws ValidationException {
		StringBuilder errors = new StringBuilder();
		String strDateTime = null;

		strDateTime = (validationItem != null ? validationItem.getStartDatetime() : null);
		if (strDateTime == null) {
			errors.append("La fecha-hora es vacia.");
			throw new ValidationException (errors.toString());
		}
		else {
			if (strDateTime.isEmpty() || strDateTime.trim().isEmpty()) {
				errors.append("La fecha-hora es vacia.");
				throw new ValidationException (errors.toString());
			}
		}
	}

}