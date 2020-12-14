package com.example.demo.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany
    private List<ArticlePrice> priceList;

    @OneToMany
    private List<ArticleStock> stockList;


    public Article()
    {

    }

    public Article(int id, String name, List<ArticlePrice> priceList, List<ArticleStock> stockList) {
        this.id = id;
        this.name = name;
        this.priceList = priceList;
        this.stockList = stockList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArticlePrice> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<ArticlePrice> priceList) {
        this.priceList = priceList;
    }

    public List<ArticleStock> getStockList() {
        return stockList;
    }

    public void setStockList(List<ArticleStock> stockList) {
        this.stockList = stockList;
    }
}
