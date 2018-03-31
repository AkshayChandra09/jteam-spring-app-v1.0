package com.app.jteam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.app.jteam.entities.Project;
import com.app.jteam.entities.User;

@Entity
public class ProjectTeamMembers {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="project_id")
	private int pid;
	
	@Column(name="user_id")
	private long uid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}
	
	public void setParameters(int pid, long uid){
		this.pid = pid;
		this.uid = uid;
	}

}
