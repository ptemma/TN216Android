package com.thud.b2014625_nguyenphuoctrong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    String[] arrSonguoi= {"2 người", "4 người", "6 người", "8 người"};
    TextInputLayout layout_SoThang;
    TextView txt_DonGia, txt_ThanhTien;
    Spinner spnSonguoi;
    String strSoNguoi;
    int dongia, thanhtien;
    EditText edtSoThang;
    int sothang=1;
    View view;
    int arrDonGia[] = {1000, 800, 600, 400};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setIcon(R.mipmap.goodluck);
        myActionBar.setDisplayShowHomeEnabled(true);
        spnSonguoi = findViewById(R.id.spn_songuoi);
        edtSoThang = findViewById(R.id.edt_sothang);
        txt_DonGia = findViewById(R.id.txt_dongia);
        txt_ThanhTien = findViewById(R.id.txt_thanhtien);
        layout_SoThang = findViewById(R.id.layout_sothang);
        edtSoThang= findViewById(R.id.edt_sothang);
        edtSoThang.setText("1");
        CheckBox sudungbep = findViewById(R.id.chk_cosudungbep);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arrSonguoi);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnSonguoi.setAdapter(adapter);
        spnSonguoi.setOnItemSelectedListener(new ChonSoNguoi());
        RadioGroup grpkhu = findViewById(R.id.grp_khu);
        grpkhu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TinhTien(view);
            }
        });
        sudungbep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TinhTien(view);
            }
        });
    }

    private class ChonSoNguoi implements android.widget.AdapterView.OnItemSelectedListener {
        @NonNull
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            strSoNguoi= arrSonguoi[position];
            dongia = arrDonGia[position];
            txt_DonGia.setText("Đơn giá: " + dongia);
            TinhTien(view);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            strSoNguoi= arrSonguoi[0];
            dongia =0;
        }
    }
    public void TinhTien(View view){

        RadioGroup grpKhu = findViewById(R.id.grp_khu);
        int id = grpKhu.getCheckedRadioButtonId();
        RadioButton rad = findViewById(id);
        CheckBox chkcosudungbep;
        chkcosudungbep = findViewById(R.id.chk_cosudungbep);
        sothang = Integer.parseInt(edtSoThang.getText().toString());
        thanhtien = dongia;
        if(sothang > 12 || sothang<1){
            layout_SoThang.setError("Số tháng phải từ 1 đến 12");
            edtSoThang.requestFocus();
            return;
        }else layout_SoThang.setError(null);

        if(rad.getText().toString().equals("Khu B")){
            thanhtien = (int) (dongia *1.2 * sothang);
        }
        if(rad.getText().toString().equals(("Khu A"))){
            thanhtien = dongia * sothang;
        }
        if(chkcosudungbep.isChecked()) thanhtien += 50;
        txt_ThanhTien.setText("Số tiền :" + thanhtien);
    }
    public void Dong(View view){
        Toast.makeText(this, "Thanhk you!", Toast.LENGTH_SHORT).show();
        finish();;
    }
}