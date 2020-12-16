package com.example.demo.Service;

import com.example.demo.Config.MyUserDetails;
import com.example.demo.Entity.MyUser;
import com.example.demo.Repository.MyUser.MyMyUserDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsServicesImpl implements UserDetailsService {

    private MyMyUserDAO myUserDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MyUser myUser = myUserDAO.getUserByEmail(email);
        if(myUser==null)
            throw new UsernameNotFoundException("Email could not be found");
        return new MyUserDetails(myUser);
    }
}
