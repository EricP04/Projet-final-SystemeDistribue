package com.example.demo.Form.Customer;

public class CustomerLoginForm {

    private String emailAddress;
    private String password;

    public CustomerLoginForm()
    {

    }

    public CustomerLoginForm(String emailAddress, String password)
    {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String email) {
        this.emailAddress = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
