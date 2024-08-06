package com.example.ql_sudungnuoc.giaodien;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ql_sudungnuoc.R;
import com.example.ql_sudungnuoc.dulieu.KhachHang;
import com.example.ql_sudungnuoc.xuly.KhachHangAdapter;

import java.util.List;

public class ThongTinKhachHang extends AppCompatActivity {
    KhachHangAdapter khachHangAdapter;
    KhachHang khachHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        khachHangAdapter = new KhachHangAdapter(this);
        hienThiKH();
    }
    public void hienThiKH(){
        List<KhachHang> khachHangs = khachHangAdapter.getAllKhachHang();
        TextView txtThongTin = findViewById(R.id.txt_thongtin);
        String strThongTin = "Thông tin khách hàng";
        int size = khachHangs.size();
        if(size>0){
            for(int i=0; i<size; i++) {
                khachHang = khachHangs.get(i);
                strThongTin += "\n\n Họ tên: " + khachHang.getHoten();
                strThongTin += "\n Số điện thoại: " +
                        khachHang.getDienthoai();
                strThongTin += "\n Đối tượng sử dụng: " +
                        khachHang.getDoituong();
                strThongTin += "\n Hình thức thanh toán: " +
                        khachHang.getThanhtoan();
                strThongTin += "\n Khu vực: " + khachHang.getKhuvuc();
            }
            txtThongTin.setText(strThongTin);
        }
        else
            txtThongTin.setText("Chưa có khách hàng trong CSDL");
    }
    public void ThemKhachHang(View view){
        khachHang = new KhachHang(1, " Nguyễn Văn Hùng", "0908000900",
                " Hộ nghèo", "Chuyển khoản", 10);
        if(khachHangAdapter.KtraKH(khachHang.getMskh()))
            Toast.makeText(this, "Khách hàng đã có trong CSDL",
                    Toast.LENGTH_SHORT).show();
        else
            khachHangAdapter.insertKhachHang(khachHang);
        khachHang = new KhachHang(2, "Lê Thị Nhung", "0908000901",
                "Sản xuất", "Chuyển khoản", 5);
        if(khachHangAdapter.KtraKH(khachHang.getMskh()))
            Toast.makeText(this, "Khách hàng đã có trong CSDL",
                    Toast.LENGTH_SHORT).show();
        else
            khachHangAdapter.insertKhachHang(khachHang);
        hienThiKH();
    }
    public void ChinhSuaKhachHang(View view){
        khachHangAdapter.updateKhachHang(2, "Lê Thị Nhung", "0908000901",
                "Kinh doanh", "Chuyển khoản", 7);
        hienThiKH();
    }

    public void XoaKhachHang(View view){
        khachHangAdapter.deleteKhachHang(1);
        hienThiKH();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        khachHangAdapter.clode();
    }
}