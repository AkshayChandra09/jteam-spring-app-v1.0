package com.app.jteam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.app.jteam.entities.AssignedTeamMember;
import com.app.jteam.entities.ProjectTeamMembers;
import com.app.jteam.entities.User;

public interface ProjectTeamRepo extends JpaRepository<ProjectTeamMembers, Integer> {
	@Query(value="SELECT p FROM ProjectTeamMembers p WHERE p.pid=:pid")
	public List<ProjectTeamMembers> findMembers(@Param("pid") int pid);
	
	@Transactional
	@Modifying
	@Query(value="SELECT t FROM ProjectTeamMembers t WHERE t.pid=:pid AND t.uid=:uid")
	public List<ProjectTeamMembers> deleteProjectMember(@Param("pid") int pid, @Param("uid") int uid);

}
