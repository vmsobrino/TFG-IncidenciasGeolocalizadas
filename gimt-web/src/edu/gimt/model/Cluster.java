package edu.gimt.model;

/**
 * Predictor for cluster from model/5d2c145ceba31d53b00002a0 Predictive model by
 * BigML - Machine Learning Made Easy
 */
public class Cluster {

	/**
	 * Cluster prediction from number of distinct incidences in a year.
	 * @param vuelcos Rollovers num.
	 * @param accidentesInvierno winter incidences num.
	 * @param accidentesOtono autumn incidences num.
	 * @param nivelVerdeBlanco Number of incidences with Green or white level.
	 * @param nivelAmarillo Number of incidences with Yellow level.
	 * @param hora1218 Number of incidences between 12 and 18 h.
	 * @param hora1800 Number of incidences between 12 and 18 h.
	 * @return Zone type string.
	 */
	public String predictCluster(Double vuelcos,
								 Double accidentesInvierno,
								 Double accidentesOtono,
								 Double nivelVerdeBlanco,
								 Double nivelAmarillo,
								 Double hora1218,
								 Double hora1800) {
		if (hora1218 == null) {
			return "Baja Siniestralidad";
		} else if (hora1218.doubleValue() > 181) {
			if (hora1800 == null) {
				return "Siniestralidad Media";
			} else if (hora1800.doubleValue() > 375) {
				if (accidentesInvierno == null) {
					return "Muy Alta Siniestralidad";
				} else if (accidentesInvierno.doubleValue() > 327) {
					return "Muy Alta Siniestralidad";
				} else if (accidentesInvierno.doubleValue() <= 327) {
					return "Muy Baja Siniestralidad";
				}
			} else if (hora1800.doubleValue() <= 375) {
				if (nivelVerdeBlanco == null) {
					return "Siniestralidad Media";
				} else if (nivelVerdeBlanco.doubleValue() > 495) {
					if (vuelcos == null) {
						return "Alta Siniestralidad";
					} else if (vuelcos.doubleValue() > 43) {
						return "Alta Siniestralidad";
					} else if (vuelcos.doubleValue() <= 43) {
						if (accidentesOtono == null) {
							return "Siniestralidad Media";
						} else if (accidentesOtono.doubleValue() > 347) {
							return "Alta Siniestralidad";
						} else if (accidentesOtono.doubleValue() <= 347) {
							return "Siniestralidad Media";
						}
					}
				} else if (nivelVerdeBlanco.doubleValue() <= 495) {
					if (nivelAmarillo == null) {
						return "Siniestralidad Media";
					} else if (nivelAmarillo.doubleValue() > 494) {
						return "Alta Siniestralidad";
					} else if (nivelAmarillo.doubleValue() <= 494) {
						return "Siniestralidad Media";
					}
				}
			}
		} else if (hora1218.doubleValue() <= 181) {
			return "Baja Siniestralidad";
		}
		return null;
	}
}