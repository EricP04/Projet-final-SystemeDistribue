package com.example.demo.Entity;

import javax.persistence.*;

@Entity
public class ArticleInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Article article;

    private double price;

    private int stock;

    public ArticleInformation() {
    }

    public ArticleInformation(Integer id, Supplier supplier, Article article, double price, int stock) {
        this.id = id;
        this.supplier = supplier;
        this.article = article;
        this.price = price;
        this.stock = stock;
    }

    public ArticleInformation(Supplier supplier, Article article, double price, int stock) {
        this.supplier = supplier;
        this.article = article;
        this.price = price;
        this.stock = stock;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }



}
