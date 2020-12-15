package com.example.demo.Service;

import com.example.demo.Entity.Customer;
import com.example.demo.Form.Customer.CustomerLoginForm;
import com.example.demo.Form.Customer.CustomerRegisterForm;
import com.example.demo.Repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService{


    @Autowired
    private CustomerDAO customerDAO;




    @Override
    public Customer createCustomerFromFormRegister(CustomerRegisterForm form) throws  Exception{
        return customerDAO.createCustomerFromFormRegister(form);
    }

    @Override
    public Customer searchCustomerFromForm(CustomerLoginForm form) throws Exception {
        return customerDAO.searchCustomerFromForm(form);
    }
}
