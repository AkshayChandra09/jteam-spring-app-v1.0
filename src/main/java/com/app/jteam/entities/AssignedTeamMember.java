package com.app.jteam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;



@Entity
public class AssignedTeamMember {
	
	@Id
	@GeneratedValue
	@Column(name="member_task_id")
	private long member_task_id;
	@Column(nullable=false)
	private long task_id;
	@Column(name="team_member")
	private long team_member;
	
	
	public long getMember_task_id() {
		return member_task_id;
	}
	public void setMember_task_id(long member_task_id) {
		this.member_task_id = member_task_id;
	}
	public long getTask_id() {
		return task_id;
	}
	public void setTask_id(long task_id) {
		this.task_id = task_id;
	}
	public long getTeam_member() {
		return team_member;
	}
	public void setTeam_member(long team_member) {
		this.team_member = team_member;
	}
	public AssignedTeamMember(long task_id, long team_member) {
		this.task_id = task_id;
		this.team_member = team_member;
	}
	
	public AssignedTeamMember(){}
	
	public void setParameters(long t, Long u){
		this.task_id = t;
		this.team_member = u;
	}
	
}
