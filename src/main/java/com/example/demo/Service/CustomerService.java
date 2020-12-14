package com.example.demo.Service;

import com.example.demo.Entity.Customer;
import com.example.demo.Form.Customer.CustomerForm;
import com.example.demo.Repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService{


    @Autowired
    private CustomerDAO customerDAO;




    @Override
    public Customer createCustomerFromForm(CustomerForm form) throws  Exception{
        return customerDAO.createCustomerFromForm(form);
    }
}
