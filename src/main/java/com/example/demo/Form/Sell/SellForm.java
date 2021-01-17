package com.example.demo.Form.Sell;

public class SellForm {

    private String emailSupplier;
    private String name;
    private double price;
    private String type;
    private int stock;

    public SellForm() {
    }

    public SellForm(String emailSupplier) {
        this.emailSupplier = emailSupplier;
    }



    public SellForm(String emailSupplier, String name, double price, String type, int stock) {
        this.emailSupplier = emailSupplier;
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmailSupplier() {
        return emailSupplier;
    }

    public void setEmailSupplier(String emailSupplier) {
        this.emailSupplier = emailSupplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
