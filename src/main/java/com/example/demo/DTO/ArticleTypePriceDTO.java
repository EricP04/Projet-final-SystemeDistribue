package com.example.demo.DTO;

import java.io.Serializable;

public class ArticleTypePriceDTO implements Serializable {

    double price;
    String type;
    int count;

    public ArticleTypePriceDTO()
    {}

    public ArticleTypePriceDTO(double price, String type,int count) {
        this.price = price;
        this.type = type;
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
