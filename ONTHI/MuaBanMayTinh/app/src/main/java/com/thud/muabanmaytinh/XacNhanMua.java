package com.thud.muabanmaytinh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class XacNhanMua extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_mua);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setIcon(R.mipmap.ic_logo);
        myActionBar.setDisplayShowHomeEnabled(true);
        Bundle bundle = getIntent().getExtras();
        String hangSanXuat = bundle.getString("HangSanXuat");
        String loaiMayTinh = bundle.getString("LoaiMayTinh");
        int soTien = bundle.getInt("SoTien");

        // Hiển thị thông tin bằng Toast
        String message = "Hãng sản xuất: " + hangSanXuat + "\nLoại máy tính: " + loaiMayTinh + "\nSố tiền: " + soTien;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    public void DongY(View view){
        setResult(RESULT_OK);
        finish();
    }
    public void Huy(View view){
        setResult(RESULT_CANCELED);
        finish();
    }
}