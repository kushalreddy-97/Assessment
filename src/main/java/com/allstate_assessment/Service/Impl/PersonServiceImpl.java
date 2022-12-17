package com.allstate_assessment.Service.Impl;

import com.allstate_assessment.Entity.Person;
import com.allstate_assessment.Exception.PersonException;
import com.allstate_assessment.Exception.PersonExistsException;
import com.allstate_assessment.Exception.PersonNotFoundException;
import com.allstate_assessment.Repository.PersonRepository;
import com.allstate_assessment.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    /**
     * Adding the person to the database
     * if the person last name already exists then raise the error
     **/
    @Override
    public Person addPerson(Person person) {
        Person savedPerson = null;
        try {

            if (checkDuplicateLastName(person.getLastName()))
                savedPerson = personRepository.save(person);
            else
                // throwing the exception
                throw new PersonExistsException("person with last name already exists", HttpStatus.CONFLICT);
        } catch (PersonException exception) {
            exception.printStackTrace();
        }
        return savedPerson;
    }

    /**
     * @param personId is used as the parameter to the update method
     * @Person object that needs to be updated
     * if there is no record for the id, throwing the exception
     * duplicate check is handled for the last name
     **/
    @Override
    public Person updatePerson(long personId, Person person) {
        Person updatedPerson;
        try {
            updatedPerson = personRepository.findById(personId).orElseThrow(() ->
            {
                throw new PersonNotFoundException("Person Not Found", HttpStatus.NOT_FOUND);
            });
            if (checkDuplicateLastName(person.getLastName())&& (person.getPersonId()!= updatedPerson.getPersonId())) {
                throw new PersonExistsException("person with last name already exists", HttpStatus.CONFLICT);
            }

            updatedPerson.setFirstName(person.getFirstName());
            updatedPerson.setLastName(person.getLastName());
            updatedPerson.setPhoneNumber(person.getPhoneNumber());
            updatedPerson.setPhoneNumber(person.getPhoneNumber());
            updatedPerson.setAddress(person.getAddress());
            personRepository.save(updatedPerson);
        } catch (PersonException exception) {
            exception.printStackTrace();
        }
        return person;
    }

    // method for getting single  person based on the person id
    // person not available then raise exception
    @Override
    public Person getPerson(long personId) {
        Person person = null;
        try {
            person = personRepository.findById(personId).orElseThrow(() -> {
                throw new PersonNotFoundException("Person Not Found", HttpStatus.NOT_FOUND);
            });

        } catch (PersonException exception) {
            exception.printStackTrace();
        }
        return person;
    }

    // method for getting all the persons if none of them then sending the empty list
    @Override
    public List<Person> getAllPersons() {
        List<Person> person = null;

        try {
            person = personRepository.findAll();
            if (person.isEmpty()) {
                person = new ArrayList<>();
            }
        } catch (RuntimeException exception) {
            exception.printStackTrace();
        }
        return person;

    }

    // method for delete the person if the person is not available raise exception
    @Override
    public void deletePerson(long personId) {
        Person person = null;
        try {
            personRepository.findById(personId).orElseThrow(() -> {
                throw new PersonNotFoundException("Person Not Found", HttpStatus.NOT_FOUND);
            });
            personRepository.deleteById(personId);
        } catch (PersonException exception) {
            exception.printStackTrace();
        }
    }

    private boolean checkDuplicateLastName(String lastName) {
        Person existingPerson = personRepository.findByLastName(lastName);
        if (existingPerson== null)
            return true;
        else
            return false;
    }
}
