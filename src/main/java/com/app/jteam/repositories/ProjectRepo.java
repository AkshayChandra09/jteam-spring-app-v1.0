package com.app.jteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.jteam.entities.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {

}
