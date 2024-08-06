package com.example.ql_sudungnuoc.xuly;

import android.app.Activity;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ql_sudungnuoc.R;

public class ImageTextAdapter extends BaseAdapter {
    private Activity context;
    private int layout_id;
    private Integer[] images;
    private String[] text;

    public ImageTextAdapter(Activity context, int layout_id, Integer[] images, String[] text) {
        this.context = context;
        this.layout_id = layout_id;
        this.images = images;
        this.text = text;
    }

    public void setDataSources(Integer[] image, String[] txt){
        images=images;
        text=txt;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class Hoder{
        TextView textView;
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        convertView = layoutInflater.inflate(layout_id, parent, false);
        Hoder hoder = new Hoder();
        hoder.textView = convertView.findViewById(R.id.txt_text);
        hoder.imageView = convertView.findViewById(R.id.Img_image);
        convertView.setTag(hoder);;

        hoder.textView.setText(text[position]);
        hoder.imageView.setImageResource(images[position]);

        return convertView;
    }
}
