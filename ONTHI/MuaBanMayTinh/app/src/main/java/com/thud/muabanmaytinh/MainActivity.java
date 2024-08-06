package com.thud.muabanmaytinh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_XAC_NHAN_MUA = 1;
    Integer[] arrImages = {R.mipmap.tivi_samsung, R.mipmap.tivi_sony, R.mipmap.tivi_sharp, R.mipmap.tivi_toshiba};
    String[] arrNames = {"Samsung", "Sony", "Sharp", "Toshiba"};
    int arrDonGia[] = {10000, 20000, 12000, 24000};
    Spinner spnHangSanXuat;
    String strHangSanXuat;
    int intDonGia, intSoTien, sothang;
    EditText edtDonGia, edtSoTien, edtSothang;
    CheckBox chkPhuKien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setIcon(R.mipmap.ic_logo);
        myActionBar.setDisplayShowHomeEnabled(true);

        edtDonGia = findViewById(R.id.edt_dongia);
        edtSoTien = findViewById(R.id.edt_sotien);
        spnHangSanXuat = findViewById(R.id.spn_hangsanxuat);
        CheckBox chkLoa = findViewById(R.id.chk_loa);
        CheckBox chkWebcam = findViewById(R.id.chk_webcam);
        CheckBox chkOnAp = findViewById(R.id.chk_onap);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arrNames);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnHangSanXuat.setAdapter(adapter);
        spnHangSanXuat.setOnItemSelectedListener(new ChonHangSanXuat());

        RadioGroup grpLoaiMayTinh = findViewById(R.id.grp_maytinh);
        grpLoaiMayTinh.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TinhTien();
            }
        });
        chkLoa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TinhTien();
            }
        });
        chkWebcam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TinhTien();
            }
        });
        chkOnAp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TinhTien();
            }
        });
    }

    private class ChonHangSanXuat implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            strHangSanXuat= arrNames[position];
            intDonGia = arrDonGia[position];
            edtDonGia.setText("Đơn giá: " + intDonGia);
            TinhTien();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            strHangSanXuat= arrNames[0];
            intDonGia =0;
        }
    }

    public void TinhTien(){
        RadioGroup grpLoaiMayTinh = findViewById(R.id.grp_maytinh);
        int id = grpLoaiMayTinh.getCheckedRadioButtonId();
        RadioButton rad = findViewById(id);
        CheckBox chkLoa, chkWebcam, chkOnAp;
        chkLoa = findViewById(R.id.chk_loa);
        chkWebcam = findViewById(R.id.chk_webcam);
        chkOnAp = findViewById(R.id.chk_onap);

        edtSothang = findViewById(R.id.so)
        intSoTien = intDonGia;
        if(rad.getText().toString().equals("Desktop")){
            intSoTien = (int) (intDonGia *1.2);
        }
        if(rad.getText().toString().equals(("Laptop"))){
            intSoTien = intDonGia;
        }
        if(chkLoa.isChecked()) intSoTien += 120;
        if(chkWebcam.isChecked()) intSoTien += 150;
        if(chkOnAp.isChecked()) intSoTien += 100;
        edtSoTien.setText("Số tiền :" + intSoTien);
    }
    public void XacNhan(View view){
        RadioGroup grpLoaiMayTinh = findViewById(R.id.grp_maytinh);
        int id = grpLoaiMayTinh.getCheckedRadioButtonId();
        RadioButton rad = findViewById(id);
        Bundle bundleGoi = new Bundle();
        bundleGoi.putString("HangSanXuat", strHangSanXuat);

        if(rad !=null){
            String loaiMayTinh = rad.getText().toString();
            bundleGoi.putString("LoaiMayTinh", loaiMayTinh);
        }
        bundleGoi.putInt("SoTien", intSoTien);
        Intent intent = new Intent(this, XacNhanMua.class);
        intent.putExtras(bundleGoi);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_XAC_NHAN_MUA) {
            if (resultCode == RESULT_OK && data != null) {
                boolean xacNhanMua = data.getBooleanExtra("xacNhanMua", false);
                if (xacNhanMua) {
                    Toast.makeText(this, "Người dùng đã xác nhận mua!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Người dùng đã hủy!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Đã có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void Dong(View view){
        finish();
    }
}