package edu.gimt.model;

public class UserBean {
	 
	private String userName;
	private String password;
	private String fullName;
 
	public UserBean(String userName, String password, String fullName){
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
	}
 
	public String getFullName() {
		return fullName;
	}
 
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
 
	public String getUsername() {
		return userName;
	}
 
	public void setUserName(String username) {
		this.userName = username;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
}
