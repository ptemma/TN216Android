package com.example.ql_sudungnuoc.giaodien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.ql_sudungnuoc.R;
import com.example.ql_sudungnuoc.xuly.ImageAdapter;

public class MainActivity_GridView extends AppCompatActivity {

    private Integer[] Images = { R.mipmap.nhanvien, R.mipmap.thongbao,
            R.mipmap.khachhang, R.mipmap.dangnhap, R.mipmap.thoat };
    Class[] arrClasses = { ThongTinNhanVien.class, ThongBao.class,
            DanhSachKhachHang.class, DangNhap.class };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid_view);

        GridView dgvMenu = findViewById(R.id.dgv_menu);
        ImageAdapter imageAdapter = new ImageAdapter(this, Images);
        dgvMenu.setAdapter(imageAdapter);
        ;
        dgvMenu.setOnItemClickListener(new ChonCongViec());

    }

    private class ChonCongViec implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if (i == 4) {
                finish();
                ;
            } else {
                Intent intent = new Intent(MainActivity_GridView.this, arrClasses[i]);
                startActivity(intent);
            }
        }

    }
}