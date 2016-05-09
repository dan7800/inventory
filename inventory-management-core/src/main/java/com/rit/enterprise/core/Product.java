package com.rit.enterprise.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    private int product;
    private String name;
    private int amount;
    private double basePrice;
    private double baseCost;

    public Product() {}

    public Product(int product, String name, int amount, double basePrice, double baseCost) {
        this.product = product;
        this.name = name;
        this.amount = amount;
        this.baseCost = baseCost;
        this.basePrice = basePrice;
    }

    @JsonProperty
    public int getProduct() {
        return product;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public int getAmount() {
        return amount;
    }

    @JsonProperty
    public double getBasePrice() {
        return basePrice;
    }

    @JsonProperty
    public double getBaseCost() {
        return baseCost;
    }

}
