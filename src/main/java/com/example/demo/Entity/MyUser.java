package com.example.demo.Entity;

import com.sun.istack.NotNull;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance
@Table(name = "MY_USER")
public abstract class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(name="email_address",nullable = false,unique = false)
    protected String emailAddress;
    protected String password;
    protected String role;
    protected String address;

    public MyUser(){}

    public MyUser(Integer id, String emailAddress, String password, String role, String address) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.password = password;
        this.role = role;
        this.address = address;
    }

    public MyUser(String emailAddress, String password, String role, String address) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.role = role;
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyUser myUser = (MyUser) o;
        return id.equals(myUser.id) &&
                emailAddress.equals(myUser.emailAddress) &&
                password.equals(myUser.password) &&
                role.equals(myUser.role) &&
                address.equals(myUser.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emailAddress, password, role, address);
    }

}
