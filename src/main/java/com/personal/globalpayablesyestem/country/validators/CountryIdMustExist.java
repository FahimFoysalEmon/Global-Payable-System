package com.personal.globalpayablesyestem.country.validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CountryIdMustExistValidator.class)
public @interface CountryIdMustExist {

    String message() default "Country Id INVALID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
