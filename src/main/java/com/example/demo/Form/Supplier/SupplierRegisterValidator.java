package com.example.demo.Form.Supplier;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Supplier;
import com.example.demo.Form.Customer.CustomerRegisterForm;
import com.example.demo.Repository.Customer.CustomerDAO;
import com.example.demo.Repository.Supplier.SupplierDAO;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class SupplierRegisterValidator implements Validator {
    private final EmailValidator emailValidator = EmailValidator.getInstance(true);

    @Autowired
    private SupplierDAO supplierDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == SupplierRegisterForm.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        SupplierRegisterForm customerRegisterForm = (SupplierRegisterForm) o;


        ValidationUtils.rejectIfEmpty(errors,"societyName","NotEmpty.supplierRegisterForm.societyName");
        ValidationUtils.rejectIfEmpty(errors,"numTva","NotEmpty.supplierRegisterForm.numTva");
        ValidationUtils.rejectIfEmpty(errors,"emailAddress","NotEmpty.supplierRegisterForm.email");
        ValidationUtils.rejectIfEmpty(errors,"password","NotEmpty.supplierRegisterForm.password");
        ValidationUtils.rejectIfEmpty(errors,"confirmPassword","NotEmpty.supplierRegisterForm.confirmPassword");
        ValidationUtils.rejectIfEmpty(errors, "address","NotEmpty.supplierRegisterForm.address");
        if(!this.emailValidator.isValid(customerRegisterForm.getEmailAddress()))
        {
            errors.rejectValue("emailAddress","Pattern.supplierRegisterForm.email");
        }
        else
        {
            // if(customerRegisterForm.getId()==null)
            //{
            Supplier supplier = supplierDAO.findCustomerByEmailAddress(customerRegisterForm.getEmailAddress());
            if(supplier != null)
            {
                errors.rejectValue("emailAddress","Duplicate.supplierRegisterForm.email");
            }
            //   }
        }

        if(!errors.hasErrors())
        {
            if(!customerRegisterForm.getConfirmPassword().equals(customerRegisterForm.getPassword()))
                errors.rejectValue("confirmPassword","Match.supplierRegisterForm.confirmPassword");
        }

    }
}
