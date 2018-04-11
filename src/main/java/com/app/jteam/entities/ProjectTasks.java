package com.app.jteam.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProjectTasks {
	
	@Id
	@GeneratedValue
	private long proj_task_id;
	
	private int project_id;
	
	private long task_id;

	public long getProj_task_id() {
		return proj_task_id;
	}

	public void setProj_task_id(long proj_task_id) {
		this.proj_task_id = proj_task_id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public long getTask_id() {
		return task_id;
	}

	public void setTask_id(long task_id) {
		this.task_id = task_id;
	}

	public ProjectTasks(int project_id, long task_id) {
		this.project_id = project_id;
		this.task_id = task_id;
	}

	public ProjectTasks() {
	}
	
}
