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
    private List<ArticleOrderInformation> articles;

    @ManyToOne
    private Customer customer;

    private int status;

    private double totalPrice;

    public Order() {
    }

    public Order(List<ArticleOrderInformation> articles, Customer customer, int status, double totalPrice) {
        this.articles = articles;
        this.customer = customer;
        this.status = status;
        this.totalPrice = totalPrice;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ArticleOrderInformation> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleOrderInformation> article) {
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
    public String getStatusString()
    {
        switch(status)
        {
            case 0:
                return "Processed";
            case 1:
                return "Shipped";
            case 2:
                return "Received";
        }
        return "";
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
