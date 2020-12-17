package com.example.demo.Repository.Supplier;

import com.example.demo.Entity.Supplier;
import com.example.demo.Form.Login.LoginForm;
import com.example.demo.Form.Supplier.SupplierRegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SupplierDAO implements ISupplierDAO {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean save(Supplier supplier) throws Exception {
        supplierRepository.save(supplier);
        return true;
    }

    @Override
    public Supplier getUserByEmail(String email) {
        Iterable<Supplier> suppliers = supplierRepository.findAll();
        for(Supplier supplier : suppliers)
        {
            if(supplier.getEmailAddress().compareTo(email)==0)
                return supplier;
        }
        return null;
    }

    @Override
    public Supplier createSupplierFromFormRegister(SupplierRegisterForm form) throws Exception {
        String encryptedPassword = this.passwordEncoder.encode(form.getPassword());
        Supplier supplier = new Supplier(form.getEmailAddress(), encryptedPassword,
                "SUPPLIER",
                form.getAddress(), form.getSocietyName(), form.getNumTva(),null);
        if(save(supplier))
            return supplier;
        return null;
    }

    @Override
    public Supplier searchSupplierFromForm(LoginForm form) throws Exception {
        return checkCredentials(form.getEmailAddress(),form.getPassword());

    }

    @Override
    public Supplier searchById(Integer id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return supplier.get();
    }

    public Supplier checkCredentials(String emailAddress, String password)
    {
        System.out.println("CHECK CREDENTIAL");
        Iterable<Supplier> suppliers = supplierRepository.findAll();

        for(Supplier supplier : suppliers)
        {
            if(supplier.getEmailAddress().compareTo(emailAddress) == 0 && passwordEncoder.matches(password,supplier.getPassword()))
                return supplier;
        }
        return null;
    }

    public Supplier findCustomerByEmailAddress(String emailAddress)
    {
        Iterable<Supplier> suppliers = supplierRepository.findAll();
        for(Supplier supplier: suppliers)
        {
            if(supplier.getEmailAddress().compareTo(emailAddress)==0)
                return supplier;
        }

        return null;
    }
}
