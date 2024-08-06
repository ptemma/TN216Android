package com.thud.homebuild.dulieu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "HomeBuild.db";
    private static final int DB_VERSION = 1;
    //table user
    public static final String TABLE_USER = "user";
    public static final String USER_ID = "id";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FULLNAME = "fullname";
    public static final String USER_EMAIL = "email";
    private static final String CREATE_TABLE_USER
            = "Create Table " + TABLE_USER + "("
            + USER_ID + " Integer Primary Key Autoincrement, "
            + USER_PASSWORD + " Text, "
            + USER_FULLNAME + " Text, "
            + USER_EMAIL + " Text);";

    //table cart
    public static final String TABLE_CART = "cart";
    public static final String CART_ID = "id";
    public static final String CART_USER_ID = "userId";

    private static final String CREATE_TABLE_CART
            = "CREATE TABLE " + TABLE_CART + "("
            + CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CART_USER_ID + " INTEGER, "
            + "FOREIGN KEY(" + CART_USER_ID + ") REFERENCES " + TABLE_USER + "(" + USER_ID + "));";

    //table item
    public static final String TABLE_ITEM = "item";
    public static final String ITEM_ID = "id";
    public static final String ITEM_NAME = "itemName";
    public static final String ITEM_PRICE = "itemPrice";
    public static final String ITEM_IMAGE_URL = "itemImageUrl";
    public static final String ITEM_USER_ID = "itemUserId";

    private static final String CREATE_TABLE_ITEM
            = "CREATE TABLE " + TABLE_ITEM + "("
            + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ITEM_NAME + " TEXT, "
            + ITEM_PRICE + " INTEGER, "
            + ITEM_IMAGE_URL + " TEXT, "
            + ITEM_USER_ID + " INTEGER, "
            + "FOREIGN KEY(" + ITEM_USER_ID + ") REFERENCES " + TABLE_USER + "(" + USER_ID + "))";
    //table cart item
    public static final String TABLE_CART_ITEM = "cartItem";
    public static final String CART_ITEM_ID = "cartItemId";
    public static final String ITEM_CART_ID = "itemCartId";
    private static final String CREATE_TABLE_CART_ITEM
            = "CREATE TABLE " + TABLE_CART_ITEM + "("
            + CART_ITEM_ID + " INTEGER, "
            + ITEM_CART_ID + " INTEGER, "
            + "FOREIGN KEY(" + CART_ITEM_ID + ") REFERENCES " + TABLE_CART + "(" + CART_ID + "), "
            + "FOREIGN KEY(" + ITEM_CART_ID + ") REFERENCES " + TABLE_ITEM + "(" + ITEM_ID + "));";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_CART);
        db.execSQL(CREATE_TABLE_ITEM);
        db.execSQL(CREATE_TABLE_CART_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("Drop Table If Exists " + TABLE_USER);
        db.execSQL("Drop Table If Exists " + TABLE_CART);
        db.execSQL("Drop Table If Exists " + TABLE_ITEM);
        db.execSQL("Drop Table If Exists " + TABLE_CART_ITEM);
        onCreate(db);
    }
}
