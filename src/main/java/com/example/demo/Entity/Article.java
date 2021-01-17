package com.example.demo.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String type;

   /* @OneToMany
    private List<ArticlePrice> priceList;

    @OneToMany
    private List<ArticleStock> stockList;*/
    @OneToMany
    private List<ArticleInformation> articleInformations;


    public Article()
    {

    }

    public Article(String name, String type, List<ArticleInformation> articleInformations) {
        this.name = name;
        this.type = type;
        this.articleInformations = articleInformations;
    }

    public Article(Integer id, String name, List<ArticleInformation> articleInformations) {
        this.id = id;
        this.name = name;
        this.articleInformations = articleInformations;
    }



    public Article(String name, String type) {
        this.name = name;
        this.type = type;
        this.articleInformations = new ArrayList<>();
    }
    /*public Article(String name, List<ArticlePrice> priceList, List<ArticleStock> stockList) {
        this.name = name;
        this.priceList = priceList;
        this.stockList = stockList;
    }

    public Article(int id, String name, List<ArticlePrice> priceList, List<ArticleStock> stockList) {
        this.id = id;
        this.name = name;
        this.priceList = priceList;
        this.stockList = stockList;
    }*/



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ArticleInformation> getArticleInformations() {
        return articleInformations;
    }

    public void setArticleInformations(List<ArticleInformation> articleInformations) {
        this.articleInformations = articleInformations;
    }

    public void addArticleInformation(ArticleInformation articleInformation)
    {
        this.articleInformations.add(articleInformation);
    }

    public void updateArticleInformation(ArticleInformation articleInformation)
    {
        boolean find = false;
        for(int i = 0 ; i<articleInformations.size() && !find;i++)
        {
            if(articleInformations.get(i).getSupplier().equals(articleInformation.getSupplier()))
            {
                find = true;
                articleInformations.get(i).setStock(articleInformation.getStock());
                articleInformations.get(i).setPrice(articleInformation.getPrice());
            }
        }
        if(!find)
            articleInformations.add(articleInformation);
    }
    /*
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

 */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id.equals(article.id) &&
                name.equals(article.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
