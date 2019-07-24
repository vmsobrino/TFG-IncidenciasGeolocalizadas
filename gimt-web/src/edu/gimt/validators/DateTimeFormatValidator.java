package edu.gimt.validators;

import edu.gimt.common.util.Utils;
import edu.gimt.validators.exception.ValidationException;


public class DateTimeFormatValidator extends GeoIncidenceAbstractValidator implements IValidator {

	public DateTimeFormatValidator(Object validationItem) {
		super(validationItem);
	}

	@Override
	public void validate() throws ValidationException {
		StringBuilder errors = new StringBuilder();
		String strDateTime = null;
		String[] buffer = null;

		//stParams = getStParams(); //no recibe parametros

		strDateTime = (validationItem != null ? validationItem.getStartDatetime() : null);
		if (strDateTime != null) {
			buffer = strDateTime.split(" - ");
			if (buffer.length == 1) { // Date-Time
				if (!Utils.validDateTime(strDateTime)) {
					errors.append("La fecha-hora: <" + strDateTime + "> no es correcta.");
					throw new ValidationException (errors.toString());
				}
			}
			else {
				if (buffer.length == 2) { // Date - Date
					for (String date : buffer) {
						if (!Utils.validDate(date)) {
							errors.append("La fecha: <" + strDateTime + "> no es correcta.");
							throw new ValidationException (errors.toString());
						}
					}
				}
				else {
					errors.append("El Formato de <fechahora_ini> no es correcto: <" + strDateTime + ">");
					throw new ValidationException (errors.toString());
				}
			}
		}
	}

}