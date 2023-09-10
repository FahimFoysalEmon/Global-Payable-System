package com.personal.globalpayablesyestem.Country.validators;

import com.personal.globalpayablesyestem.Bank.BankRepository;
import com.personal.globalpayablesyestem.Country.CountryRepository;
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
