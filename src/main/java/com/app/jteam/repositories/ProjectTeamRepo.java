package com.app.jteam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.app.jteam.controllers.TeamObject;
import com.app.jteam.entities.Project;
import com.app.jteam.entities.ProjectTeamMembers;
import com.app.jteam.entities.User;


public interface ProjectTeamRepo extends JpaRepository<ProjectTeamMembers, Integer> {
	@Query(value="SELECT p FROM ProjectTeamMembers p WHERE p.pid=:pid")
	public List<ProjectTeamMembers> findMembers(@Param("pid") int pid);
	
	@Transactional
	@Modifying
	@Query(value="SELECT t FROM ProjectTeamMembers t WHERE t.pid=:pid AND t.uid=:uid")
	public List<ProjectTeamMembers> deleteProjectMember(@Param("pid") int pid, @Param("uid") long uid);
	
	 @Query(value="SELECT new com.app.jteam.controllers.TeamObject(p.pid, COUNT(p.uid)) FROM ProjectTeamMembers p GROUP BY p.pid")
	 public List<TeamObject> stats();  
	 
	 @Query(value="SELECT COUNT(*) FROM ProjectTeamMembers WHERE pid=:pid")
	 public long NumberOfMembers(@Param("pid") int pid);
	 
	 /*//select project.project_name,count(user_id) from project_team_members inner join project on project_team_members.project_id = project.id group by project_team_members.project_id;
	 @Query(value="")
	 public List<TeamObject> projectNameMembers();*/
	 
	 @Query(value="SELECT p FROM ProjectTeamMembers p WHERE p.uid=:uid")
	 public <Optional> List<ProjectTeamMembers> findProjects(@Param("uid") long uid);
}
