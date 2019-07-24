package edu.gimt.persistence;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;


/**
 * Geo Incidence Entity class implementation.
 * @author Victor M. Sobrino - TFG
 */
@Entity
@Table(name = "gimt_geo_incidence")
@NamedQueries({@NamedQuery(name = "GeoIncidence.findAll", query = "SELECT gi FROM GeoIncidence gi"),
			   @NamedQuery(name = "GeoIncidence.findById", query = "SELECT gi FROM GeoIncidence gi WHERE gi.idGeoIncidence = :idGeoIncidence"),
			   @NamedQuery(name = "GeoIncidence.findByKeys", query = "SELECT gi FROM GeoIncidence gi WHERE gi.idGeoCommunity = :idGeoCommunity AND gi.startDatetime = :startDatetime AND gi.longitude = :longitude AND gi.latitude = :latitude"),
			   @NamedQuery(name = "GeoIncidence.deleteByDate", query = "DELETE FROM GeoIncidence gi WHERE :startDatetime <= gi.startDatetime AND gi.startDatetime <= :endDateTime")})


public class GeoIncidence {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_geo_incidence")
	private Long idGeoIncidence;

	@Column(name = "id_geo_community")
	private Integer idGeoCommunity;

	@Column(name = "nuts3_code")
	private String nuts3;

	@Column(name = "id_geo_province")
	private Integer idGeoProvince;

	@Column(name = "id_geo_cause_type")
	private Integer idGeoCauseType;

	@Column(name = "id_geo_cause_subtype")
	private Integer idGeoCauseSubtype;
	
	@Column(name = "id_geo_town")
	private Integer idTown;

	@Column(name = "town_name")
	private String townName;

	@Column(name = "id_geo_jd")
	private Integer idJd;

	@Column(name = "start_datetime")
	private java.util.Date startDatetime;

	@Column(name = "end_datetime")
	private java.util.Date endDatetime;

	@Column(name = "id_geo_road_level")
	private Integer idGeoRoadLevel;

	@Column(name = "mountain_port_level")
	private String mountainPortLevel;

	@Column(name = "road_code")
	private String roadCode;

	@Column(name = "initial_kilometer")
	private Float initialKilometer;

	@Column(name = "final_kilometer")
	private Float finalKilometer;
	
	@Column(name = "sense")
	private String sense;

	@Column(name = "name")
	private String name;

	@Column(name = "longitude")
	private BigDecimal longitude;

	@Column(name = "latitude")
	private BigDecimal latitude;

	@Column(name = "month_of_year")
	@Generated(GenerationTime.ALWAYS)
	private String monthOfYear;

	@Column(name = "day_of_week")
	@Generated(GenerationTime.ALWAYS)
	private String dayOfWeek;

	@Column(name = "hour_of_day")
	@Generated(GenerationTime.ALWAYS)
	private Integer hourOfDay;

	@Column(name = "num_day_of_week")
	@Generated(GenerationTime.ALWAYS)
	private Integer numDayOfWeek;

	@Column(name = "num_month_of_year")
	@Generated(GenerationTime.ALWAYS)
	private Integer numMonthOfYear;

	/**
	 * @return the idGeoCommunity
	 */
	public Integer getIdGeoCommunity() {
		return idGeoCommunity;
	}

	/**
	 * @param idGeoCommunity the idGeoCommunity to set
	 */
	public void setIdGeoCommunity(Integer idGeoCommunity) {
		this.idGeoCommunity = idGeoCommunity;
	}

	/**
	 * @return the idGeoProvince
	 */
	public Integer getIdGeoProvince() {
		return idGeoProvince;
	}

	/**
	 * @param idGeoProvince the idGeoProvince to set
	 */
	public void setIdGeoProvince(Integer idGeoProvince) {
		this.idGeoProvince = idGeoProvince;
	}

	/**
	 * @return the idGeoCauseType
	 */
	public Integer getIdGeoCauseType() {
		return idGeoCauseType;
	}

	/**
	 * @param idGeoCauseType the idGeoCauseType to set
	 */
	public void setIdGeoCauseType(Integer idGeoCauseType) {
		this.idGeoCauseType = idGeoCauseType;
	}

	/**
	 * @return the idGeoCauseSubtype
	 */
	public Integer getIdGeoCauseSubtype() {
		return idGeoCauseSubtype;
	}

	/**
	 * @param idGeoCauseSubtype the idGeoCauseSubtype to set
	 */
	public void setIdGeoCauseSubtype(Integer idGeoCauseSubtype) {
		this.idGeoCauseSubtype = idGeoCauseSubtype;
	}

	/**
	 * @return the townName
	 */
	public String getTownName() {
		return townName;
	}

	/**
	 * @param townName the townName to set
	 */
	public void setTownName(String townName) {
		this.townName = townName;
	}

	/**
	 * @return the startDatetime
	 */
	public java.util.Date getStartDatetime() {
		return startDatetime;
	}

	/**
	 * @param startDatetime the startDatetime to set
	 */
	public void setStartDatetime(java.util.Date startDatetime) {
		this.startDatetime = startDatetime;
	}

	/**
	 * @return the endDatetime
	 */
	public java.util.Date getEndDatetime() {
		return endDatetime;
	}

	/**
	 * @param endDatetime the endDatetime to set
	 */
	public void setEndDatetime(java.util.Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	/**
	 * @return the idGeoRoadLevel
	 */
	public Integer getIdGeoRoadLevel() {
		return idGeoRoadLevel;
	}

	/**
	 * @param idGeoRoadLevel the idGeoRoadLevel to set
	 */
	public void setIdGeoRoadLevel(Integer idGeoRoadLevel) {
		this.idGeoRoadLevel = idGeoRoadLevel;
	}

	/**
	 * @return the mountainPortLevel
	 */
	public String getMountainPortLevel() {
		return mountainPortLevel;
	}

	/**
	 * @param mountainPortLevel the mountainPortLevel to set
	 */
	public void setMountainPortLevel(String mountainPortLevel) {
		this.mountainPortLevel = mountainPortLevel;
	}

	/**
	 * @return the roadCode
	 */
	public String getRoadCode() {
		return roadCode;
	}

	/**
	 * @param roadCode the roadCode to set
	 */
	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}

	/**
	 * @return the initialKilometer
	 */
	public Float getInitialKilometer() {
		return initialKilometer;
	}

	/**
	 * @param initialKilometer the initialKilometer to set
	 */
	public void setInitialKilometer(Float initialKilometer) {
		this.initialKilometer = initialKilometer;
	}

	/**
	 * @return the finalKilometer
	 */
	public Float getFinalKilometer() {
		return finalKilometer;
	}

	/**
	 * @param finalKilometer the finalKilometer to set
	 */
	public void setFinalKilometer(Float finalKilometer) {
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
	public BigDecimal getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public BigDecimal getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the idGeoIncidence
	 */
	public Long getIdGeoIncidence() {
		return idGeoIncidence;
	}

	/**
	 * @return the monthOfYear
	 */
	public String getMonthOfYear() {
		return monthOfYear;
	}

	/**
	 * @return the dayOfWeek
	 */
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * @return the hourOfDay
	 */
	public Integer getHourOfDay() {
		return hourOfDay;
	}

	/**
	 * @return the numDayOfWeek
	 */
	public Integer getNumDayOfWeek() {
		return numDayOfWeek;
	}

	/**
	 * @return the numMonthOfYear
	 */
	public Integer getNumMonthOfYear() {
		return numMonthOfYear;
	}

	public String getNuts3() {
		return nuts3;
	}

	public void setNuts3(String nuts3) {
		this.nuts3 = nuts3;
	}

	public Integer getIdTown() {
		return idTown;
	}

	public void setIdTown(Integer idTown) {
		this.idTown = idTown;
	}

	public Integer getIdJd() {
		return idJd;
	}

	public void setIdJd(Integer idJd) {
		this.idJd = idJd;
	}

}
