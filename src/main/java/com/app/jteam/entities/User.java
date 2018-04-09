package com.app.jteam.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.app.jteam.entities.Role;


@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String user_name;
	private String password;
	private String email_id;
	private String role;
	
	/******************************************/
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Role> roles;
	public User(String username, String password, List<Role> roles) {
		this.user_name = username;
	    this.password = password;
	    this.roles = roles;
	}
	/******************************************/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public User() {

	}
	
	  public List<Role> getRoles() {
	        return roles;
	    }

	    public void setRoles(List<Role> roles) {
	        this.roles = roles;
	    }

}
