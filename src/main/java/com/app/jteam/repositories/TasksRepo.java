package com.app.jteam.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.jteam.entities.Task;


public interface TasksRepo extends JpaRepository<Task,Long>{
	@Query(value="SELECT t FROM Task t WHERE t.task_name=:task_name")
	public Task findByName(@Param("task_name") String task_name);
	
	//make inner join work anyhow before hosting
	//@Query("SELECT  t FROM  Task t INNER JOIN Project p ON p.id = t.id WHERE p.id = 1")
	//List<Task> findByProject();
	
}