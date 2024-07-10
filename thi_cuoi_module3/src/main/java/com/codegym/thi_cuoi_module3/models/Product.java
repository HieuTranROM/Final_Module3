package com.codegym.thi_cuoi_module3.models;

public class Product {
    private int productId;
    private String productName;
    private int price;
    private int discount;
    private int stock_quantity;

    public Product() {
    }

    public Product(int productId, String productName, int price, int discount, int stock_quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.discount = discount;
        this.stock_quantity = stock_quantity;
    }

    public Product(String productName, int price, int discount, int stock_quantity) {
        this.productName = productName;
        this.price = price;
        this.discount = discount;
        this.stock_quantity = stock_quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }
}
