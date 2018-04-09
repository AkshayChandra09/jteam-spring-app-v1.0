package com.app.jteam.controllers;

import com.app.jteam.entities.User;

public class TeamObject {
	private int project_id;
	private long numberOfMembers;
	private User[] members;
	private String project_name;
	
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public User[] getMembers() {
		return members;
	}
	public void setMembers(User[] members) {
		this.members = members;
	}
	public long getNumberOfMembers() {
		return numberOfMembers;
	}
	public void setNumberOfMembers(long numberOfMembers) {
		this.numberOfMembers = numberOfMembers;
	}
	
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
	public TeamObject(int project_id, long numberOfMembers){
		this.project_id = project_id;
		this.numberOfMembers = numberOfMembers;
	}
	
	public TeamObject(String project_name, long numberOfMembers){
		this.project_name = project_name;
		this.numberOfMembers = numberOfMembers;
	}
	
	public TeamObject(long numberOfMembers){
		this.numberOfMembers = numberOfMembers;
	}
	
}
