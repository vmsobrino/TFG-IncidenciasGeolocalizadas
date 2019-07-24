package edu.gimt.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * Town Entity class implementation.
 * @author Victor M. Sobrino - TFG
 */
@Entity
@Table(name = "gimt_town")
@NamedQueries({@NamedQuery(name = "Town.findAll", query = "SELECT t FROM Town t"),
			   @NamedQuery(name = "Town.findById", query = "SELECT t FROM Town t WHERE t.idTown = :idTown")})

public class Town {
	@Id
	@Column(name = "id_town")
	private Integer idTown;

	@Column(name = "id_t_province")
	private Integer idProvince;

	@Column(name = "town_name")
	private String townName;

	@Column(name = "id_t_jd")
	private Integer idJudicialDistrict;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idTown == null) ? 0 : idTown.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Town [idTown=" + idTown + ", idProvince="
				+ idProvince + ", townName=" + townName
				+ ", idJudicialDistrict=" + idJudicialDistrict + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Town)) {
			return false;
		}
		Town other = (Town) obj;
		if (idTown == null) {
			if (other.idTown != null) {
				return false;
			}
		} else if (!idTown.equals(other.idTown)) {
			return false;
		}
		return true;
	}

	public Integer getIdTown() {
		return idTown;
	}

	public void setIdTown(Integer idTown) {
		this.idTown = idTown;
	}

	public Integer getIdProvince() {
		return idProvince;
	}

	public void setIdProvince(Integer idProvince) {
		this.idProvince = idProvince;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public Integer getIdJudicialDistrict() {
		return idJudicialDistrict;
	}

	public void setIdJudicialDistrict(Integer idJudicialDistrict) {
		this.idJudicialDistrict = idJudicialDistrict;
	}

}
