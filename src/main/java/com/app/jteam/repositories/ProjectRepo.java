package com.app.jteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.jteam.entities.Project;
import com.app.jteam.entities.User;

public interface ProjectRepo extends JpaRepository<Project, Long> {

}
