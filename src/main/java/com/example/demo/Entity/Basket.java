package com.example.demo.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

   /* @OneToOne
    private Supplier supplier;*/
    @OneToMany
    private List<ArticleOrderInformation> articles;

    @ManyToOne
    private Customer customer;

    private double totalPrice;

    public Basket() {
    }

    public Basket(List<ArticleOrderInformation> articles, Customer customer, double totalPrice) {
        this.articles = articles;
        this.customer = customer;
        this.totalPrice = totalPrice;
    }

    public Basket(Customer customer, double totalPrice) {
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.articles = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

  /*  public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }*/

    public List<ArticleOrderInformation> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleOrderInformation> articles) {
        this.articles = articles;
    }

    public void addArticle(ArticleOrderInformation article)
    {
        for(int i = 0 ;i<articles.size();i++)
        {
            if(articles.get(i).getArticle().equals(article.getArticle()) &&
                    articles.get(i).getSupplier().equals(article.getSupplier())
            )
            {
                articles.set(i,article);
                return;
            }
        }
        this.articles.add(article);
    }
}
