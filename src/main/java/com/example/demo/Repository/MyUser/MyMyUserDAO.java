package com.example.demo.Repository.MyUser;

import com.example.demo.Config.MyUserDetails;
import com.example.demo.Entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyMyUserDAO implements IMyUserDAO {

    @Autowired
    MyUserRepository myUserRepository;


    public MyUser getUserByEmail(String email) {
        Iterable<MyUser> users = myUserRepository.findAll();
        for(MyUser myUser : users)
        {
            System.out.println("ON COMPARE :" + email+ " avec ");
            System.out.println("MY USER DANS LA BOUCLE : " + myUser.getEmailAddress());
            if(myUser.getEmailAddress().compareTo(email)==0)
                return myUser;
        }
        throw new UsernameNotFoundException("Could not find user");
    }
}
