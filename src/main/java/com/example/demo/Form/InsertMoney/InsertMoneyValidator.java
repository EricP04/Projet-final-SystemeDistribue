package com.example.demo.Form.InsertMoney;


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
public class InsertMoneyValidator implements Validator {



    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == InsertMoneyForm.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        System.out.println("VALIDATE CUSTOM LOGIN FORM");
        InsertMoneyForm insertMoneyForm = (InsertMoneyForm) o;
        ValidationUtils.rejectIfEmpty(errors,"numAccount","NotEmpty.insertMoneyForm.accountNum");
        ValidationUtils.rejectIfEmpty(errors,"amountAsk","NotEmpty.insertMoneyForm.amountAsked");
        if(insertMoneyForm.getAmountAsk()<=0)
        {
            errors.rejectValue("amountAsk","NonNegative.insertMoneyForm.amountAsked");
            return;
        }




    }
}
