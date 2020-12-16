package com.example.demo.Service;

import com.example.demo.Config.MyUserDetails;
import com.example.demo.Entity.MyUser;
import com.example.demo.Repository.MyUser.MyMyUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsServicesImpl implements UserDetailsService {

    @Autowired
    private MyMyUserDAO myUserDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MyUser myUser = myUserDAO.getUserByEmail(email);
        System.out.println("LOAD USER BY USERNAME");
        if(myUser==null) {
            System.out.println("MY USER EST NULL");
            throw new UsernameNotFoundException("Email could not be found");
        }
        return new MyUserDetails(myUser);
    }
}
