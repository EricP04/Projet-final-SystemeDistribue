package com.example.demo.Entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Order_Table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    private List<ArticleInformation> articles;

    @ManyToOne
    private Customer customer;

    private int status;

    private double totalPrice;

    public Order() {
    }

    public Order(int id, List<ArticleInformation> articles, Customer customer, int status, double totalPrice) {
        this.id = id;
        this.articles = articles;
        this.customer = customer;
        this.status = status;
        this.totalPrice = totalPrice;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ArticleInformation> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleInformation> article) {
        this.articles = article;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
