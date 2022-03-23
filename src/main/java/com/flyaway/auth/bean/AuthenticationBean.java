package com.flyaway.auth.bean;

public class AuthenticationBean {
	
	private int userId;
	private boolean admin;
	private String name;
	private String email;
	private String password;
	private String gender;
	
	public AuthenticationBean() {
		
	}
	public AuthenticationBean(boolean admin, String name, String email, String password, String gender) {
		super();
		this.admin = admin;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

}
