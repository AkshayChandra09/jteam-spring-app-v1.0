package com.app.jteam.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Project {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(nullable=false)
	private String project_name;
	
	@Column(nullable=false)
	private String project_desc;
	
	private Date project_start_date;
 
	private long project_budget;
	
	private String project_admin;
	
	private String project_vendor_name;
	
	private String Status;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getProject_desc() {
		return project_desc;
	}

	public void setProject_desc(String project_desc) {
		this.project_desc = project_desc;
	}

	public Date getProject_start_date() {
		return project_start_date;
	}

	public void setProject_start_date(Date project_start_date) {
		this.project_start_date = project_start_date;
	}

	public String getProject_vendor_name() {
		return project_vendor_name;
	}

	public void setProject_vendor_name(String project_vendor_name) {
		this.project_vendor_name = project_vendor_name;
	}

	public String getStatus() {
		return Status;
	}

	public long getProject_budget() {
		return project_budget;
	}

	public void setProject_budget(long project_budget) {
		this.project_budget = project_budget;
	}

	public String getProject_admin() {
		return project_admin;
	}

	public void setProject_admin(String project_admin) {
		this.project_admin = project_admin;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Project(String project_name, String project_desc, Date project_start_date, long project_budget,
			String project_admin, String project_vendor_name, String status) {
		this.project_name = project_name;
		this.project_desc = project_desc;
		this.project_start_date = project_start_date;
		this.project_budget = project_budget;
		this.project_admin = project_admin;
		this.project_vendor_name = project_vendor_name;
		Status = status;
	}
	
	public Project() {
	
	}
	
}
