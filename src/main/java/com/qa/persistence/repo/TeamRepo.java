package com.qa.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.domain.Team;

@Repository
public interface TeamRepo extends  JpaRepository<Team, Long> {

}
