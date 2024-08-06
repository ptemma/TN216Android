package com.thud.homebuild.xuly;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thud.homebuild.R;
import com.thud.homebuild.dulieu.Item;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Activity context;
    private int layout_id;
    private Integer[] Images;
    private List<Item> items;

    public ImageAdapter(Activity context, int layout_id, Integer[] images, List<Item> items) {
        this.context = context;
        this.layout_id = layout_id;
        Images = images;
        this.items = items;
    }

    public void setImages(Integer[] images) {
        Images = images;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return Images.length;
    }
    @Override
    public Object getItem(int i) {
        return Images[i];
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    private class Hoder{
        ImageView imageView;
        TextView itemName;
        TextView itemPrice;
        Button btnBuyItem;
        Button btnMua;

        Button btnDelItemFromCart;
        Button btnMuaItemFromCart;

    }

    SharedPreferences itemRef;
    public interface BuyItemClickListener{
        void onBuyItemClick(int position);
    }
    BuyItemClickListener buyItemClickListener;
    public void setBuyItemClickListener(BuyItemClickListener listener) {
        this.buyItemClickListener = listener;
    }

    public interface MuaItemClickListener {
        void onMuaItemClick(int position);
    }

    MuaItemClickListener muaItemClickListener;

    public void setMuaItemClickListener(MuaItemClickListener listener) {
        this.muaItemClickListener = listener;
    }

    public interface MuaItemFromCartClickListener {
        void onMuaItemFromCartClick(int position);
    }

    MuaItemFromCartClickListener muaItemFromCartClickListener;

    public void setMuaItemFromCartClickListener(MuaItemFromCartClickListener listener) {
        this.muaItemFromCartClickListener = listener;
    }

    public interface XoaItemFromCartClickListener {
        void onXoaItemFromCartClick(int position);
    }

    XoaItemFromCartClickListener xoaItemFromCartClickListener;

    public void setXoaItemFromCartClickListener(XoaItemFromCartClickListener listener) {
        this.xoaItemFromCartClickListener = listener;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        LayoutInflater layoutInflater = context.getLayoutInflater();
        view = layoutInflater.inflate(layout_id, viewGroup, false);
        Hoder hoder = new Hoder();
        hoder.itemName = view.findViewById(R.id.txt_nameItem);
        hoder.imageView = view.findViewById(R.id.img_imageItem);
        hoder.itemPrice = view.findViewById(R.id.txt_priceItem);
        hoder.btnBuyItem = view.findViewById(R.id.btn_buyItem);
        hoder.btnMua = view.findViewById(R.id.btn_muaItem);
        hoder.btnMuaItemFromCart= view.findViewById((R.id.btn_muaFromCart));
        hoder.btnDelItemFromCart= view.findViewById(R.id.btn_xoaFromCart);
        view.setTag(hoder);
        hoder.itemName.setText(items.get(i).getItemName());
        hoder.imageView.setImageResource(Images[i]);
        hoder.itemPrice.setText(String.valueOf(items.get(i).getPrice()));
        itemRef = context.getSharedPreferences("item", Context.MODE_PRIVATE);

        hoder.btnBuyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = itemRef.edit();
                editor.putInt("itemId", items.get(i).getItemId());
                Toast.makeText(context, "hello" + items.get(i).getItemId(), Toast.LENGTH_SHORT).show();
                editor.apply();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thêm vào giỏ hàng");
                builder.setMessage("Bạn có muốn thêm \"" + items.get(i).getItemName() + "vào giỏ hàng không?");
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (buyItemClickListener != null) {
                            buyItemClickListener.onBuyItemClick(i);
                        }
                    }
                });
                builder.setNegativeButton("Không thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        hoder.btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (muaItemClickListener != null) {
                    muaItemClickListener.onMuaItemClick(i);
                }
            }
        });

//        hoder.btnDelItemFromCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("Xóa khỏi giỏ hàng");
//                builder.setMessage("Bạn có muốn xóa \"" + items.get(i).getItemName() + "khỏi giỏ hàng không?");
//                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (xoaItemFromCartClickListener != null) {
//                            xoaItemFromCartClickListener.onXoaItemFromCartClick(i);
//                        }
//                    }
//                });
//                builder.setNegativeButton("Không thêm", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });


        return view;
    }
}
