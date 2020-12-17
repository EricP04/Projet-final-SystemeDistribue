package com.example.demo.Entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Supplier extends MyUser{

    private String societyName;
    private String numTva;
    @OneToMany
    private List<ArticleInformation> articleInformation;
    public Supplier()
    {

    }

    public Supplier(String emailAddress, String password, String role, String address, String societyName, String numTva, List<ArticleInformation> articleInformation) {
        super(emailAddress, password, role, address);
        this.societyName = societyName;
        this.numTva = numTva;
        if(articleInformation!=null)
            this.articleInformation = articleInformation;
        else
            this.articleInformation = new ArrayList<>();
    }

    public String getNumTva() {
        return numTva;
    }

    public void setNumTva(String numTva) {
        this.numTva = numTva;
    }


    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public List<ArticleInformation> getArticleInformation() {
        return articleInformation;
    }

    public void setArticleInformation(List<ArticleInformation> articleInformation) {
        this.articleInformation = articleInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return societyName.equals(supplier.societyName) &&
                numTva.equals(supplier.numTva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(societyName, numTva);
    }
}
