package com.example.demo.Repository;

import com.example.demo.Entity.Customer;
import com.example.demo.Form.Customer.CustomerLoginForm;
import com.example.demo.Form.Customer.CustomerRegisterForm;
import org.springframework.security.core.userdetails.UserDetails;

public interface ICustomerDAO {
    Customer createCustomerFromFormRegister(CustomerRegisterForm form) throws  Exception;

    Customer searchCustomerFromForm(CustomerLoginForm form) throws Exception;

}
