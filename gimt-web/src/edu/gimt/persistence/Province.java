package edu.gimt.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Province Entity class implementation.
 * @author Victor M. Sobrino - TFG
 */
@Entity
@Table(name = "gimt_province")
@NamedQueries({@NamedQuery(name = "Province.findAll", query = "SELECT p FROM Province p WHERE p.enabled = 1"),
			   @NamedQuery(name = "Province.findByName", query = "SELECT p FROM Province p WHERE p.enabled = 1 AND p.provinceName = :provinceName"),
			   @NamedQuery(name = "Province.findById", query = "SELECT p FROM Province p WHERE p.enabled = 1 AND p.idProvince = :idProvince")})
public class Province {

	@Id
	@Column(name = "id_province")
	private Integer idProvince;

	@Column(name = "province_name")
	private String provinceName;

	@Column(name = "province_plate")
	private String provincePlate;

	@Column(name = "enabled")
	private Integer enabled;
	
	@Column(name = "id_cp_community")
	private Integer idCpCommunity;
	
	@Column(name = "nuts3")
	private String nuts3;
	
	/**
	 * @return the enabled
	 */
	public Integer getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the idProvince
	 */
	public Integer getIdProvince() {
		return idProvince;
	}

	/**
	 * @return the provinceName
	 */
	public String getProvinceName() {
		return provinceName;
	}

	/**
	 * @return the provincePlate
	 */
	public String getProvincePlate() {
		return provincePlate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idProvince == null) ? 0 : idProvince.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Province [idProvince=" + idProvince + ", provinceName="
				+ provinceName + ", provincePlate=" + provincePlate
				+ ", enabled=" + enabled + ", nuts3=" + nuts3 + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Province)) {
			return false;
		}
		Province other = (Province) obj;
		if (idProvince == null) {
			if (other.idProvince != null) {
				return false;
			}
		} else if (!idProvince.equals(other.idProvince)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the idCpCommunity
	 */
	public Integer getIdCpCommunity() {
		return idCpCommunity;
	}

	/**
	 * @param idCpCommunity the idCpCommunity to set
	 */
	public void setIdCpCommunity(Integer idCpCommunity) {
		this.idCpCommunity = idCpCommunity;
	}

	public String getNuts3() {
		return nuts3;
	}

	public void setNuts3(String nuts3) {
		this.nuts3 = nuts3;
	}

}
