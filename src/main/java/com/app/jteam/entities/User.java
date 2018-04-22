package com.app.jteam.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.jteam.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="User")
@Scope("session")
public class User implements UserDetails {
	
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String user_name;
	private String password;
	private String email_id;
	private String role;
	
	
	/******************************************/
	/*@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Role> roles;
	public User(String username, String password, List<Role> roles) {
		this.user_name = username;
	    this.password = password;
	    this.roles = roles;
	}*/
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
	
	/*public List<Role> getRoles() {
	        return roles;
	}

	 public void setRoles(List<Role> roles) {
	        this.roles = roles;
	 }*/
	 
		@JsonIgnore
		@Override
		public boolean isEnabled() {
			return true;
		}

		@JsonIgnore
		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@JsonIgnore
		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@JsonIgnore
		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@JsonIgnore
		public Collection<? extends GrantedAuthority> getAuthorities() {
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(role));
			return authorities;
		}
		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return user_name;
		}
		

}
