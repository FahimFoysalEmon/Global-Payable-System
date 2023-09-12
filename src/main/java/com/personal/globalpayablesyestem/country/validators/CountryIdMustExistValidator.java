package com.personal.globalpayablesyestem.country.validators;

import com.personal.globalpayablesyestem.country.CountryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class CountryIdMustExistValidator implements ConstraintValidator<CountryIdMustExist, String> {

    @Autowired
    private CountryRepository countryRepository;

    public void initialize(CountryIdMustExist constraint) {
    }

    public boolean isValid(String id, ConstraintValidatorContext context) {
        return countryRepository.findById(id).isPresent();
    }
}
