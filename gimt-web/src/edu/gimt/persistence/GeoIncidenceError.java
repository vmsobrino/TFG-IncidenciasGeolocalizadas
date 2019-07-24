package edu.gimt.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * Geo Incidence Entity class implementation.
 * @author Victor M. Sobrino - TFG
 */
@Entity
@Table(name = "gimt_geo_incidence_error")
@NamedQueries({@NamedQuery(name = "GeoIncidenceError.findAll", query = "SELECT gie FROM GeoIncidenceError gie"),
			   @NamedQuery(name = "GeoIncidenceError.deleteByDate", query = "DELETE FROM GeoIncidenceError gie WHERE :startDatetime <= gie.startDatetime AND gie.startDatetime <= :endDateTime")})

public class GeoIncidenceError {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_geo_incidence_error")
	private Long idGeoIncidenceError;

	@Column(name = "incidence_type")
	private String incidenceType;

	@Column(name = "community")
	private String community;

	@Column(name = "province")
	private String province;

	@Column(name = "plate")
	private String plate;
	
	@Column(name = "cause")
	private String cause;

	@Column(name = "town")
	private String town;

	@Column(name = "start_datetime")
	private String startDatetime;

	@Column(name = "incidence_level")
	private String incidenceLevel;

	@Column(name = "road")
	private String road;

	@Column(name = "initial_kilometer")
	private String initialKilometer;

	@Column(name = "final_kilometer")
	private String finalKilometer;

	@Column(name = "sense")
	private String sense;

	@Column(name = "name")
	private String name;

	@Column(name = "longitude")
	private String longitude;

	@Column(name = "latitude")
	private String latitude;
	
	@Column(name = "error_cause")
	private String errorCause;

	/**
	 * @return the incidenceType
	 */
	public String getIncidenceType() {
		return incidenceType;
	}

	/**
	 * @param incidenceType the incidenceType to set
	 */
	public void setIncidenceType(String incidenceType) {
		this.incidenceType = incidenceType;
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
	 * @return the incidenceLevel
	 */
	public String getIncidenceLevel() {
		return incidenceLevel;
	}

	/**
	 * @param incidenceLevel the incidenceLevel to set
	 */
	public void setIncidenceLevel(String incidenceLevel) {
		this.incidenceLevel = incidenceLevel;
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

	/**
	 * @return the idGeoIncidenceError
	 */
	public Long getIdGeoIncidenceError() {
		return idGeoIncidenceError;
	}

}
