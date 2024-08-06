package com.thud.homebuild.xuly;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thud.homebuild.dulieu.DbHelper;
import com.thud.homebuild.dulieu.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter {

    private DbHelper myDbHelper;
    private SQLiteDatabase db;
    private String[] allColumns = { DbHelper.USER_ID,
            DbHelper.USER_EMAIL, DbHelper.USER_PASSWORD,
            DbHelper.USER_FULLNAME };

    public UserAdapter(Context context) {
        myDbHelper = new DbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public long addUser(String email, String fullName, String password) {
        db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.USER_EMAIL, email);
        values.put(DbHelper.USER_PASSWORD, password);
        values.put(DbHelper.USER_FULLNAME, fullName);
        return db.insert(DbHelper.TABLE_USER, null, values);
    }

    public int updateUser(int id,
                          String password,
                          String fullname,
                          String email){
        ContentValues values = new ContentValues();
        values.put(DbHelper.USER_EMAIL, email);
        values.put(DbHelper.USER_PASSWORD, password);
        values.put(DbHelper.USER_FULLNAME, fullname);
        return db.update(DbHelper.TABLE_USER, values,
                DbHelper.USER_ID + " = " + id, null);
    }
    public int deleteUser(int id) {
        return db.delete(DbHelper.TABLE_USER,
                DbHelper.USER_ID + " = " + id, null);
    }
    private User cursorToUser(Cursor cursor) {
        User values = new User();
        values.setId(cursor.getInt(0));
        values.setEmail(cursor.getString(3));
        values.setPassword(cursor.getString(1));
        values.setFullname(cursor.getString(2));
        return values;
    }
    public List<User> getAllUser() {
        List<User> users = new ArrayList<User>();
        Cursor cursor = db.query(DbHelper.TABLE_USER,
                allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                User values = cursorToUser(cursor);
                users.add(values);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return users;
    }

    public Boolean checkUser(String email) {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from user WHERE email = ?" ,new String[]{email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //check login
    public Boolean checkLogin(String email, String password){
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE  email = ? AND password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    public User getUser(String email){
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE  email = ?", new String[]{email});
        if(cursor.moveToFirst()){
            User user = cursorToUser(cursor);
            return user;
        }
        return null;
    }

    public void close(){
        db.close();
        myDbHelper.close();
    }
}
