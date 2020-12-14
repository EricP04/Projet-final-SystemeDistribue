package com.example.demo.Entity;


import javax.persistence.*;

@Entity
public class ArticleStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Article article;

    @ManyToOne
    private Supplier supplier;

    private int stock;

    public ArticleStock() {
    }

    public ArticleStock(int id, Article article, Supplier supplier, int stock) {
        this.id = id;
        this.article = article;
        this.supplier = supplier;
        this.stock = stock;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
