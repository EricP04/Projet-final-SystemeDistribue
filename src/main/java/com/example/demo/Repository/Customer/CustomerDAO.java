package com.example.demo.Repository.Customer;

import com.example.demo.Entity.Customer;
import com.example.demo.Form.Login.LoginForm;
import com.example.demo.Form.Customer.CustomerRegisterForm;
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
    public Customer createCustomerFromFormRegister(CustomerRegisterForm customerRegisterForm) throws Exception
    {
        String encryptedPassword = this.passwordEncoder.encode(customerRegisterForm.getPassword());
        Customer customer = new Customer(customerRegisterForm.getName(), customerRegisterForm.getSurname(),
                customerRegisterForm.getAddress(), (float) 0.0,
                customerRegisterForm.getEmailAddress(), encryptedPassword);
        customer.setRole("CUSTOMER");
        if(save(customer))
            return customer;
        return null;
    }

    @Override
    public Customer searchCustomerFromForm(LoginForm form) throws Exception {
        return checkCredentials(form.getEmailAddress(),form.getPassword());
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

    public Customer checkCredentials(String emailAddress, String password)
    {
        System.out.println("CHECK CREDENTIAL");
        Iterable<Customer> listCustomer = customerRepository.findAll();

        for(Customer c : listCustomer)
        {
            System.out.println("CUSTOMER = email " + c.getEmailAddress());
            System.out.println(" password : " + c.getPassword());
            System.out.println("Password send encoded  : " +passwordEncoder.encode(password) );
            System.out.println("TEST : " +passwordEncoder.matches(password,c.getPassword()));
            System.out.println("TEST2 : " + passwordEncoder.encode(password).compareTo(c.getPassword()));
            System.out.println("Test 3:" +passwordEncoder.matches(c.getPassword(),passwordEncoder.encode(password)));
            if(c.getEmailAddress().compareTo(emailAddress) == 0 && passwordEncoder.matches(password,c.getPassword()))
                return c;
        }
        return null;
    }

}
