package com.cs521.team7.model.Users.shopping;

/**
 * Created by Acer on 6/29/2016.
 */
public class CartItem {
    int productID;
    int quantity;
    float productPrice;

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductID() {
        return productID;

    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartItem(int productID, int quantity, float price) {

        this.productID = productID;
        this.quantity = quantity;
        this.productPrice=price;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "productID=" + productID +
                ", quantity=" + quantity +
                ", productPrice=" + productPrice +
                '}';
    }
}
