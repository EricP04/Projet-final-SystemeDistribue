package com.example.demo.Service.Supplier;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Supplier;
import com.example.demo.Form.Customer.CustomerRegisterForm;
import com.example.demo.Form.Login.LoginForm;
import com.example.demo.Form.Supplier.SupplierRegisterForm;
import com.example.demo.Repository.Supplier.SupplierDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService implements ISupplierService {

    @Autowired
    private SupplierDAO supplierDAO;

    @Override
    public Supplier getUserByEmail(String email) {
        return supplierDAO.getUserByEmail(email);
    }

    @Override
    public Supplier createSupplierFromFormRegister(SupplierRegisterForm form) throws  Exception{
        return supplierDAO.createSupplierFromFormRegister(form);
    }

    @Override
    public Supplier searchSupplierFromForm(LoginForm form) throws Exception {
        return supplierDAO.searchSupplierFromForm(form);
    }

}
