package com.thud.homebuild.xuly;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thud.homebuild.R;
import com.thud.homebuild.dulieu.Cart;
import com.thud.homebuild.dulieu.CartItem;
import com.thud.homebuild.dulieu.DbHelper;
import com.thud.homebuild.dulieu.Item;
import com.thud.homebuild.dulieu.User;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter
{

    private DbHelper myDbHelper;
    private SQLiteDatabase db;
    ItemAdapter itemAdapter;
    private String[] allColumns = { DbHelper.CART_ID,
            DbHelper.USER_ID };
    public CartAdapter(Context context) {
        myDbHelper = new DbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public long addCart(int userId) {
        db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.CART_USER_ID, userId);
        return db.insert(DbHelper.TABLE_CART, null, values);
    }
    public Cart getMyCart(int userId){
        Cursor cursor = db.rawQuery("SELECT * FROM cart WHERE userId = ?", new String[]{String.valueOf(userId)});
        if(cursor.moveToFirst()){
            Cart cart = cursorToCart(cursor);
            return cart;
        }
        return null;
    }
    public List<Item> getItems(int cartId) {
        List<Item> itemList = new ArrayList<>();
        SQLiteDatabase db = myDbHelper.getReadableDatabase();

        String query = "SELECT i." + DbHelper.ITEM_ID + ", i." + DbHelper.ITEM_NAME + ", i." + DbHelper.ITEM_PRICE +
                " FROM " + DbHelper.TABLE_ITEM + " i " +
                " JOIN " + DbHelper.TABLE_CART_ITEM + " ci ON i." + DbHelper.ITEM_ID + " = ci." + DbHelper.ITEM_CART_ID +
                " WHERE ci." + DbHelper.CART_ITEM_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(cartId)});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Item item = new Item();
                item.setItemId(cursor.getInt(0));
                item.setItemName(cursor.getString(1));
                item.setPrice(cursor.getFloat(2));
                itemList.add(item);
            }
            cursor.close();
        }

        db.close();
        return itemList;
    }

    public boolean deleteItemFromCart(int cartItemId, int itemCartId) {
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        int deletedRows = db.delete(DbHelper.TABLE_CART_ITEM,
                DbHelper.CART_ITEM_ID + " = ? and " +
                        DbHelper.ITEM_CART_ID + " = ?",

                new String[]{String.valueOf(cartItemId), String.valueOf(itemCartId)});
        db.close();
        return deletedRows > 0;
    }



    private Cart cursorToCart(Cursor cursor){
        Cart values = new Cart();
        values.setCartId(cursor.getInt(0));
        values.setUserId(cursor.getInt(1));
        return values;
    }

    public long addItemToCart(int cartId, int itemId) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.CART_ITEM_ID, cartId);
        values.put(DbHelper.ITEM_CART_ID, itemId);
        long newRowId = db.insert(DbHelper.TABLE_CART_ITEM, null, values);
        return newRowId;
    }

    public void close(){
        db.close();
        myDbHelper.close();
    }


    SharedPreferences itemRef;
    public interface MuaItemClickListener {
        void onMuaItemClick(int position);
    }

    ImageAdapter.MuaItemClickListener muaItemClickListener;

    public void setMuaItemClickListener(ImageAdapter.MuaItemClickListener listener) {
        this.muaItemClickListener = listener;
    }


}
