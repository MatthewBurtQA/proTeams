package com.qa.persistence.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.domain.Programmer;

@Repository
public interface ProgrammerRepo extends JpaRepository<Programmer, Long> {


}
