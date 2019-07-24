package edu.gimt.model;

import java.math.BigDecimal;
import java.util.Date;

import edu.gimt.common.Constants;
import edu.gimt.common.util.Utils;

public class IncidenceBean {
	
	private String communityName = null;
	private String provinceName = null;
	private String causeTypeName = null;
	private String causeSubtypeName = null;
	private String townName = null;
	private java.util.Date startDatetime = null;
	private java.util.Date endDatetime = null;
	private String levelColour = null;
	private String levelFlow = null;
	private String mountainPortLevel = null;
	private String roadCode = null;
	private Float initialKilometer = null;
	private Float finalKilometer = null;
	private String sense = null;
	private String name = null;
	private BigDecimal longitude = null;
	private BigDecimal latitude = null;
	private String markerIcon = null;
	
	/**
	 * @return the markerIcon
	 */
	public String getMarkerIcon() {
		return markerIcon;
	}

	/**
	 * @return the communityName
	 */
	public String getCommunityName() {
		return communityName;
	}
	/**
	 * @param communityName the communityName to set
	 */
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	/**
	 * @return the provinceName
	 */
	public String getProvinceName() {
		return provinceName;
	}
	/**
	 * @param provinceName the provinceName to set
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	/**
	 * @return the causeTypeName
	 */
	public String getCauseTypeName() {
		return causeTypeName;
	}
	/**
	 * @param causeTypeName the causeTypeName to set
	 */
	public void setCauseTypeName(String causeTypeName) {
		this.causeTypeName = causeTypeName;
	}
	/**
	 * @return the causeSubtypeName
	 */
	public String getCauseSubtypeName() {
		return causeSubtypeName;
	}
	/**
	 * @param causeSubtypeName the causeSubtypeName to set
	 */
	public void setCauseSubtypeName(String causeSubtypeName) {
		this.causeSubtypeName = causeSubtypeName;
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
	 * @return the levelColour
	 */
	public String getLevelColour() {
		return levelColour;
	}
	/**
	 * @param levelColour the levelColour to set
	 */
	public void setLevelColour(String levelColour) {
		this.levelColour = levelColour;
	}
	/**
	 * @return the levelFlow
	 */
	public String getLevelFlow() {
		return levelFlow;
	}
	/**
	 * @param levelFlow the levelFlow to set
	 */
	public void setLevelFlow(String levelFlow) {
		this.levelFlow = levelFlow;
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
	 * @return the longitude
	 */
	public String getLongitudeString() {
		return Utils.getLatLonFormat(longitude);
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
	 * @return the latitude
	 */
	public String getLatitudeString() {
		return Utils.getLatLonFormat(latitude);
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * @param communityName
	 * @param provinceName
	 * @param causeTypeName
	 * @param causeSubtypeName
	 * @param townName
	 * @param startDatetime
	 * @param endDatetime
	 * @param levelColour
	 * @param levelFlow
	 * @param mountainPortLevel
	 * @param roadCode
	 * @param initialKilometer
	 * @param finalKilometer
	 * @param sense
	 * @param name
	 * @param longitude
	 * @param latitude
	 */
	public IncidenceBean(String communityName, String provinceName,
			String causeTypeName, String causeSubtypeName, String townName,
			Date startDatetime, Date endDatetime, String levelColour,
			String levelFlow, String mountainPortLevel, String roadCode,
			Float initialKilometer, Float finalKilometer, String sense,
			String name, BigDecimal longitude, BigDecimal latitude) {
		super();
		this.communityName = communityName;
		this.provinceName = provinceName;
		this.causeTypeName = causeTypeName;
		this.causeSubtypeName = causeSubtypeName;
		this.townName = townName;
		this.startDatetime = startDatetime;
		this.endDatetime = endDatetime;
		this.levelColour = levelColour;
		this.levelFlow = levelFlow;
		this.mountainPortLevel = mountainPortLevel;
		this.roadCode = roadCode;
		this.initialKilometer = initialKilometer;
		this.finalKilometer = finalKilometer;
		this.sense = sense;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		// set marker-icon
		if (null == causeTypeName) {
			this.markerIcon = "stop.png";
		}
		else {
			switch (causeTypeName) {
			case Constants.TYPE_INC_METEOROLOGY:
				this.markerIcon = "thunderstorm.png";
				break;
			case Constants.TYPE_INC_ACCIDENT:
				this.markerIcon = "caraccident.png";
				break;
			case Constants.TYPE_INC_RETENTION:
				this.markerIcon = "caution.png";
				break;
			case Constants.TYPE_INC_ROAD_SAFETY:
				this.markerIcon = "icyroad.png";
				break;
			case Constants.TYPE_INC_MOUNTAIN_PORT:
				this.markerIcon = "mountains.png";
				break;
			case Constants.TYPE_INC_WINTER_ROAD_SECTIONS:
				this.markerIcon = "snowy.png";
				break;
			case Constants.TYPE_INC_WORK:
				this.markerIcon = "construction.png";
				break;
			case Constants.TYPE_INC_WORKS:
				this.markerIcon = "construction.png";
				break;
			case Constants.TYPE_INC_OTHERS:
				this.markerIcon = "closedroad.png";
				break;
			case Constants.TYPE_INC_SPORT_EVENTS:
				this.markerIcon = "cycling.png";
				break;
			}
		}
	}


	/**
	 * Gets html div with incidence info.
	 */
	public String getDivInfo() {
		StringBuilder div = new StringBuilder();
		final String NONE = "- ";
		div.append("<div id=\"content\">");
		div.append(" Provincia: ");
		if (provinceName == null) {
			div.append(NONE);
		}
		else {
			div.append(provinceName);
		}
		div.append(", Poblaci&oacute;n: ");
		if (townName == null) {
			div.append(NONE);
		}
		else {
			div.append(townName);
		}
		div.append("<br>");
		div.append(" Tipo: ");
		if (causeTypeName == null) {
			div.append(NONE);
		}
		else {
			div.append(causeTypeName);			
		}
		div.append(", Causa: ");
		if (causeSubtypeName == null) {
			div.append(NONE);
		}
		else {
			div.append(causeSubtypeName);
		}
		div.append("<br>");
		div.append(" Inicio: ");
		if (startDatetime == null) {
			div.append(NONE);
		}
		else {
			div.append(Utils.getStringDateTime(startDatetime));
		}
		div.append(", Fin: ");
		if (endDatetime == null) {
			div.append(NONE);
		}
		else {
			div.append(Utils.getStringDateTime(endDatetime));
		}
		div.append(", Nivel: ");
		if (levelColour == null) {
			div.append(NONE);
		}
		else {
			div.append(levelColour);
			if (levelFlow != null) {
				div.append(" (" + levelFlow + ")");
			}
		}
		div.append("<br>");
		div.append(" Puerto: ");
		if (mountainPortLevel == null) {
			div.append(NONE);
		}
		else {
			div.append(mountainPortLevel);
		}
		div.append(", Carretera: ");
		if (roadCode == null) {
			div.append(NONE);
		}
		else {
			div.append(roadCode);
		}
		div.append("<br>");
		div.append(" Km Inicial: ");
		if (initialKilometer == null) {
			div.append(NONE);
		}
		else {
			div.append(initialKilometer);
		}
		div.append(", Km Final: ");
		if (finalKilometer == null) {
			div.append(NONE);
		}
		else {
			div.append(finalKilometer);
		}
		div.append("<br>");
		div.append(" Sentido: ");
		if (sense == null) {
			div.append(NONE);
		}
		else {
			div.append(sense);
		}
		div.append(", Nombre: ");
		if (name == null) {
			div.append(NONE);
		}
		else {
			div.append(name);
		}
		div.append("<br>");
		div.append(" Latitud: ");
		div.append(Utils.getLatLonFormat(latitude));
		div.append(", Longitud: ");
		div.append(Utils.getLatLonFormat(longitude));
		div.append("</div>");
		return div.toString();
	}
	

	
	

}
