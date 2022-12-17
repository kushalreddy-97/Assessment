package com.allstate_assessment.Validation;

import com.allstate_assessment.Entity.Person;
import com.allstate_assessment.Exception.DuplicateException;
import com.allstate_assessment.Repository.PersonRepository;
import com.allstate_assessment.Util.IErrorCode;
import com.allstate_assessment.Util.PersonErrorCode;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonAddDuplicateHandler implements DuplicateHandler<Person> {

    @Autowired
    PersonRepository personRepository;

    @Override
    public void checkDuplicate(Person person) {
        List<IErrorCode> errorCodeEnumList = new ArrayList<>();


        String lastName = person.getLastName();
        String phoneNumber = person.getPhoneNumber();

        Person existingPerson = getPersonByLastName(lastName);
        if (existingPerson != null) {
            errorCodeEnumList.add(PersonErrorCode.PERSON_WITH_LAST_NAME_ALREADY_EXISTS);
        }
        Person existingPhoneNumber = findPersonByPhoneNumber(phoneNumber);
        if (existingPhoneNumber != null) {
            errorCodeEnumList.add(PersonErrorCode.PERSON_WITH_PHONE_NUMBER_ALREADY_EXISTS);
        }
        if (!errorCodeEnumList.isEmpty()) {
            throw new DuplicateException("Duplicate Error", errorCodeEnumList);
        }
    }

    private Person getPersonByLastName(String personLastName) {
        if (personLastName != null && !personLastName.isEmpty() && !StringUtils.isBlank(personLastName)) {
            return
                    personRepository.findByLastName(personLastName);
        }
        return null;
    }

    private Person findPersonByPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.isEmpty() && !StringUtils.isBlank(phoneNumber)) {
            return personRepository.findByPhoneNumber(phoneNumber);
        }
        return null;

    }
}
