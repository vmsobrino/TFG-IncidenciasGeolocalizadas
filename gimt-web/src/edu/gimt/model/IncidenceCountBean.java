package edu.gimt.model;

import java.lang.reflect.Field;
import java.math.BigInteger;

import edu.gimt.common.Constants;


public class IncidenceCountBean {
	
	private Integer yearOfIncidence = null;
	private Integer jdId = null;
	private String judicialDistrict = null;
	private BigInteger hits = null;
	private BigInteger runover = null;
	private BigInteger wayouts = null;
	private BigInteger scissorTracks = null;
	private BigInteger rollovers = null;
	private BigInteger winterAccidents = null;
	private BigInteger springAccidents = null;
	private BigInteger summerAccidents = null;
	private BigInteger autumnAccidents = null;
	private BigInteger greenWhiteLevel = null;
	private BigInteger yellowLevel = null;
	private BigInteger redLevel = null;
	private BigInteger blackLevel = null;
	private BigInteger hour0006 = null;
	private BigInteger hour0612 = null;
	private BigInteger hour1218 = null;
	private BigInteger hour1800 = null;
	
	public IncidenceCountBean(Integer yearOfIncidence, Integer jdId,
			String judicialDistrict, BigInteger hits, BigInteger runover,
			BigInteger wayouts, BigInteger scissorTracks, BigInteger rollovers,
			BigInteger winterAccidents, BigInteger springAccidents,
			BigInteger summerAccidents, BigInteger autumnAccidents,
			BigInteger greenWhiteLevel, BigInteger yellowLevel, BigInteger redLevel,
			BigInteger blackLevel, BigInteger hour0006, BigInteger hour0612,
			BigInteger hour1218, BigInteger hour1800) {
		super();
		this.yearOfIncidence = yearOfIncidence;
		this.jdId = jdId;
		this.judicialDistrict = judicialDistrict;
		this.hits = hits;
		this.runover = runover;
		this.wayouts = wayouts;
		this.scissorTracks = scissorTracks;
		this.rollovers = rollovers;
		this.winterAccidents = winterAccidents;
		this.springAccidents = springAccidents;
		this.summerAccidents = summerAccidents;
		this.autumnAccidents = autumnAccidents;
		this.greenWhiteLevel = greenWhiteLevel;
		this.yellowLevel = yellowLevel;
		this.redLevel = redLevel;
		this.blackLevel = blackLevel;
		this.hour0006 = hour0006;
		this.hour0612 = hour0612;
		this.hour1218 = hour1218;
		this.hour1800 = hour1800;
	}

	public Integer getYearOfIncidence() {
		return yearOfIncidence;
	}

	public void setYearOfIncidence(Integer yearOfIncidence) {
		this.yearOfIncidence = yearOfIncidence;
	}

	public Integer getJdId() {
		return jdId;
	}

	public void setJdId(Integer jdId) {
		this.jdId = jdId;
	}

	public String getJudicialDistrict() {
		return judicialDistrict;
	}

	public void setJudicialDistrict(String judicialDistrict) {
		this.judicialDistrict = judicialDistrict;
	}

	public BigInteger getHits() {
		return hits;
	}

	public void setHits(BigInteger hits) {
		this.hits = hits;
	}

	public BigInteger getRunover() {
		return runover;
	}

	public void setRunover(BigInteger runover) {
		this.runover = runover;
	}

	public BigInteger getWayouts() {
		return wayouts;
	}

	public void setWayouts(BigInteger wayouts) {
		this.wayouts = wayouts;
	}

	public BigInteger getScissorTracks() {
		return scissorTracks;
	}

	public void setScissorTracks(BigInteger scissorTracks) {
		this.scissorTracks = scissorTracks;
	}

	public BigInteger getRollovers() {
		return rollovers;
	}

	public void setRollovers(BigInteger rollovers) {
		this.rollovers = rollovers;
	}

	public BigInteger getWinterAccidents() {
		return winterAccidents;
	}

	public void setWinterAccidents(BigInteger winterAccidents) {
		this.winterAccidents = winterAccidents;
	}

	public BigInteger getSpringAccidents() {
		return springAccidents;
	}

	public void setSpringAccidents(BigInteger springAccidents) {
		this.springAccidents = springAccidents;
	}

	public BigInteger getSummerAccidents() {
		return summerAccidents;
	}

	public void setSummerAccidents(BigInteger summerAccidents) {
		this.summerAccidents = summerAccidents;
	}

	public BigInteger getAutumnAccidents() {
		return autumnAccidents;
	}

	public void setAutumnAccidents(BigInteger autumnAccidents) {
		this.autumnAccidents = autumnAccidents;
	}

	public BigInteger getGreenWhiteLevel() {
		return greenWhiteLevel;
	}

	public void setGreenWhiteLevel(BigInteger greenWhiteLevel) {
		this.greenWhiteLevel = greenWhiteLevel;
	}

	public BigInteger getYellowLevel() {
		return yellowLevel;
	}

	public void setYellowLevel(BigInteger yellowLevel) {
		this.yellowLevel = yellowLevel;
	}

	public BigInteger getRedLevel() {
		return redLevel;
	}

	public void setRedLevel(BigInteger redLevel) {
		this.redLevel = redLevel;
	}

	public BigInteger getBlackLevel() {
		return blackLevel;
	}

	public void setBlackLevel(BigInteger blackLevel) {
		this.blackLevel = blackLevel;
	}

	public BigInteger getHour0006() {
		return hour0006;
	}

	public void setHour0006(BigInteger hour0006) {
		this.hour0006 = hour0006;
	}

	public BigInteger getHour0612() {
		return hour0612;
	}

	public void setHour0612(BigInteger hour0612) {
		this.hour0612 = hour0612;
	}

	public BigInteger getHour1218() {
		return hour1218;
	}

	public void setHour1218(BigInteger hour1218) {
		this.hour1218 = hour1218;
	}

	public BigInteger getHour1800() {
		return hour1800;
	}

	public void setHour1800(BigInteger hour1800) {
		this.hour1800 = hour1800;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
						yearOfIncidence, jdId, judicialDistrict, hits, runover,
						wayouts, scissorTracks, rollovers, winterAccidents,
						springAccidents, summerAccidents, autumnAccidents,
						greenWhiteLevel, yellowLevel, redLevel, blackLevel,
						hour0006, hour0612, hour1218, hour1800);
	}

	public String getHeader() {
		StringBuffer sb = new StringBuffer();
		Field field = null;
        for (int i = 0; i < this.getClass().getDeclaredFields().length; i++) {
        	field = this.getClass().getDeclaredFields()[i];
        	sb.append(field.getName());
            if (i + 1 < this.getClass().getDeclaredFields().length) {
            	sb.append(",");
            }
        }
        sb.append(Constants.LINE_SEPARATOR);
		return sb.toString();
	}

}
