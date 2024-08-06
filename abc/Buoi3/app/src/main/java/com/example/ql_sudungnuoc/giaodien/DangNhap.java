package com.example.ql_sudungnuoc.giaodien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ql_sudungnuoc.R;

public class DangNhap extends AppCompatActivity {
    EditText edtDangNhap, edtMatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtDangNhap = findViewById(R.id.edt_taikhoan);
        edtMatKhau= findViewById(R.id.edt_matkhau);

    }
    public void KiemTraNguoiDung(View view){
        String strTaiKhoan, strMatKhau;
        strTaiKhoan = edtDangNhap.getText().toString().trim();
        strMatKhau = edtMatKhau.getText().toString().trim();
        if (strTaiKhoan.length() <=0){
            edtDangNhap.setError("Vui lòng nhập tên đăng nhập");
            edtDangNhap.requestFocus();
            return;
        }
        else {
            edtDangNhap.setError(null);
        }

        if (strMatKhau.length() <=0 ){
            edtMatKhau.setError("Vui lòng nhập mật khẩu");
            edtMatKhau.requestFocus();
            return;
        }
        else {
            edtMatKhau.setError(null);
        }

        if(strTaiKhoan.equals("admin") && strMatKhau.equals("123")){
            edtMatKhau.setError(null);
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            finish();
            Intent intent = new Intent(this, ThongTinNhanVien.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "tài khoản hoặc mật khẩu không đúng!",
                    Toast.LENGTH_LONG).show();
            //edtDangNhap.setError("tài khoản không tồn tại");
            edtMatKhau.setError("tài khoản hoặc mật khẩu không đúng!");

        }
    }

}