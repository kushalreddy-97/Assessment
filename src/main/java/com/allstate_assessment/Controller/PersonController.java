package com.allstate_assessment.Controller;

import com.allstate_assessment.Entity.Person;
import com.allstate_assessment.Service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("phonebook")
public class PersonController {

    @Autowired
    PersonService personService;

    /**
     * @return List<Person>
     * Get method to get all the persons from the database
     * returns list
     * If none in the data base returns empty list
     **/
    @GetMapping("/getAllPersons")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    /**
     * @param personId
     * @return Person
     * Get method to return one person data
     * Get method accepts id as the argument and returns the data of that particular id
     * If id is not present it gives error Record not found
     **/

    @GetMapping("/{personId}")
    public Person getPerson(@PathVariable long personId) {
        return personService.getPerson(personId);
    }

    /**
     * @param Person
     * @return Person object
     * Post method accepts the Person object and returns the person object
     * firstName is not a mandatory feils and no constraints on it
     * lastName is a mandatory feild
     * phone number is a mandatory feild it accepts only numbers and the size is between 11 to 13
     * mobile number only accepts numbers
     * AddressLine1 is a mandatory feild
     * AddressLine 2 is not a mandatory feild its optional
     * country and postcode is mandatory
     * postcode accepts Alpha Numeric characters
     * Country accepts only Characters and wont accept numbers

     **/

    @PostMapping("/addPerson")
    @ResponseStatus(HttpStatus.CREATED)
    public Person addPerson(@RequestBody @Valid Person Person) {
        return personService.addPerson(Person);
    }

    /**
     * @param personId
     * @return Person
     * Update method accepts the id
     * if the id is available it will update the record else it will give error
     **/

    @PutMapping("/updatePerson/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public Person updatePerson(@PathVariable long personId, @Valid @RequestBody Person Person) {
        return personService.updatePerson(personId, Person);
    }

    /**
     * @param personId Delete method accepts the Id and deletes the record
     *                 if that record is available in the database
     *                 Else will throw an error
     */
    @DeleteMapping("/deletePerson/{personId}")
    public void deletePerson(@PathVariable long personId) {
        personService.deletePerson(personId);
    }
}
