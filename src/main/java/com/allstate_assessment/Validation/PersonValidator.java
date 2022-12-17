package com.allstate_assessment.Validation;

import com.allstate_assessment.Entity.Person;
import com.allstate_assessment.Util.PersonErrorCode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.lastName",
                PersonErrorCode.PERSON_LAST_NAME_INVALID.getCodeStr(),
                PersonErrorCode.PERSON_LAST_NAME_INVALID.getMessage());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.address.addressLine1",
                PersonErrorCode.PERSON_ADDRESS_ADDRESS_LINE1_INVALID.getCodeStr(),
                PersonErrorCode.PERSON_ADDRESS_ADDRESS_LINE1_INVALID.getMessage());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.phoneNumber",
                PersonErrorCode.PERSON_PHONE_NUMBER_INVALID.getCodeStr(),
                PersonErrorCode.PERSON_PHONE_NUMBER_INVALID.getMessage());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.address.postcode",
                PersonErrorCode.PERSON_ADDRESS_POSTCODE_INVALID.getCodeStr(),
                PersonErrorCode.PERSON_ADDRESS_POSTCODE_INVALID.getMessage());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.address.country",
                PersonErrorCode.PERSON_ADDRESS_COUNTRY_INVALID.getCodeStr(),
                PersonErrorCode.PERSON_ADDRESS_COUNTRY_INVALID.getMessage());
        validatePersonPatters(errors, person);

    }

    private void validatePersonPatters(Errors errors, Person person) {

        if (person.getLastName() != null && !person.getLastName().isEmpty()
                && !StringUtils.isEmpty(person.getLastName())) {
            boolean isValid = CommonValidatorUtil.isName(person.getLastName());
            if (!isValid) {
                errors.rejectValue("person.lastName", PersonErrorCode.PERSON_LAST_NAME_INVALID.getCodeStr(),
                        PersonErrorCode.PERSON_LAST_NAME_INVALID.getMessage());
            }
        }

        if (person.getFirstName() != null && !person.getFirstName().isEmpty()
                && !StringUtils.isEmpty(person.getFirstName())) {
            boolean isValid = CommonValidatorUtil.isAlphabetical(person.getFirstName());
            if (!isValid) {
                errors.rejectValue("person.firstName", PersonErrorCode.PERSON_FIRST_NAME_INVALID.getCodeStr(),
                        PersonErrorCode.PERSON_FIRST_NAME_INVALID.getMessage());
            }
        }


        if (person.getAddress().get(0).getPostcode().length() > 6 && person.getAddress().get(0).getPostcode().length() < 10) {
            boolean isValid = CommonValidatorUtil.isAlphanumeric(person.getAddress().get(0).getPostcode());
            if (!isValid) {
                errors.rejectValue("person.address.postcode", PersonErrorCode.PERSON_ADDRESS_POSTCODE_INVALID.getCodeStr(),
                        PersonErrorCode.PERSON_ADDRESS_POSTCODE_INVALID.getMessage());
            }
        }

        if (person.getAddress().get(0).getCountry() != null && !person.getAddress().get(0).getCountry().isEmpty() &&
                !StringUtils.isEmpty(person.getAddress().get(0).getCountry())) {
            boolean isValid = CommonValidatorUtil.isAlphabetical(person.getAddress().get(0).getCountry());
            if (!isValid) {
                errors.rejectValue("person.address.country", PersonErrorCode.PERSON_ADDRESS_COUNTRY_INVALID.getCodeStr(),
                        PersonErrorCode.PERSON_ADDRESS_COUNTRY_INVALID.getMessage());
            }
        }

        if (person.getPhoneNumber() != null && !person.getPhoneNumber().isEmpty()
                && !StringUtils.isEmpty(person.getPhoneNumber())) {
            boolean isValid = CommonValidatorUtil.isPhoneNumber(person.getPhoneNumber());
            if (!isValid) {
                errors.rejectValue("person.phoneNumber", PersonErrorCode.PERSON_PHONE_NUMBER_INVALID.getCodeStr(),
                        PersonErrorCode.PERSON_PHONE_NUMBER_INVALID.getMessage());
            }
        }

        if (person.getMobileNumber() != null && !person.getMobileNumber().isEmpty()
                && !StringUtils.isEmpty(person.getMobileNumber())) {
            boolean isValid = CommonValidatorUtil.isPhoneNumber(person.getMobileNumber());
            if (!isValid) {
                errors.rejectValue("person.mobileNumber", PersonErrorCode.PERSON_MOBILE_NUMBER_INVALID.getCodeStr(),
                        PersonErrorCode.PERSON_MOBILE_NUMBER_INVALID.getMessage());
            }
        }


    }
}
