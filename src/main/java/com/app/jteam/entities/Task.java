package com.app.jteam.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date; 

@Entity
public class Task {
	@Id
	@GeneratedValue
	@Column(nullable=false)
	private Long id;
	
	@Column(nullable=false)
	private String task_name;
	
	@Column(nullable=false)
	private Date task_assigned_date;

	@Column(nullable=false)
	private Date task_deadline; 
	
	private String task_status;
	
	private String task_priority;
	
	
	//Date date = new Date();
    //SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");  
    //String strDate = formatter.format(date);   
	
	public Task(){
		/*task_name = "XXX";
		task_assigned_date = null;
		task_deadline = null;
		task_status="incomplete";
		task_priority="low";*/
	}
	
	public Task(Long id, String task_name, Date task_assigned_date, Date task_deadline, String task_status,
			String task_priority) {
		this.id = id;
		this.task_name = task_name;
		this.task_assigned_date = task_assigned_date;
		this.task_deadline = task_deadline;
		this.task_status = task_status;
		this.task_priority = task_priority;
	}


	public String getTask_name() {
		return task_name;
	}
	
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	
	public Date getTask_deadline() {
		return task_deadline;
	}
	
	public void setTask_deadline(Date task_deadline) {
		this.task_deadline = task_deadline;
	}
	
	public String getTask_status() {
		return task_status;
	}
	
	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}
	
	public String getTask_priority() {
		return task_priority;
	}
	
	public void setTask_priority(String task_priority) {
		this.task_priority = task_priority;
	}
	
	public Date getTask_assigned_date() {
		return task_assigned_date;
	}

	public void setTask_assigned_date(Date task_assigned_date) {
		this.task_assigned_date = task_assigned_date;
	}

	public Long getId() {
		return id;
	}
	
	
	/*@Override
	public String toString() {
		return "Task [id=" + id + ", task_name=" + task_name + ", task_deadline=" + task_deadline + ", task_status="
				+ task_status + ", task_priority=" + task_priority + "]";
	}*/
}
