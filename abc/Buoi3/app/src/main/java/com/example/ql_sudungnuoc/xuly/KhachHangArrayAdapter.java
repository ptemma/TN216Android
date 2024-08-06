package com.example.ql_sudungnuoc.xuly;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ql_sudungnuoc.R;
import com.example.ql_sudungnuoc.dulieu.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangArrayAdapter extends ArrayAdapter<KhachHang> {
    Activity context;
    int layoutId;
    ArrayList<KhachHang> khArray;
    public KhachHangArrayAdapter(Activity context, int layoutId,
                                 ArrayList<KhachHang> khArray) {
        super(context, layoutId, khArray);
        this.context = context;
        this.khArray = khArray;
        this.layoutId = layoutId;
    }
    public void setKhArray(ArrayList<KhachHang> khArray) {
        this.khArray = khArray;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return khArray.size();
    }
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        if(getCount()>0 && position>=0) {
            final KhachHang kh = khArray.get(position);
            final TextView txtSTT =
                    convertView.findViewById(R.id.txt_stt);
            txtSTT.setText(""+ (position + 1));
            final TextView txtHoTen =
                    convertView.findViewById(R.id.txt_hoten);
            txtHoTen.setText(kh.getHoten());
            final ImageView imgThanhToan =
                    convertView.findViewById(R.id.img_thanhtoan);
            if(kh.getThanhtoan().equals("Chuyển khoản"))
                imgThanhToan.setImageResource(R.mipmap.mastercard);
            else
                imgThanhToan.setImageResource(R.mipmap.money);
        }
        return convertView;
    }
}
