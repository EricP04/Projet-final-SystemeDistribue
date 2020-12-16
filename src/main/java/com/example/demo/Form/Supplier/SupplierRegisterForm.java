package com.example.demo.Form.Supplier;

public class SupplierRegisterForm {

    private Integer id;
    private String societyName;
    private String address;
    private String numTva;
    private String emailAddress;
    private String password;
    private String confirmPassword;
    public SupplierRegisterForm() {
    }


    public SupplierRegisterForm(Integer id, String societyName, String address, String numTva, String emailAddress, String password, String confirmPassword) {
        this.id = id;
        this.societyName = societyName;
        this.address = address;
        this.numTva = numTva;
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

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumTva() {
        return numTva;
    }

    public void setNumTva(String numTva) {
        this.numTva = numTva;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
