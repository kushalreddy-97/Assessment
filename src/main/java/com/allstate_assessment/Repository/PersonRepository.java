package com.allstate_assessment.Repository;

import com.allstate_assessment.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByLastName(String personLastName);

    Person findByPhoneNumber(String phoneNumber);
}
