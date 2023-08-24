package com.personal.globalpayablesyestem.Bank.validators;

import com.personal.globalpayablesyestem.Bank.Branch.BranchRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class BranchIdMustExistValidator implements ConstraintValidator<BranchIdMustExist, String> {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public void initialize(BranchIdMustExist constraintAnnotation) {

    }
    @Override
    public boolean isValid(String branchId, ConstraintValidatorContext constraintValidatorContext) {
        return branchRepository.findById(branchId).isPresent();
    }
}
