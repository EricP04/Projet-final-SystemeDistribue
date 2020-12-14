package com.example.demo.Entity;


import javax.persistence.*;

@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String societyName;
    private String emailAddress;
    /**
     * @Todo: Encryption password
     */
    private String password;

    public Supplier(int id, String societyName, String emailAddress, String password) {
        this.id = id;
        this.societyName = societyName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public Supplier() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
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
