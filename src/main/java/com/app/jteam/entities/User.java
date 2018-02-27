package com.app.jteam.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String user_name;
	private String password;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	
	public User(String user_name, String password) {
		this.user_name = user_name;
		this.password = password;
	}


	/*@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + user_name + ", lname=" + password + "]";
	}*/
	public User() {
		
	}
	
}
