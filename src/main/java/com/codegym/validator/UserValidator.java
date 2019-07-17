package com.codegym.validator;

import com.codegym.request.CreateUserRequest;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateUserRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void  validate(Object target, Errors errors) {

        CreateUserRequest userRequest = (CreateUserRequest) target;

        String phoneNumber = userRequest.getPhoneNumber();

        ValidationUtils.rejectIfEmpty(errors, "phoneNumber", "phoneNumber.empty");
        if (phoneNumber.length()>11 || phoneNumber.length()<10){
            errors.rejectValue("phoneNumber", "phoneNumber.length");
        }
        if (!phoneNumber.startsWith("0")){
            errors.rejectValue("phoneNumber", "phoneNumber.startsWith");
        }
        if (!phoneNumber.matches("(^$|[0-9]*$)")){
            errors.rejectValue("phoneNumber", "phoneNumber.matches");
        }

        if(userService.existsByEmail(userRequest.getEmail())) {
            errors.rejectValue("email", "email.exist");
        }
    }
}