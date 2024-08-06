package com.example.ql_sudungnuoc.giaodien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ql_sudungnuoc.R;
import com.example.ql_sudungnuoc.dulieu.KhachHang;
import com.example.ql_sudungnuoc.xuly.ImageTextAdapter;

public class MainActivity_Custom_GridView extends AppCompatActivity {

    private Integer[] Images = {R.mipmap.nhanvien, R.mipmap.thongbao,R.mipmap.khachhang, R.mipmap.dangnhap, R.mipmap.thoat};
    private String[] Texts = {"Nhân viên", "Thông báo","Khách hàng", "Đăng nhập", "Thoát"};
    Class[] arrClasses= {ThongTinNhanVien.class, ThongBao.class, ThongTinKhachHang.class, DangNhap.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_custom_grid_view);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.water);

        GridView menuCustomGridView = findViewById(R.id.dgv_menu_custom);
        ImageTextAdapter imageTextAdapter = new ImageTextAdapter(this,R.layout.custom_gridview_cell, Images, Texts);

        menuCustomGridView.setAdapter(imageTextAdapter);
        menuCustomGridView.setOnItemClickListener(new ChonCongViec());

    }

    private class ChonCongViec implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position==4){
                finish();
            }
            else {
                Intent intent = new Intent(MainActivity_Custom_GridView.this, arrClasses[position]);
                startActivity(intent);
            }
        }
    }
}