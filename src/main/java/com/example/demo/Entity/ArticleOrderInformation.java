package com.example.demo.Entity;

import javax.persistence.*;

@Entity
public class ArticleOrderInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Supplier supplier;

    @ManyToOne
    private Article article;

    private double price;

    private int count;

    public ArticleOrderInformation() {
    }

    public ArticleOrderInformation(Supplier supplier, Article article, double price, int count) {
        this.supplier = supplier;
        this.article = article;
        this.price = price;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
