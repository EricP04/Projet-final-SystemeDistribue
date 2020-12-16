package com.example.demo.Repository.MyUser;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.MyUser;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

public interface MyUserRepository extends CrudRepository<MyUser, Integer> {

    @Override
    Iterable<MyUser> findAll();


}
