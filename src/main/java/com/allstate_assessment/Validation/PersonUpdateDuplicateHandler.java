package com.allstate_assessment.Validation;

import com.allstate_assessment.Entity.Person;
import com.allstate_assessment.Exception.DuplicateException;
import com.allstate_assessment.Repository.PersonRepository;
import com.allstate_assessment.Util.IErrorCode;
import com.allstate_assessment.Util.PersonErrorCode;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonUpdateDuplicateHandler implements DuplicateHandler<Person> {

    @Autowired
    PersonRepository personRepository;

    @Override
    public void checkDuplicate(Person person) {
        List<IErrorCode> errorCodeEnumList = new ArrayList<>();
        long personId = person.getPersonId();
        Assert.notNull(personId, "person id cannot be null");
        String phoneNumber = person.getPhoneNumber();
        String lastName = person.getLastName();
        Person existingPersonByLastName = getPersonByLastName(lastName);
        if (existingPersonByLastName != null && existingPersonByLastName.getPersonId() != personId) {
            errorCodeEnumList.add(PersonErrorCode.PERSON_WITH_LAST_NAME_ALREADY_EXISTS);
        }
        Person existingPersonByPhoneNumber = getPersonByPhoneNumber(phoneNumber);
        if (existingPersonByPhoneNumber != null && existingPersonByPhoneNumber.getPersonId() != personId) {
            errorCodeEnumList.add(PersonErrorCode.PERSON_WITH_PHONE_NUMBER_ALREADY_EXISTS);
        }
        if (!errorCodeEnumList.isEmpty()) {
            throw new DuplicateException("Duplicate Error", errorCodeEnumList);
        }

    }

    private Person getPersonByLastName(String lastName) {
        if (lastName != null && !lastName.isEmpty() && !StringUtils.isBlank(lastName)) {
            return personRepository.findByLastName(lastName);
        }
        return null;
    }

    private Person getPersonByPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.isEmpty() && !StringUtils.isBlank(phoneNumber)) {
            return personRepository.findByPhoneNumber(phoneNumber);
        }
        return null;

    }
}
