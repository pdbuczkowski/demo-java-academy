package com.mobica.demojavaacademy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface Age {

    String message() default "invalid age";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class AgeValidator implements ConstraintValidator<Age, Date> {

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        var millis = new Date().getTime() - date.getTime();
        var days = TimeUnit.MILLISECONDS.toDays(millis);
        return days / 365 >= 18;
    }
}
