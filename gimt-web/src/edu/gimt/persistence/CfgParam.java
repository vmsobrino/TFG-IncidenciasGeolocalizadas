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
 * Configuration Parameter Entity class implementation.
 * @author Victor M. Sobrino - TFG
 */
@Entity
@Table(name = "gimt_cfg_param")
@NamedQueries({@NamedQuery(name = "CfgParam.findByModule", query = "SELECT cp FROM CfgParam cp WHERE cp.codModule = :codModule"),
			   @NamedQuery(name = "CfgParam.findByModuleAndParam", query = "SELECT cp FROM CfgParam cp WHERE cp.codModule = :codModule AND cp.codParam = :codParam")})
public class CfgParam {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_cfg_param")
	private Integer idCfgParam;
	
	@Column(name = "cod_module")
	private String codModule;
	
	@Column(name = "cod_param")
	private String codParam;
	
	@Column(name = "param_value")
	private String paramValue;
	
	/**
	 * Default constructor.
	 */
	public CfgParam() {
	}
	
	/**
	 * Constructor with all columns.
	 */
	public CfgParam(String codModule, String codParam, String paramValue) {
		this.codModule = codModule;
		this.codParam = codParam;
		this.paramValue = paramValue;
	}

	/**
	 * @return the idCfgParam
	 */
	public Integer getIdCfgParam() {
		return idCfgParam;
	}		
	
	/**
	 * @return the codModule
	 */
	public String getCodModule() {
		return codModule;
	}

	/**
	 * @param codModule the codModule to set
	 */
	public void setCodModule(String codModule) {
		this.codModule = codModule;
	}

	/**
	 * @return the codParam
	 */
	public String getCodParam() {
		return codParam;
	}

	/**
	 * @param codParam the codParam to set
	 */
	public void setCodParam(String codParam) {
		this.codParam = codParam;
	}

	/**
	 * @return the paramValue
	 */
	public String getParamValue() {
		return paramValue;
	}

	/**
	 * @param paramValue the paramValue to set
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}
