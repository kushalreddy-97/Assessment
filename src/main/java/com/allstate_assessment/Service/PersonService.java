package com.allstate_assessment.Service;

import com.allstate_assessment.Entity.Person;

import java.util.List;

public interface PersonService {

    Person addPerson(Person person);

    Person updatePerson(long personId, Person person);

    Person getPerson(long personId);

    List<Person> getAllPersons();

    void deletePerson(long personId);
}
