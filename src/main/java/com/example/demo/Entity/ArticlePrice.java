package com.example.demo.Entity;


import javax.persistence.*;

@Entity
public class ArticlePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @MapsId("id")
    private Article article;

    @ManyToOne
    @MapsId("id")
    private Supplier supplier;

    private int price;

    public ArticlePrice() {
    }

    public ArticlePrice(int id, Article article, Supplier supplier, int price) {
        this.id = id;
        this.article = article;
        this.supplier = supplier;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
