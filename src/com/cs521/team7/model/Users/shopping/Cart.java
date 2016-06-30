package com.cs521.team7.model.Users.shopping;

import java.util.ArrayList;

/**
 * Created by Acer on 6/29/2016.
 */
public class Cart {
    ArrayList<CartItem> productList;
    public Cart()
    {
        productList=new ArrayList<CartItem>();
    }
    public void addProductToCart(CartItem item)
    {
        productList.add(item);
    }

    public ArrayList<CartItem> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<CartItem> productList) {
        this.productList = productList;
    }
    public int getTotalItems()
    {
        int numberOfItems=0;
        for(CartItem item:this.productList)
        {
            numberOfItems+=item.getQuantity();
        }
        return numberOfItems;
    }
    public float getTotalCartPrice()
    {
        float total=0;
        for(CartItem item:this.productList)
        {
            total=total+(item.getQuantity()*item.getProductPrice());
        }
        return total;
    }

    @Override
    public String toString() {
        String tostr=null;
        for(CartItem item: productList)
        {
            tostr=tostr+"\n"+item.toString();
        }
        return tostr;
    }
}
