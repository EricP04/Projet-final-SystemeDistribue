package com.example.demo.Repository;

import com.example.demo.Entity.Customer;
import com.example.demo.Form.Customer.CustomerForm;

public interface ICustomerDAO {
    Customer createCustomerFromForm(CustomerForm form) throws  Exception;

}
