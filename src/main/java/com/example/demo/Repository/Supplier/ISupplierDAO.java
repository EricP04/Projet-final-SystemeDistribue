package com.example.demo.Repository.Supplier;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Supplier;
import com.example.demo.Form.Customer.CustomerRegisterForm;
import com.example.demo.Form.Login.LoginForm;
import com.example.demo.Form.Supplier.SupplierRegisterForm;

public interface ISupplierDAO {
    Supplier getUserByEmail(String email);

    Supplier createSupplierFromFormRegister(SupplierRegisterForm form) throws  Exception;

    Supplier searchSupplierFromForm(LoginForm form) throws Exception;

    Supplier searchById(Integer id);
}
