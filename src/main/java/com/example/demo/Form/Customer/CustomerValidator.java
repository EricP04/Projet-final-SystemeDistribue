package com.example.demo.Form.Customer;

import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CustomerDAO;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

@Component
public class CustomerValidator implements Validator {

    private final EmailValidator emailValidator = EmailValidator.getInstance(true);

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == CustomerForm.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        CustomerForm customerForm = (CustomerForm) o;

        ValidationUtils.rejectIfEmpty(errors,"name","NotEmpty.customerForm.name");
        ValidationUtils.rejectIfEmpty(errors,"surname","NotEmpty.customerForm.surname");
        ValidationUtils.rejectIfEmpty(errors,"emailAddress","NotEmpty.customerForm.email");
        ValidationUtils.rejectIfEmpty(errors,"password","NotEmpty.customerForm.password");
        ValidationUtils.rejectIfEmpty(errors,"confirmPassword","NotEmpty.customerForm.confirmPassword");
        ValidationUtils.rejectIfEmpty(errors, "address","NotEmpty.customerForm.address");
        System.out.println("Customer form email address = " + customerForm.getEmailAddress());
        if(!this.emailValidator.isValid(customerForm.getEmailAddress()))
        {
            errors.rejectValue("emailAddress","Pattern.customerForm.email");
        }
        else
        {
            if(customerForm.getId()==null)
            {
                Customer customer = customerDAO.findCustomerByEmailAddress(customerForm.getEmailAddress());
                if(customer != null)
                {
                    errors.rejectValue("emailAddress","Duplicate.customerForm.email");
                }
            }
        }

        if(!errors.hasErrors())
        {
            if(!customerForm.getConfirmPassword().equals(customerForm.getPassword()))
                errors.rejectValue("confirmPassword","Match.customerForm.confirmPassword");
        }

    }
}
