package com.personal.globalpayablesyestem.bank.validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BankIdMustExistValidator.class)
public @interface BankIdMustExist {

    String message() default "Bank Id INVALID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
