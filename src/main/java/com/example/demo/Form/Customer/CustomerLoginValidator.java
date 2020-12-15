package com.example.demo.Form.Customer;


import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CustomerDAO;
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
        return aClass == CustomerLoginForm.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        System.out.println("VALIDATE CUSTOM LOGIN FORM");
        CustomerLoginForm customerLoginForm = (CustomerLoginForm) o;
        ValidationUtils.rejectIfEmpty(errors,"emailAddress","NotEmpty.customerLoginForm.email");
        ValidationUtils.rejectIfEmpty(errors,"password","NotEmpty.customerLoginForm.password");
        if(!this.emailValidator.isValid(customerLoginForm.getEmailAddress()))
        {
            System.out.println("EMAIL INVALID :  " + customerLoginForm.getEmailAddress());
            errors.rejectValue("emailAddress","Pattern.customerLoginForm.email");
        }
        else
        {
            Customer customer = customerDAO.checkCredentials(customerLoginForm.getEmailAddress(), customerLoginForm.getPassword());
            if(customer == null)
            {
                errors.rejectValue("emailAddress","Bad.customerLoginForm.credentials");
                errors.rejectValue("password","Bad.customerLoginForm.credentials");

            }

        }


    }
}
