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
public class CustomerRegisterValidator implements Validator {

    private final EmailValidator emailValidator = EmailValidator.getInstance(true);

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == CustomerRegisterForm.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        System.out.println("CUSTOMER REGISTer VALIDATOR");
        CustomerRegisterForm customerRegisterForm = (CustomerRegisterForm) o;

        ValidationUtils.rejectIfEmpty(errors,"name","NotEmpty.customerRegisterForm.name");
        ValidationUtils.rejectIfEmpty(errors,"surname","NotEmpty.customerRegisterForm.surname");
        ValidationUtils.rejectIfEmpty(errors,"emailAddress","NotEmpty.customerRegisterForm.email");
        ValidationUtils.rejectIfEmpty(errors,"password","NotEmpty.customerRegisterForm.password");
        ValidationUtils.rejectIfEmpty(errors,"confirmPassword","NotEmpty.customerRegisterForm.confirmPassword");
        ValidationUtils.rejectIfEmpty(errors, "address","NotEmpty.customerRegisterForm.address");
        System.out.println("Customer form email address = " + customerRegisterForm.getEmailAddress());
        if(!this.emailValidator.isValid(customerRegisterForm.getEmailAddress()))
        {
            errors.rejectValue("emailAddress","Pattern.customerRegisterForm.email");
        }
        else
        {
           // if(customerRegisterForm.getId()==null)
            //{
                Customer customer = customerDAO.findCustomerByEmailAddress(customerRegisterForm.getEmailAddress());
                if(customer != null)
                {
                    errors.rejectValue("emailAddress","Duplicate.customerRegisterForm.email");
                }
         //   }
        }

        if(!errors.hasErrors())
        {
            if(!customerRegisterForm.getConfirmPassword().equals(customerRegisterForm.getPassword()))
                errors.rejectValue("confirmPassword","Match.customerRegisterForm.confirmPassword");
        }

    }
}
