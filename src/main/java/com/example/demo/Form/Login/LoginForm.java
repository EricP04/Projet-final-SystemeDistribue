package com.example.demo.Form.Login;

public class LoginForm {

    private String emailAddress;
    private String password;

    public LoginForm()
    {

    }

    public LoginForm(String emailAddress, String password)
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
