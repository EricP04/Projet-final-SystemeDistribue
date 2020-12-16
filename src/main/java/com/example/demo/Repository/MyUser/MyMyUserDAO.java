package com.example.demo.Repository.MyUser;

import com.example.demo.Entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyMyUserDAO implements IMyUserDAO {

    @Autowired
    MyUserRepository myUserRepository;


    @Override
    public MyUser getUserByEmail(String email) {
        Iterable<MyUser> users = myUserRepository.findAll();
        for(MyUser myUser : users)
        {
            if(myUser.getEmailAddress().compareTo(email)==0)
                return myUser;
        }
        return null;
    }
}
