package com.thud.homebuild.xuly;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.thud.homebuild.R;
import com.thud.homebuild.dulieu.DbHelper;
import com.thud.homebuild.dulieu.Item;
import com.thud.homebuild.dulieu.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    private DbHelper myDbHelper;
    private SQLiteDatabase db;
    private String[] allColumns = { DbHelper.ITEM_ID,
            DbHelper.ITEM_NAME,
            DbHelper.ITEM_PRICE,
            DbHelper.ITEM_IMAGE_URL};

    private Context context;
    private List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
        super(context, 0, items);
        context = context;
        items = items;
    }
    public ItemAdapter(Context context){
        super(context,0);
        myDbHelper = new DbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }
    public long addItem(String itemName, Integer itemPrice, String imageUrl, Integer userId) {
        db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.ITEM_NAME, itemName);
        values.put(DbHelper.ITEM_PRICE,itemPrice);
        values.put(DbHelper.ITEM_IMAGE_URL, imageUrl);
        values.put(DbHelper.ITEM_USER_ID, userId);
        return db.insert(DbHelper.TABLE_ITEM, null, values);
    }

    public int deleteItem(int id) {
        return db.delete(DbHelper.TABLE_ITEM,
                DbHelper.ITEM_ID + " = " + id, null);
    }
    private Item cursorToItem(Cursor cursor) {
        Item values = new Item();
        values.setItemId(cursor.getInt(0));
        values.setItemName(cursor.getString(1));
        values.setPrice(cursor.getFloat(2));
        values.setItemImageUrl(cursor.getString(3));
        return values;
    }

    public List<Item> AllItems() {
        List<Item> items = new ArrayList<Item>();
        Cursor cursor = db.query(DbHelper.TABLE_ITEM,
                allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Item values = cursorToItem(cursor);
                items.add(values);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return items;
    }
    public Boolean checkItem(int itemId) {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from item WHERE id = ?" ,new String[]{String.valueOf(itemId)});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public void close(){
        db.close();
        myDbHelper.close();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Item currentItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_in_cart, parent, false);
        }

        TextView textViewItemName = convertView.findViewById(R.id.txt_itemNameInCart);
        TextView textViewPrice = convertView.findViewById(R.id.txt_priceInCart);
        Button btnMua = convertView.findViewById(R.id.btn_muaFromCart);

        if (currentItem != null) {
            textViewItemName.setText(currentItem.getItemName());
            textViewPrice.setText(String.valueOf(currentItem.getPrice()));
        }
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Mua item: " + currentItem.getItemName(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

}
