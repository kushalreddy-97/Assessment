package com.allstate_assessment;

import com.allstate_assessment.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Person,Long> {
}
