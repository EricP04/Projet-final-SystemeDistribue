package com.example.demo.Repository.Customer;

import com.example.demo.Entity.Customer;
import com.example.demo.Form.Login.LoginForm;
import com.example.demo.Form.Customer.CustomerRegisterForm;

public interface ICustomerDAO {
    Customer createCustomerFromFormRegister(CustomerRegisterForm form) throws  Exception;

    Customer searchCustomerFromForm(LoginForm form) throws Exception;

    Customer searchCustomerByMail(String mail);

}
