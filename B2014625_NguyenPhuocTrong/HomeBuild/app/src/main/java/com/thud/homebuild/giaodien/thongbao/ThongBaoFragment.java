package com.thud.homebuild.giaodien.thongbao;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.thud.homebuild.R;

public class ThongBaoFragment extends Fragment {


    public ThongBaoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_thong_bao, container, false);
        SharedPreferences itemRefs = getActivity().getSharedPreferences("infoItem", Context.MODE_PRIVATE);

        String name = itemRefs.getString("name", "");
        String time = itemRefs.getString("time", "");
        Float price = itemRefs.getFloat("price", 0);
        TextView txtThongbao = view.findViewById(R.id.txt_thongbao);
        String strThongTin;
        strThongTin = "Thông tin mua hàng:";
        strThongTin += "\n   Nội thất: " + name;
        strThongTin += "\n   Đơn giá: " + price;
        strThongTin += "\n   Vào lúc: " + time;
        txtThongbao.setText(strThongTin);
        return view;
    }
}