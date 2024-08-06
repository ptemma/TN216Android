package com.example.ql_sudungnuoc.giaodien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ql_sudungnuoc.R;

public class ThongTinNhanVien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_nhan_vien);
    }

    public void LuuNhanVien(View view) {
        EditText edtTaiKhoan , edtMatKhau, edtQuyenSD, edtDienThoai;
        String strTaiKhoan, strMatKhau, strQuyenSD, strDienThoai,
                strThongTinNhanVien;

        edtTaiKhoan = findViewById(R.id.edt_taikhoan);
        strTaiKhoan = edtTaiKhoan.getText().toString().trim();
        if(strTaiKhoan.length() <1 ){
            edtTaiKhoan.requestFocus();
            edtTaiKhoan.selectAll();
            Toast.makeText(this, "Tên đăng nhập không được trống", Toast.LENGTH_SHORT).show();
            return;
        }

        edtMatKhau = findViewById(R.id.edt_matkhau);
        strMatKhau = edtMatKhau.getText().toString().trim();
        if(strMatKhau.length() <1 ){
            edtMatKhau.requestFocus();
            edtMatKhau.selectAll();
            Toast.makeText(this, "Mật khẩu không được trống", Toast.LENGTH_SHORT).show();
            return;
        }

        edtQuyenSD = findViewById(R.id.edt_quyensd);
        strQuyenSD = edtQuyenSD.getText().toString().trim();
        if(strQuyenSD.length() <1){
            edtQuyenSD.requestFocus();
            edtQuyenSD.selectAll();
            Toast.makeText(this, "Quyền sử dụng không được trống", Toast.LENGTH_SHORT).show();
            return;
        }
        edtDienThoai = findViewById(R.id.edt_dienthoai);
        strDienThoai = edtDienThoai.getText().toString().trim();
        if(strDienThoai.length() <1){
            edtDienThoai.requestFocus();
            edtMatKhau.selectAll();
            Toast.makeText(this, "Điện thoại không được trống", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder bdrThongBao = new AlertDialog.Builder(this);
        bdrThongBao.setTitle("Thông tin nhân viên");
        bdrThongBao.setIcon(R.mipmap.user);
        bdrThongBao.setPositiveButton("Đồng ý",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ThongTinNhanVien.this, "Đồng ý", Toast.LENGTH_SHORT).show();

                    }
                });
        bdrThongBao.setNegativeButton("Hủy bỏ",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ThongTinNhanVien.this, "Hủy bỏ", Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                });
        strThongTinNhanVien = "Tên đăng nhập: " + strTaiKhoan + "\n"
                + "Mật Khẩu: " + strMatKhau + "\n"
                + "Quyền sử dụng: " + strQuyenSD + "\n"
                + "Điện thoại: " + strDienThoai;
        bdrThongBao.setMessage(strThongTinNhanVien);
        bdrThongBao.create().show();
    }
}