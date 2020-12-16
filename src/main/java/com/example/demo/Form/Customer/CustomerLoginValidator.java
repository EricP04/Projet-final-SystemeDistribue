package com.example.demo.Form.Customer;


import com.example.demo.Entity.Customer;
import com.example.demo.Form.Login.LoginCustomerForm;
import com.example.demo.Form.Login.LoginForm;
import com.example.demo.Repository.Customer.CustomerDAO;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CustomerLoginValidator implements Validator {

    private final EmailValidator emailValidator = EmailValidator.getInstance(true);

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == LoginCustomerForm.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        System.out.println("VALIDATE CUSTOM LOGIN FORM");
        LoginForm loginForm = (LoginForm) o;
        ValidationUtils.rejectIfEmpty(errors,"emailAddress","NotEmpty.loginForm.email");
        ValidationUtils.rejectIfEmpty(errors,"password","NotEmpty.loginForm.password");
        if(!this.emailValidator.isValid(loginForm.getEmailAddress()))
        {
            System.out.println("EMAIL INVALID :  " + loginForm.getEmailAddress());
            errors.rejectValue("emailAddress","Pattern.loginForm.email");
        }
        else
        {
            Customer customer = customerDAO.checkCredentials(loginForm.getEmailAddress(), loginForm.getPassword());
            if(customer == null)
            {
                errors.rejectValue("emailAddress","Bad.loginForm.credentials");
                errors.rejectValue("password","Bad.loginForm.credentials");

            }

        }


    }
}
