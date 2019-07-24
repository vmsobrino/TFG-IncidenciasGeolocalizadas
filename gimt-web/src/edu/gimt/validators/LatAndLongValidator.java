package edu.gimt.validators;

import edu.gimt.validators.exception.ValidationException;


public class LatAndLongValidator extends GeoIncidenceAbstractValidator implements IValidator {

	public LatAndLongValidator(Object validationItem) {
		super(validationItem);
	}

	@Override
	public void validate() throws ValidationException {
		StringBuilder errors = new StringBuilder();
		String latitude = null;
		String longitude = null;
		String[] buffer = null;
		String stParams = null;

		stParams = getParams(); // Parameters for validation

		latitude = (validationItem != null ? validationItem.getLatitude() : null);
		longitude = (validationItem != null ? validationItem.getLongitude() : null);
		if (latitude == null || longitude == null) {
			errors.append("Valor de Latitud y/o Longitud nulos.");
			throw new ValidationException (errors.toString());
		}
		else {
			if (stParams != null) {
				buffer = stParams.split(";");
				if (buffer.length == 4) { // Min & Max Latitude + Min & Max Longitude
					try {
						Double minLatitude = Double.parseDouble(buffer[0]);
						Double maxLatitude = Double.parseDouble(buffer[1]);
						Double minLongitude = Double.parseDouble(buffer[2]);
						Double maxLongitude = Double.parseDouble(buffer[3]);
						Double lat = Double.parseDouble(latitude);
						Double lon = Double.parseDouble(longitude);
						if (lat.doubleValue() < minLatitude.doubleValue() ||
							lat.doubleValue() > maxLatitude.doubleValue() ||
							lon.doubleValue() < minLongitude.doubleValue() ||
							lon.doubleValue() > maxLongitude.doubleValue() ||
							lat.doubleValue() == 0 || lon.doubleValue() == 0) {
							errors.append("Latitud y/o Longitud no correctas.");
							throw new ValidationException (errors.toString());
						}
					}
					catch (NumberFormatException nfe) {
						errors.append("Latitud y/o Longitud no tiene un valor numérico.");
						throw new ValidationException (errors.toString());
					}
				}
				else {
					errors.append("Faltan parámetros para validación de Latitud y Longitud: <" + stParams + ">");
					throw new ValidationException (errors.toString());
				}
			}
		}
	}

}