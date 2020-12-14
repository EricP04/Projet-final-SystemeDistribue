package com.example.demo.Repository;

import com.example.demo.Entity.Customer;
import com.example.demo.Form.Customer.CustomerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomerDAO implements ICustomerDAO {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean save(Customer customer) throws Exception {
        System.out.println("CUSTOMER DAO SAVE");
       customerRepository.save(customer);
       return true;
    }
    @Override
    public Customer createCustomerFromForm(CustomerForm customerForm) throws Exception
    {
        String encryptedPassword = this.passwordEncoder.encode(customerForm.getPassword());
        Customer customer = new Customer(customerForm.getName(), customerForm.getSurname(),
                customerForm.getAddress(), (float) 0.0,
                customerForm.getEmailAddress(), encryptedPassword);
        if(save(customer))
            return customer;
        return null;
    }

    public Customer findCustomerByEmailAddress(String emailAddress)
    {
        Iterable<Customer> listCustomer = customerRepository.findAll();
        for(Customer c: listCustomer)
        {
            if(c.getEmailAddress().compareTo(emailAddress)==0)
                return c;
        }

        return null;
    }


}
