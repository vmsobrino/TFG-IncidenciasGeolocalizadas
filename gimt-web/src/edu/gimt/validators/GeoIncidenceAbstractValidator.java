package edu.gimt.validators;

import edu.gimt.validators.exception.ValidationException;


/**
 * Extends Abstract-Validator Class.
 * @author Victor M. Sobrino - TFG
 */
public class GeoIncidenceAbstractValidator extends AbstractValidator implements IValidator {

	// Item to validate
	protected GeoIncidenceValidationItem validationItem;

	/**
	 * Constructor.
	 * @param validationItem
	 */
	public GeoIncidenceAbstractValidator(Object validationItem) {
		super();
		if ((validationItem != null) && (validationItem instanceof GeoIncidenceValidationItem)) {
			this.validationItem = (GeoIncidenceValidationItem)validationItem;
		}
	}

	@Override
	public void validate() throws ValidationException { // Validation process
		throw new ValidationException("GeoIncidenceAbstractValidator: metodo no implementado!");
	}

	// Gets item to validate
	protected GeoIncidenceValidationItem getGeoIncidenceValidationItem() {
		return validationItem;
	}

	// Sets item to validate
	protected void setGeoIncidenceValidationItem(GeoIncidenceValidationItem validationItem) {
		this.validationItem = validationItem;
	}
	
}
