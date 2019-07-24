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
@Table(name = "gimt_user")
@NamedQueries({@NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser"),
			   @NamedQuery(name = "User.findByNameAndPassword", query = "SELECT u FROM User u WHERE u.userName = :userName AND u.password = :password AND u.enabled=1")})
public class User {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Integer idUser;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "enabled")
	private Integer enabled;
	
	/**
	 * Default constructor.
	 */
	public User() {
	}
	
	/**
	 * Constructor with all columns.
	 */
	public User(String userName, String password, String fullName, Integer enabled) {
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.enabled = enabled;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return userName;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.userName = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the idUser
	 */
	public Integer getIdUser() {
		return idUser;
	}



}
