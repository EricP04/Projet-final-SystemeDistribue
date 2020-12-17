package com.example.demo.DTO;

import com.example.demo.Entity.Supplier;

public class ArticleSupplierDTO {

    private Integer idArticle;
    private Integer idArticleInfo;
    private String name;
    private Supplier supplier;
    private double price;
    private int stock;

    public ArticleSupplierDTO() {
    }

    public ArticleSupplierDTO(Integer idArticle, Integer idArticleInfo, String name, Supplier supplier, double price, int stock) {
        this.idArticle = idArticle;
        this.idArticleInfo = idArticleInfo;
        this.name = name;
        this.supplier = supplier;
        this.price = price;
        this.stock = stock;
    }



    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public Integer getIdArticleInfo() {
        return idArticleInfo;
    }

    public void setIdArticleInfo(Integer idArticleInfo) {
        this.idArticleInfo = idArticleInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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
