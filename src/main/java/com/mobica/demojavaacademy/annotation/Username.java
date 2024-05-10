package com.mobica.demojavaacademy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
public @interface Username {
    
    String message() default "cannot contain uppercase letters or special characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class UsernameValidator implements ConstraintValidator<Username, String> {

    // final String[] specialCharacters = new String[] {"@", "#", "$", "%", "^", "&", "*"};

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        // for (String c: specialCharacters) {
        //     if (username.contains(c)) {
        //         return false;
        //     }
        // }
        // for (Character c: username.toCharArray()) {
        //     if (Character.isUpperCase(c)) {
        //         return false;
        //     }
        // }
        // return true;
        Pattern pattern = Pattern.compile("[^a-z0-9]");
        Matcher matcher = pattern.matcher(username);
        return !matcher.find();
    }
}
