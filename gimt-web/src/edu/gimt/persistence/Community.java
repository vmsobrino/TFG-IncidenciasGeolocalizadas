package edu.gimt.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Community Entity class implementation.
 * @author Victor M. Sobrino - TFG
 */
@Entity
@Table(name = "gimt_community")
@NamedQueries({@NamedQuery(name = "Community.findAll", query = "SELECT c FROM Community c WHERE c.enabled = 1"),
			   @NamedQuery(name = "Community.findByName", query = "SELECT c FROM Community c WHERE upper(c.communityName) = upper(:communityName)")})
public class Community {
	
	@Id
	@Column(name = "id_community")
	private Integer idCommunity;
	
	@Column(name = "community_name")
	private String communityName;
	
	@Column(name = "enabled")
	private Integer enabled;
	
	@Column(name = "nuts2")
	private String nuts2;
	
	
	/**
	 * Default constructor.
	 */
	public Community() {
	}
	
	/**
	 * Constructor with all columns.
	 */
	public Community(Integer idCommunity, String communityName, Integer enabled) {
		this.idCommunity = idCommunity;
		this.communityName = communityName;
		this.enabled = enabled;
	}
	
	/**
	 * @return the idCommunity
	 */
	public Integer getIdCommunity() {
		return idCommunity;
	}
	
	/**
	 * @param idCommunity the idCommunity to set
	 */
	public Community setIdCommunity(Integer idCommunity) {
		this.idCommunity = idCommunity;
		return this;
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
	public Community setCommunityName(String communityName) {
		this.communityName = communityName;
		return this;
	}
	
	/**
	 * @return the enabled
	 */
	public Integer getEnabled() {
		return enabled;
	}
	
	/**
	 * @param enabled the enabled to set
	 */
	public Community setEnabled(Integer enabled) {
		this.enabled = enabled;
		return this;
	}

	public String getNuts2() {
		return nuts2;
	}

	public void setNuts2(String nuts2) {
		this.nuts2 = nuts2;
	}
	
	
}
