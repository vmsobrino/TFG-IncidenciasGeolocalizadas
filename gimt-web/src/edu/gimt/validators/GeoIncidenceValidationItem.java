package edu.gimt.validators;

import java.util.Date;

import edu.gimt.common.util.Utils;


/**
 * Geo Incidence Item class for validation process.
 * @author Victor M. Sobrino - TFG
 */
public class GeoIncidenceValidationItem implements Comparable<GeoIncidenceValidationItem> {

	private String type;
	private String community;
	private String province;
	private String plate;
	private String cause;
	private String town;
	private String startDatetime;
	private String level;
	private String road;
	private String initialKilometer;
	private String finalKilometer;
	private String sense;
	private String name;
	private String longitude;
	private String latitude;
	private String errorCause;
	private String judicialDistrict;
	private String nuts3;
	
	
	/**
	 * HashCode for this object instance.
	 */
	public int hashCode() {
		return type.hashCode() +
			   community.hashCode() +
			   province.hashCode() +
			   plate.hashCode() +
			   cause.hashCode() +
			   town.hashCode() +
			   startDatetime.hashCode() +
			   level.hashCode() +
			   road.hashCode() +
			   initialKilometer.hashCode() +
			   finalKilometer.hashCode() +
			   sense.hashCode() +
			   name.hashCode() +
			   longitude.hashCode() +
			   latitude.hashCode();
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the community
	 */
	public String getCommunity() {
		return community;
	}
	/**
	 * @param community the community to set
	 */
	public void setCommunity(String community) {
		this.community = community;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the plate
	 */
	public String getPlate() {
		return plate;
	}
	/**
	 * @param plate the plate to set
	 */
	public void setPlate(String plate) {
		this.plate = plate;
	}
	/**
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}
	/**
	 * @param cause the cause to set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}
	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}
	/**
	 * @param town the town to set
	 */
	public void setTown(String town) {
		this.town = town;
	}
	/**
	 * @return the startDatetime
	 */
	public String getStartDatetime() {
		return startDatetime;
	}
	/**
	 * @param startDatetime the startDatetime to set
	 */
	public void setStartDatetime(String startDatetime) {
		this.startDatetime = startDatetime;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * @return the road
	 */
	public String getRoad() {
		return road;
	}
	/**
	 * @param road the road to set
	 */
	public void setRoad(String road) {
		this.road = road;
	}
	/**
	 * @return the initialKilometer
	 */
	public String getInitialKilometer() {
		return initialKilometer;
	}
	/**
	 * @param initialKilometer the initialKilometer to set
	 */
	public void setInitialKilometer(String initialKilometer) {
		this.initialKilometer = initialKilometer;
	}
	/**
	 * @return the finalKilometer
	 */
	public String getFinalKilometer() {
		return finalKilometer;
	}
	/**
	 * @param finalKilometer the finalKilometer to set
	 */
	public void setFinalKilometer(String finalKilometer) {
		this.finalKilometer = finalKilometer;
	}
	/**
	 * @return the sense
	 */
	public String getSense() {
		return sense;
	}
	/**
	 * @param sense the sense to set
	 */
	public void setSense(String sense) {
		this.sense = sense;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * @return the errorCause
	 */
	public String getErrorCause() {
		return errorCause;
	}
	/**
	 * @param errorCause the errorCause to set
	 */
	public void setErrorCause(String errorCause) {
		this.errorCause = errorCause;
	}
	
	public String getJudicialDistrict() {
		return judicialDistrict;
	}

	public void setJudicialDistrict(String judicialDistrict) {
		this.judicialDistrict = judicialDistrict;
	}

	public String getNuts3() {
		return nuts3;
	}

	public void setNuts3(String nuts3) {
		this.nuts3 = nuts3;
	}

	/**
	 * Gets string value for one item
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Tipo: " + (null == this.type? "" : this.type) + "\n");
		sb.append("Comunidad: " + (null == this.community? "" : this.community) + "\n");
		sb.append("Provincia: " + (null == this.province? "" : this.province) + "\n");
		sb.append("Matrícula: " + (null == this.plate? "" : this.plate) + "\n");
		sb.append("Causa: " + (null == this.cause? "" : this.cause) + "\n");
		sb.append("Municipio: " + (null == this.town? "" : this.town) + "\n");
		sb.append("FechaHora: " + (null == this.startDatetime? "" : this.startDatetime) + "\n");
		sb.append("Nivel: " + (null == this.level? "" : this.level) + "\n");
		sb.append("Carretera: " + (null == this.road? "" : this.road) + "\n");
		sb.append("Km Inicial: " + (null == this.initialKilometer? "" : this.initialKilometer) + "\n");
		sb.append("Km Final: " + (null == this.finalKilometer? "" : this.finalKilometer) + "\n");
		sb.append("Sentido: " + (null == this.sense? "" : this.sense) + "\n");
		sb.append("Nombre: " + (null == this.name? "" : this.name) + "\n");
		sb.append("Longitud: " + (null == this.longitude? "" : this.longitude) + "\n");
		sb.append("Latitud: " + (null == this.latitude? "" : this.latitude) + "\n");
		return sb.toString();
	}

	/**
	 * Gets string value for one item
	 */
	public int getCompletedFields() {
		int completed = 0;
		completed += (null == this.type? 0 : 1);
		completed += (null == this.community? 0 : 1);
		completed += (null == this.province? 0 : 1);
		completed += (null == this.plate? 0 : 1);
		completed += (null == this.cause? 0 : 1);
		completed += (null == this.town? 0 : 1);
		completed += (null == this.startDatetime? 0 : 1);
		completed += (null == this.level? 0 : 1);
		completed += (null == this.road? 0 : 1);
		completed += (null == this.initialKilometer? 0 : 1);
		completed += (null == this.finalKilometer? 0 : 1);
		completed += (null == this.sense? 0 : 1);
		completed += (null == this.name? 0 : 1);
		completed += (null == this.longitude? 0 : 1);
		completed += (null == this.latitude? 0 : 1);
		return completed;
	}
	
	/**
	 * Compare two objetct for this class object type.
	 * @param givi Object to compare.
	 * @return True if both objects are equals, False otherwise.
	 */
	public boolean equals(GeoIncidenceValidationItem givi) {
		if (givi == null) {
			return false;
		}
	    if (!(givi instanceof GeoIncidenceValidationItem)) {
	    	return false;
	    }
	    GeoIncidenceValidationItem other = (GeoIncidenceValidationItem) givi;
	    return
		   ((null == this.type && null == other.type) || (null != this.type && null != other.type && this.type.equalsIgnoreCase(other.type))) &&
		   ((null == this.community && null == other.community) || (null != this.community && null != other.community && this.community.equalsIgnoreCase(other.community))) &&
		   ((null == this.province && null == other.province) || (null != this.province && null != other.province && this.province.equalsIgnoreCase(other.province))) &&
		   ((null == this.plate && null == other.plate) || (null != this.plate && null != other.plate && this.plate.equalsIgnoreCase(other.plate))) &&
		   ((null == this.cause && null == other.cause) || (null != this.cause && null != other.cause && this.cause.equalsIgnoreCase(other.cause))) &&
		   ((null == this.town && null == other.town) || (null != this.town && null != other.town && this.town.equalsIgnoreCase(other.town))) &&
		   ((null == this.startDatetime && null == other.startDatetime) || (null != this.startDatetime && null != other.startDatetime && this.startDatetime.equals(other.startDatetime))) &&
		   ((null == this.level && null == other.level) || (null != this.level && null != other.level && this.level.equalsIgnoreCase(other.level))) &&
		   ((null == this.road && null == other.road) || (null != this.road && null != other.road && this.road.equalsIgnoreCase(other.road))) &&
		   ((null == this.initialKilometer && null == other.initialKilometer) || (null != this.initialKilometer && null != other.initialKilometer && this.initialKilometer.equals(other.initialKilometer))) &&
		   ((null == this.finalKilometer && null == other.finalKilometer) || (null != this.finalKilometer && null != other.finalKilometer && this.finalKilometer.equals(other.finalKilometer))) &&
		   ((null == this.sense && null == other.sense) || (null != this.sense && null != other.sense && this.sense.equalsIgnoreCase(other.sense))) &&
		   ((null == this.name && null == other.name) || (null != this.name && null != other.name && this.name.equalsIgnoreCase(other.name))) &&
		   ((null == this.longitude && null == other.longitude) || (null != this.longitude && null != other.longitude && this.longitude.equals(other.longitude))) &&
		   ((null == this.latitude && null == other.latitude) || (null != this.latitude && null != other.latitude && this.latitude.equals(other.latitude)));
  	}

	/**
	 * @return the startDatetime
	 */
	public Date getStartDatetimeObject() {
		return Utils.getDateTime(startDatetime);
	}
	
	/**
	 * Order by incidence by distinct fields, date time (asc) and number of not null fields (desc).
	 */
	@Override
	public int compareTo(GeoIncidenceValidationItem o) {

		int comparation = 0;
		
		if (!this.equals(o)) {

			// First, province
			if (null != this.getProvince() && null != ((GeoIncidenceValidationItem) o).getProvince()) {
				comparation = this.getProvince().compareTo(((GeoIncidenceValidationItem) o).getProvince());
			}
			else {
				if (null == this.getProvince() && null != ((GeoIncidenceValidationItem) o).getProvince()) {
					comparation = -1;
				}
				else {
					if (null != this.getProvince() && null == ((GeoIncidenceValidationItem) o).getProvince()) {
						comparation = 1;
					}
					else {
						comparation = 0;
					}
				}
			}
	
			// Second, cause type
			if (comparation == 0) {
				if (null != this.getType() && null != ((GeoIncidenceValidationItem) o).getType()) {
					comparation = this.getType().compareTo(((GeoIncidenceValidationItem) o).getType());
				}
				else {
					if (null == this.getType() && null != ((GeoIncidenceValidationItem) o).getType()) {
						comparation = -1;
					}
					else {
						if (null != this.getType() && null == ((GeoIncidenceValidationItem) o).getType()) {
							comparation = 1;
						}
						else {
							comparation = 0;
						}
					}
				}
			}
	
			// Third, cause subtype (cause)
			if (comparation == 0) {
				if (null != this.getCause() && null != ((GeoIncidenceValidationItem) o).getCause()) {
					comparation = this.getCause().compareTo(((GeoIncidenceValidationItem) o).getCause());
				}
				else {
					if (null == this.getCause() && null != ((GeoIncidenceValidationItem) o).getCause()) {
						comparation = -1;
					}
					else {
						if (null != this.getCause() && null == ((GeoIncidenceValidationItem) o).getCause()) {
							comparation = 1;
						}
						else {
							comparation = 0;
						}
					}
				}
			}
	
			// Fourth, Town name
			if (comparation == 0) {
				if (null != this.getTown() && null != ((GeoIncidenceValidationItem) o).getTown()) {
					comparation = this.getTown().compareTo(((GeoIncidenceValidationItem) o).getTown());
				}
				else {
					if (null == this.getTown() && null != ((GeoIncidenceValidationItem) o).getTown()) {
						comparation = -1;
					}
					else {
						if (null != this.getTown() && null == ((GeoIncidenceValidationItem) o).getTown()) {
							comparation = 1;
						}
						else {
							comparation = 0;
						}
					}
				}
			}
	
			// Fifth, Road name
			if (comparation == 0) {
				if (null != this.getRoad() && null != ((GeoIncidenceValidationItem) o).getRoad()) {
					comparation = this.getRoad().compareTo(((GeoIncidenceValidationItem) o).getRoad());
				}
				else {
					if (null == this.getRoad() && null != ((GeoIncidenceValidationItem) o).getRoad()) {
						comparation = -1;
					}
					else {
						if (null != this.getRoad() && null == ((GeoIncidenceValidationItem) o).getRoad()) {
							comparation = 1;
						}
						else {
							comparation = 0;
						}
					}
				}
			}
	
			// Sixth, Start date-time
			if (comparation == 0) {
				if (null != this.getStartDatetime() && null != ((GeoIncidenceValidationItem) o).getStartDatetime()) {
					comparation = Utils.compareDates(this.getStartDatetime(), ((GeoIncidenceValidationItem) o).getStartDatetime());
				}
				else {
					if (null == this.getStartDatetime() && null != ((GeoIncidenceValidationItem) o).getStartDatetime()) {
						comparation = -1;
					}
					else {
						if (null != this.getStartDatetime() && null == ((GeoIncidenceValidationItem) o).getStartDatetime()) {
							comparation = 1;
						}
						else {
							comparation = 0;
						}
					}
				}
			}
			// Seventh, by completed number of fields
			if (comparation == 0) {
				comparation = ((GeoIncidenceValidationItem) o).getCompletedFields() - this.getCompletedFields();
			}
		}

		return comparation;
	}

}
