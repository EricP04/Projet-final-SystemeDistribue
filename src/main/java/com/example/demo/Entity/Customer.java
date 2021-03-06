package com.example.demo.Entity;


import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

@Entity
public class Customer extends MyUser{

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;*/

    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="funds_available")
    private float fundsAvailable;
    //@Column(name="email_address",nullable = false,unique = true)
    //private String emailAddress;
    /**
     * @TODO : Add encryption to store the password
     */
   // @Column(name="password",nullable = false,unique = true)
    //private String password;

    public Customer() {
    }

    public Customer(String name, String surname, String address, float fundsAvailable, String emailAddress, String password) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.fundsAvailable = fundsAvailable;
        this.emailAddress = emailAddress;
        this.password = password;
    }



    public Customer(Integer id, String emailAddress, String password, String role, String address, String name, String surname, float fundsAvailable) {
        super(id, emailAddress, password, role, address);
        this.name = name;
        this.surname = surname;
        this.fundsAvailable = fundsAvailable;
    }

  /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }



    public float getFundsAvailable() {
        return fundsAvailable;
    }

    public void setFundsAvailable(float fundsAvailable) {
        this.fundsAvailable = fundsAvailable;
    }

    /*public String getEmailAddress() {
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
    }*/
}
