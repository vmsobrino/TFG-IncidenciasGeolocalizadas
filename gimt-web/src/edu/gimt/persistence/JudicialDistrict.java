package edu.gimt.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * Judicial District Entity class implementation.
 * @author Victor M. Sobrino - TFG
 */
@Entity
@Table(name = "gimt_judicial_district")
@NamedQueries({@NamedQuery(name = "ToJudicialDistrictwn.findAll", query = "SELECT jd FROM JudicialDistrict jd"),
			   @NamedQuery(name = "JudicialDistrict.findById", query = "SELECT jd FROM JudicialDistrict jd WHERE jd.idJudicialDistrict = :idJudicialDistrict")})

public class JudicialDistrict {
	@Id
	@Column(name = "id_jd")
	private Integer idJudicialDistrict;

	@Column(name = "id_jd_province")
	private Integer idProvince;

	@Column(name = "jd_name")
	private String judicialDistrictName;

	public Integer getIdJudicialDistrict() {
		return idJudicialDistrict;
	}

	public void setIdJudicialDistrict(Integer idJudicialDistrict) {
		this.idJudicialDistrict = idJudicialDistrict;
	}

	public Integer getIdProvince() {
		return idProvince;
	}

	public void setIdProvince(Integer idProvince) {
		this.idProvince = idProvince;
	}

	public String getJudicialDistrictName() {
		return judicialDistrictName;
	}

	public void setJudicialDistrictName(String judicialDistrictName) {
		this.judicialDistrictName = judicialDistrictName;
	}


}
