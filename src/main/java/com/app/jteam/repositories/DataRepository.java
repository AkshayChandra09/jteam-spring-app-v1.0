package com.app.jteam.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.jteam.entities.AssignedTeamMember;
import com.app.jteam.entities.User;


public interface DataRepository extends JpaRepository<User,Long>{
	@Query(value="SELECT u FROM User u WHERE u.id=:id")
	public User findName(@Param("id") Long id);
	
	@Query(value="SELECT u FROM User u WHERE u.user_name=:user_name")
	public User findByUserName(@Param("user_name") String user_name);

}

