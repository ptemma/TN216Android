package com.thud.dichvuthueoto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class XacNhanThueXe extends AppCompatActivity {
    String dienThoai, thanhToan, hoTen, thongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_thue_xe);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.car_2);

        Intent intent = getIntent();
        Bundle bundleNhan = intent.getExtras();
        dienThoai = bundleNhan.getString("DienThoai", "");
        hoTen = bundleNhan.getString("Hoten", "");
        thanhToan = bundleNhan.getString("ThanhToan", "");
        thongTin = "Họ tên khách hàng: " + hoTen;
        thongTin += "\nSố điện thoại: " + dienThoai;
        thongTin += "\nHình thức thanh toán: " + thanhToan;
        TextView txtKH = findViewById(R.id.txt_khachhang);
        txtKH.setText(thongTin);
    }

    public void DongY(View view) {
        setResult(RESULT_OK);
        finish();
    }

    public void HuyBo(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

}