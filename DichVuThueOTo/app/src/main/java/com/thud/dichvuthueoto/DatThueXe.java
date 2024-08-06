package com.thud.dichvuthueoto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class DatThueXe extends AppCompatActivity {
    String strHoten, strDienThoai, strThanhToan;
    EditText edtHoTen, edtSDT;
    TextInputLayout layoutHoten, layoutSDT;
    ActivityResultLauncher<Intent> runActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_thue_xe);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.car_2);

        edtHoTen= findViewById(R.id.edt_hotenkh);
        edtSDT = findViewById(R.id.edt_dienthoai);
        layoutHoten = findViewById(R.id.layout_hotenkh);
        layoutSDT = findViewById(R.id.layout_dienthoai);

        Intent intent = getIntent();
        Bundle bundleNhan = intent.getExtras();

        String strLoaiXe, strNgayBD, strThongTin;
        int dongia, songay, sotien;
        boolean thuetaixe;

        strNgayBD = bundleNhan.getString("NgayBD", "");
        strLoaiXe = bundleNhan.getString("LoaiXe", "");
        dongia = bundleNhan.getInt("DonGia", 0);
        songay = bundleNhan.getInt("SoNgay", 0);
        sotien = bundleNhan.getInt("SoTien", 0);
        thuetaixe = bundleNhan.getBoolean("ThueTaiXe", false);
        strThongTin = "Thông tin đặt thuê xe:";
        strThongTin += "\n   Loại xe: " + strLoaiXe;
        strThongTin += "\n   Đơn giá: " + dongia;
        strThongTin += "\n   Ngày bắt đầu: " + strNgayBD;
        strThongTin += "\n   Số ngày thuê: " + songay;
        if (thuetaixe)
            strThongTin += "\n   Thuê tài xế: Có";
        else
            strThongTin += "\n   Thuê tài xế: Không";
        strThongTin += "\n   Số tiền: " + sotien;

        TextView txtThongTin = findViewById(R.id.txt_thongtin);
        txtThongTin.setText(strThongTin);

        runActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if(o.getResultCode() == Activity.RESULT_OK){
                            Toast.makeText(DatThueXe.this, "Đồng ý thuê!", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(DatThueXe.this, "Không đồng ý!", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                }
        );
    }
    public void XacNhanThueXe(View view){
        strHoten =edtHoTen.getText().toString().trim();
        strDienThoai = edtSDT.getText().toString().trim();
        if(strHoten.isEmpty()){
            layoutHoten.setError("Bạn chưa nhập họ tên!");
            edtHoTen.requestFocus();
            return;
        }
        else layoutHoten.setError(null);
        if(strDienThoai.isEmpty()){
            layoutSDT.setError("Bạn chưa nhập số điện thoại");
            edtSDT.requestFocus();
            return;
        }
        else layoutSDT.setError(null);

        RadioGroup radioGroup = findViewById(R.id.grp_thanhtoan);
        int id = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(id);
        strThanhToan = radioButton.getText().toString();

        Bundle bundleGui= new Bundle();
        bundleGui.putString("Hoten", strHoten);
        bundleGui.putString("DienThoai", strDienThoai);
        bundleGui.putString("ThanhToan", strThanhToan);
        Intent intent = new Intent(this, XacNhanThueXe.class);
        intent.putExtras(bundleGui);
        runActivity.launch(intent);
    }
    public void HuyBo(View view){
        finish();
    }
}