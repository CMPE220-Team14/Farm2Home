package com.android.cmpe220.farm2home.demo.model;

public class ConsumerCartItem {
    private ConsumerProduct product;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ConsumerProduct getProduct() {
        return product;
    }

    public void setProduct(ConsumerProduct product) {
        this.product = product;
    }

}
