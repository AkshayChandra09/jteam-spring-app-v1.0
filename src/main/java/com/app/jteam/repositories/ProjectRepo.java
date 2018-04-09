package com.app.jteam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.jteam.entities.Project;
import com.app.jteam.entities.ProjectTeamMembers;
import com.app.jteam.entities.User;

public interface ProjectRepo extends JpaRepository<Project, Integer> {
	@Query(value="SELECT p FROM Project p WHERE p.id=:id")
	public Project findProject(@Param("id") int id);
}
