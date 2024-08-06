package com.thud.homebuild.dulieu;

public class Item {
    private int itemId;
    private int cartId;
    private String itemName;
    private Float price;
    private String itemImageUrl;
    private int itemUserId;

    public Item(){

    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getItemUserId() {
        return itemUserId;
    }

    public void setItemUserId(int itemUserId) {
        this.itemUserId = itemUserId;
    }

}
