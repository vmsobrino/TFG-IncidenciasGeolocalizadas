package edu.gimt.validators;

import edu.gimt.validators.exception.ValidationException;


public class KilometerValidator extends GeoIncidenceAbstractValidator implements IValidator {

	public KilometerValidator(Object validationItem) {
		super(validationItem);
	}

	@Override
	public void validate() throws ValidationException {
		StringBuilder errors = new StringBuilder();
		String initialKm = null;
		String finalKm = null;

		//stParams = getParams(); // No Parameters for validation

		initialKm = (validationItem != null ? validationItem.getInitialKilometer() : null);
		finalKm = (validationItem != null ? validationItem.getFinalKilometer() : null);
		if (initialKm != null && finalKm != null ) {
			try {
				Double.parseDouble(initialKm);
				Double.parseDouble(finalKm);
			}
			catch (NumberFormatException nfe) {
				errors.append("Km. Inicial y/o Final: <" + initialKm + ", " + finalKm + "> no tienen un valor numérico.");
				throw new ValidationException (errors.toString());
			}
		}
	}

}