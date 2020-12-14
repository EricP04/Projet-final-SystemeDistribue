package com.example.demo.Form.Customer;


import com.example.demo.Entity.Customer;

public class CustomerForm {
    private Integer id;
    private String name;
    private String surname;
    private String address;
    private String emailAddress;
    private String password;
    private String confirmPassword;

    public CustomerForm()
    {

    }

    public CustomerForm(Integer id, String name, String surname, String address, String emailAddress, String password, String confirmPassword) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.emailAddress = emailAddress;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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
}
