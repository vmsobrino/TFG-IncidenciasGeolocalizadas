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
 * Incidence-Cause-SubType Entity class implementation.
 * @author Victor M. Sobrino - TFG
 */
@Entity
@Table(name = "gimt_incidence_cause_subtype")
@NamedQueries({@NamedQuery(name = "IncidenceCauseSubtype.findAll", query = "SELECT icst FROM IncidenceCauseSubtype icst"),
			   @NamedQuery(name = "IncidenceCauseSubtype.findByType", query = "SELECT icst FROM IncidenceCauseSubtype icst WHERE icst.idStCauseType = :idStCauseType")})
public class IncidenceCauseSubtype {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_cause_subtype")
	private Integer idCauseSubtype;

	@Column(name = "cause_subtype_name")
	private String causeSubtypeName;

	@Column(name = "id_st_cause_type")
	private Integer idStCauseType;

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
	 * @return the idStCauseType
	 */
	public Integer getIdStCauseType() {
		return idStCauseType;
	}

	/**
	 * @param idStCauseType the idStCauseType to set
	 */
	public void setIdStCauseType(Integer idStCauseType) {
		this.idStCauseType = idStCauseType;
	}

	/**
	 * @return the idCauseSubtype
	 */
	public Integer getIdCauseSubtype() {
		return idCauseSubtype;
	}

}
