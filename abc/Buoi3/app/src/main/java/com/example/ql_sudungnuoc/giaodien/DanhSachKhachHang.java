package com.example.ql_sudungnuoc.giaodien;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ql_sudungnuoc.R;
import com.example.ql_sudungnuoc.dulieu.KhachHang;
import com.example.ql_sudungnuoc.xuly.KhachHangAdapter;
import com.example.ql_sudungnuoc.xuly.KhachHangArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class DanhSachKhachHang extends AppCompatActivity {

    KhachHangAdapter khAdapter;
    KhachHangArrayAdapter khArrayAdapter;
    List<KhachHang> lstKhachHang;
    ListView lvKhachHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_khach_hang);
        khAdapter = new KhachHangAdapter(this);
        lvKhachHang = findViewById(R.id.lst_khachhang);
        lstKhachHang = khAdapter.getAllKhachHang();
        khArrayAdapter = new KhachHangArrayAdapter(this,
                R.layout.danhsach_khachhang_list,
                (ArrayList<KhachHang>) lstKhachHang);
        lvKhachHang.setAdapter(khArrayAdapter);
    }
    public void ThemKhachHang(View view){
        KhachHang khachHang;
        khachHang = new KhachHang(1, "Nguyễn Phước Trọng", "0202023332", "Hộ nghèo", "Chuyển khoản", 10);
        if (khAdapter.KtraKH(khachHang.getMskh())){
            Toast.makeText(this, "Khách hàng đã được thêm trước đó", Toast.LENGTH_SHORT).show();

        }
        else khAdapter.insertKhachHang(khachHang);

        khachHang = new KhachHang(3, "Nguyễn Gia Linh",  "0908000901",
                "Sản xuất",
                "Chuyển khoản",
                5);
        if (khAdapter.KtraKH(khachHang.getMskh())){
            Toast.makeText(this, "Khách hàng đã được thêm trước đó", Toast.LENGTH_SHORT).show();

        }
        else khAdapter.insertKhachHang(khachHang);

        khachHang = new KhachHang(4, "Nguyễn Thị Cẩm Tiên",
                "Hộ bình thường",  "Tiền mặt",
                "0908000902",
                9);
        if (khAdapter.KtraKH(khachHang.getMskh())){
            Toast.makeText(this, "Khách hàng đã được thêm trước đó", Toast.LENGTH_SHORT).show();

        }
        else khAdapter.insertKhachHang(khachHang);

        khArrayAdapter.setKhArray((ArrayList<KhachHang>) khAdapter.getAllKhachHang());
    }

    public void ChinhSuaKhachHang(View view){
        khAdapter.updateKhachHang(2, "Lê",
                "0908000901", "Kinh doanh", "Tiền mặt", 7);
        khArrayAdapter.setKhArray((ArrayList<KhachHang>)
                khAdapter.getAllKhachHang());
    }

    public void XoaKhachHang(View view){
        khAdapter.deleteKhachHang(1);
        khArrayAdapter.setKhArray((ArrayList<KhachHang>)
                khAdapter.getAllKhachHang());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        khAdapter.clode();
    }
}
