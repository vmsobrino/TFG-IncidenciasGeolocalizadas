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
 * Validator Entity class implementation.
 * @author Victor M. Sobrino - TFG
 */
@Entity
@Table(name = "gimt_validator")
@NamedQueries({@NamedQuery(name="Validator.findAll", query = "SELECT v FROM Validator v ORDER BY v.idValidator"),
			   @NamedQuery(name="Validator.findByService", query = "SELECT v FROM Validator v WHERE v.service = :service ORDER BY v.idValidator")})
public class Validator {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_validator")
	private Integer idValidator;
	
	@Column(name = "service")
	private String service;
	
	@Column(name = "validator")
	private String validator;
	
	@Column(name = "params")
	private String params;
	
	/**
	 * Default constructor.
	 */
	public Validator() {
	}

	/**
	 * @return the service
	 */
	public String getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(String service) {
		this.service = service;
	}

	/**
	 * @return the validator
	 */
	public String getValidator() {
		return validator;
	}

	/**
	 * @param validator the validator to set
	 */
	public void setValidator(String validator) {
		this.validator = validator;
	}

	/**
	 * @return the params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * @return the idValidator
	 */
	public Integer getIdValidator() {
		return idValidator;
	}
	

}
