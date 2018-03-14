package com.app.jteam.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.jteam.entities.Task;

public interface TasksRepo extends JpaRepository<Task,Long>{
	
}