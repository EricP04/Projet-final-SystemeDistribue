package com.example.demo.Form.Supplier;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Supplier;
import com.example.demo.Form.Login.LoginForm;
import com.example.demo.Form.Login.LoginSupplierForm;
import com.example.demo.Repository.Customer.CustomerDAO;
import com.example.demo.Repository.Supplier.SupplierDAO;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SupplierLoginValidator implements Validator {


    @Autowired
    private SupplierDAO supplierDAO;
    private final EmailValidator emailValidator = EmailValidator.getInstance(true);

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == LoginSupplierForm.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        LoginForm loginForm = (LoginForm) o;
        ValidationUtils.rejectIfEmpty(errors, "emailAddress", "NotEmpty.customerLoginForm.email");
        ValidationUtils.rejectIfEmpty(errors, "password", "NotEmpty.customerLoginForm.password");
        if (!this.emailValidator.isValid(loginForm.getEmailAddress())) {
            System.out.println("EMAIL INVALID :  " + loginForm.getEmailAddress());
            errors.rejectValue("emailAddress", "Pattern.customerLoginForm.email");
        } else {
            Supplier supplier = supplierDAO.checkCredentials(loginForm.getEmailAddress(), loginForm.getPassword());
            if (supplier == null) {
                errors.rejectValue("emailAddress", "Bad.customerLoginForm.credentials");
                errors.rejectValue("password", "Bad.customerLoginForm.credentials");

            }

        }

    }






}
