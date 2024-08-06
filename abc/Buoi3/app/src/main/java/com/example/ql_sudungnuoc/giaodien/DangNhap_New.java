package com.example.ql_sudungnuoc.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ql_sudungnuoc.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class DangNhap_New extends AppCompatActivity {

    TextInputLayout layoutTaiKhoan, layoutMatKhau;
    TextInputEditText editTextTaiKhoan, editTextMatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap_new);

        ActionBar myActionBar = getSupportActionBar();
        if (myActionBar != null){
            myActionBar.setDisplayShowHomeEnabled(true);
            myActionBar.setIcon(R.mipmap.water_2);
        }
        editTextTaiKhoan = findViewById(R.id.edt_taikhoan);
        editTextMatKhau = findViewById(R.id.edt_matkhau);

        layoutTaiKhoan = findViewById(R.id.layout_taikhoan);
        layoutMatKhau = findViewById(R.id.layout_matkhau);
    }
    public void DangNhap(View view){
        String strTaiKhoan, strMatKhau;
        strTaiKhoan = editTextTaiKhoan.getText().toString().trim();
        strMatKhau = editTextMatKhau.getText().toString().trim();

        if(strTaiKhoan.length() ==0){
            layoutTaiKhoan.setError("Lỗi chưa nhập tài khoản");
            layoutTaiKhoan.requestFocus();
            return;
        }
        else layoutTaiKhoan.setError(null);

        if(strMatKhau.length() ==0){
            layoutMatKhau.setError("Lỗi chưa nhập mật khẩu");
            layoutMatKhau.requestFocus();
            return;
        }
        else layoutMatKhau.setError(null);

        if(strTaiKhoan.equals("admin") && strMatKhau.equals("123")){
            layoutMatKhau.setError(null);
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            finish();

            Intent intent = new Intent(this, MainActivity_GridView.class);
            startActivity(intent);
        }
        else if (!strTaiKhoan.equals("admin")){
            Toast.makeText(this, "Tài khoản chưa tồn tại", Toast.LENGTH_SHORT).show();
            layoutTaiKhoan.setError("Tài khoản chưa tồn tại");
        }
        else{
            Toast.makeText(this, "Mật khẩu chưa đúng", Toast.LENGTH_SHORT).show();
            layoutMatKhau.setError("Mật khẩu chưa đúng");
        }
    }
}