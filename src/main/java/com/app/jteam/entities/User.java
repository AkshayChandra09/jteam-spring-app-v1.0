package com.app.jteam.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String user_name;
	private String password;
	
	@ManyToOne
	private AssignedTeamMember team_member; 
	
	private ArrayList<Long> tasks = new ArrayList<Long>();
	
	public void assignTask(Long task_id) {
	   // this.team_member = task_mem;
	    tasks.add(task_id);
	}
	  
	public void setTaskTeamMem(AssignedTeamMember team_member){
		this.team_member = team_member;
	}
	
	public AssignedTeamMember getTeskTeamMem(){
		return team_member;
	}

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
