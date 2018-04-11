package com.app.jteam.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.jteam.entities.Project;
import com.app.jteam.entities.ProjectTasks;

public interface ProjectTasksRepo extends JpaRepository<ProjectTasks, Long> {
	
	@Query(value="SELECT p FROM ProjectTasks p WHERE p.project_id=:project_id")
	public List<ProjectTasks> findProjectTasks(@Param("project_id") int project_id);
	
}
