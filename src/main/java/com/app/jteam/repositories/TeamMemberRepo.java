package com.app.jteam.repositories;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.app.jteam.entities.AssignedTeamMember;
import com.app.jteam.entities.User;


public interface TeamMemberRepo extends JpaRepository<AssignedTeamMember, Long>{

	@Query(value="SELECT t FROM AssignedTeamMember t WHERE t.task_id=:task_id")
	public List<AssignedTeamMember> findTaskIds(@Param("task_id") long task_id);
	//@Query(value="SELECT u.user_name FROM User u INNER JOIN AssignedTeamMember ON AssignedTeamMember.team_member = User.id WHERE AssignedTeamMember.task_id=:task_id")
	//public List<String> findTaskIds(@Param("task_id") Long task_id);
	@Transactional
	@Modifying
	@Query(value="SELECT t FROM AssignedTeamMember t WHERE t.task_id=:task_id AND t.team_member=:team_member")
	public List<AssignedTeamMember> deleteMember(@Param("task_id") long task_id, @Param("team_member") long team_member);
	
}
