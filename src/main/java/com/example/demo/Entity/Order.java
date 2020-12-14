package com.example.demo.Entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="OrderTable")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    private List<Article> articles;

    @ManyToOne
    private Customer customer;

    private int status;

    private float totalPrice;

    public Order() {
    }

    public Order(int id, List<Article> articles, Customer customer, int status, float totalPrice) {
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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> article) {
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

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
