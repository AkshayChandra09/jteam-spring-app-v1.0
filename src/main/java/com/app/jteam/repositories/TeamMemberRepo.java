package com.app.jteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.jteam.entities.AssignedTeamMember;

public interface TeamMemberRepo extends JpaRepository<AssignedTeamMember,Long>{

}
