package com.app.jteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.jteam.entities.User;

public interface DataRepository extends JpaRepository<User,Long>{

}
