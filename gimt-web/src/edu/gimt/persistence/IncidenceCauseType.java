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
 * Incidence-Cause-Type Entity class implementation.
 * @author Victor M. Sobrino - TFG
 */
@Entity
@Table(name = "gimt_incidence_cause_type")
@NamedQueries({@NamedQuery(name = "IncidenceCauseType.findAll", query = "SELECT ict FROM IncidenceCauseType ict"),
			   @NamedQuery(name = "IncidenceCauseType.findByName", query = "SELECT ict FROM IncidenceCauseType ict WHERE ict.causeTypeName = :causeTypeName")})
public class IncidenceCauseType {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_cause_type")
	private Integer idCauseType;

	@Column(name = "cause_type_name")
	private String causeTypeName;

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
	 * @return the idCauseType
	 */
	public Integer getIdCauseType() {
		return idCauseType;
	}

	
}
