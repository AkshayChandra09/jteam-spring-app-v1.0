package com.app.jteam.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.jteam.entities.Task;


public interface TasksRepo extends JpaRepository<Task,Long>{
	@Query(value="SELECT t FROM Task t WHERE t.task_name=:task_name")
	public Task findByName(@Param("task_name") String task_name);
}