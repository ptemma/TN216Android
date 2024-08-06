package com.thud.homebuild.dulieu;

public class CartItem {
    private int id;
    private int cartId;
    private int itemId;

    // Constructor
    public CartItem(int id, int cartId, int itemId) {
        this.id = id;
        this.cartId = cartId;
        this.itemId = itemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
