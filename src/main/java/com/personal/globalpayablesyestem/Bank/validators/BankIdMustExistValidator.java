package com.personal.globalpayablesyestem.Bank.validators;

import com.personal.globalpayablesyestem.Bank.BankRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class BankIdMustExistValidator implements ConstraintValidator<BankIdMustExist, String> {

    @Autowired
    private BankRepository bankRepository;

    public void initialize(BankIdMustExist constraint) {
    }

    public boolean isValid(String id, ConstraintValidatorContext context) {
        return bankRepository.findById(id).isPresent();
    }
}
