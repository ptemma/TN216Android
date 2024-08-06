package com.example.ql_sudungnuoc.xuly;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context Context;
    private Integer[] Images;

    public ImageAdapter(android.content.Context context, Integer[] images) {
        Context = context;
        Images = images;
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
    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        ImageView imageView = new ImageView(Context);
        imageView.setImageResource(Images[i]);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setLayoutParams(
                new GridView.LayoutParams(360, 360));
        imageView.setPadding(10, 30, 10, 30);
        return imageView;
    }
}
