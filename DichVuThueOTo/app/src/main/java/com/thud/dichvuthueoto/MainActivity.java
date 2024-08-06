package com.thud.dichvuthueoto;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    String arrLoaiXe[] = { "Xe 4 chỗ", "7 chỗ", "16 chỗ", "29 chỗ", "45 chỗ" };
    int arrDonGia[] = { 120, 130, 150, 170, 200 };

    Spinner spnLoaiXe;
    String strLoaiXe, strNgayBD;
    int intDonGia, intSoNgay, SoTien;
    boolean blnthueTaiXe;
    EditText edtDonGia, edtSoTien;
    TextView txtNgayBD;
    TextInputLayout layoutSoNgay;
    TextInputEditText edtSoNgay;
    CheckBox chkThueTaiXe;
    DatePickerDialog.OnDateSetListener dateSetListener;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, Media_UnBoundService.class);
        startService(intent);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.car_2);

        edtDonGia = findViewById(R.id.edt_dongia);
        spnLoaiXe = findViewById(R.id.spn_loaixe);
        txtNgayBD = findViewById(R.id.txt_ngaybd);
        edtSoNgay = findViewById(R.id.edt_songay);
        layoutSoNgay = findViewById(R.id.layout_songay);
        chkThueTaiXe = findViewById(R.id.chk_taixe);
        edtSoTien = findViewById(R.id.edt_sotien);

        ArrayAdapter<String> loaiXeAdp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                arrLoaiXe);
        loaiXeAdp.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnLoaiXe.setAdapter(loaiXeAdp);
        spnLoaiXe.setOnItemSelectedListener(new ChonLoaiXe());
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        strNgayBD = dateFormat.format(calendar.getTime());
        txtNgayBD.setText("Ngày bắt đầu: " + strNgayBD);
    }

    private class ChonLoaiXe implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            strLoaiXe = arrLoaiXe[position];
            intDonGia = arrDonGia[position];
            edtDonGia.setText("Đơn giá: " + intDonGia);
            ;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            strLoaiXe = arrLoaiXe[0];
            intDonGia = 0;
        }
    }

    public void ChonNgay(View view) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                strNgayBD = dayOfMonth + "/" + month + "/" + year;
                txtNgayBD.setText("Ngày bắt đầu: " + strNgayBD);
            }
        };
        DatePickerDialog dialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        dialog.setTitle("Chọn ngày thuê xe");
        dialog.show();
    }

    public boolean TinhTien(View view) {
        String soNgayThue = edtSoNgay.getText().toString().trim();
        if (soNgayThue.length() <= 0 || Integer.parseInt(soNgayThue) == 0) {
            layoutSoNgay.setError("Lỗi nhập số ngày thuê");
            edtSoNgay.requestFocus();
            return false;
        } else {
            layoutSoNgay.setError(null);
            intSoNgay = Integer.parseInt(soNgayThue);
        }
        SoTien = intSoNgay * intDonGia;
        blnthueTaiXe = chkThueTaiXe.isChecked();
        if (blnthueTaiXe) {
            SoTien = SoTien + intSoNgay * 30;
        }
        edtSoTien.setText("Số tiền: " + SoTien);
        return true;
    }

    public void DatThue(View view) {
        if (!TinhTien(view)) {
            return;
        }
        Bundle bundleGoi = new Bundle();
        bundleGoi.putString("LoaiXe", strLoaiXe);
        bundleGoi.putInt("DonGia", intDonGia);
        bundleGoi.putString("NgayBD", strNgayBD);
        bundleGoi.putInt("SoNgay", intSoNgay);
        bundleGoi.putBoolean("ThueTaiXe", blnthueTaiXe);
        bundleGoi.putInt("SoTien", SoTien);
        Intent intent = new Intent(this, DatThueXe.class);
        intent.putExtras(bundleGoi);
        startActivity(intent);
    }

    public void DongActivity(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        stopService(intent);
        super.onDestroy();
    }
}