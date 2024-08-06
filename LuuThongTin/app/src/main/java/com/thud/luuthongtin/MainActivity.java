package com.thud.luuthongtin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pfrThongTin;
    TextInputEditText edtHoTen, edtCapDo, edtDiem;
    TextInputLayout layoutHoTen, layoutCapDo, layoutDiem;
    String ten;
    int capdo;
    float diem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtHoTen = findViewById(R.id.edt_hoten);
        edtCapDo = findViewById(R.id.edt_capdo);
        edtDiem = findViewById(R.id.edt_diem);
        layoutHoTen = findViewById(R.id.layout_hoten);
        layoutCapDo = findViewById(R.id.layout_capdo);
        layoutDiem = findViewById(R.id.layout_diem);

        pfrThongTin = getSharedPreferences("ttHienTai", MODE_PRIVATE);
        ten = pfrThongTin.getString("TEN", "");
        capdo = pfrThongTin.getInt("CAP_DO", 1);
        diem = pfrThongTin.getFloat("DIEM", 0);

        edtHoTen.setText(ten);
        edtCapDo.setText("" + capdo);
        edtDiem.setText("" + diem);
    }
    public void LuuThongTin(View view){
        ten = edtHoTen.getText().toString().trim();
        if(ten.length() == 0){
            layoutHoTen.setError("Lỗi chưa nhập họ tên");
            edtHoTen.requestFocus();
            return;
        }
        else
            layoutHoTen.setError(null);
        String strCapDo = edtCapDo.getText().toString().trim();
        if(strCapDo.length() == 0 || Integer.parseInt(strCapDo) == 0){
            layoutCapDo.setError("Lỗi nhập cấp độ");
            edtCapDo.requestFocus();
            return;
        }
        else {
            layoutCapDo.setError(null);
            capdo = Integer.parseInt(strCapDo);
        }
        String strDiem = edtDiem.getText().toString().trim();
        if(strDiem.length() == 0 || Float.parseFloat(strDiem) == 0){
            layoutDiem.setError("Lỗi nhập điểm");
            edtDiem.requestFocus();
            return;
        }
        else {
            layoutDiem.setError(null);
            diem = Float.parseFloat(strDiem);
        }
        SharedPreferences.Editor editor = pfrThongTin.edit();
        editor.putString("TEN", ten);
        editor.putInt("CAP_DO", capdo);
        editor.putFloat("DIEM", diem);

        editor.apply();
        finish();
    }

}