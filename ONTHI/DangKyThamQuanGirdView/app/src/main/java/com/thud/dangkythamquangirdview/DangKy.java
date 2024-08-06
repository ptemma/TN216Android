package com.thud.dangkythamquangirdview;


import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class DangKy extends AppCompatActivity {

    TextInputLayout layout_hoten, layout_email, layout_soluong, layout_songay;
    TextInputEditText edt_hoten,edt_email,edt_soluong, edt_songay;
    TextView txt_ngaybatdau;
    int songay, soluong;
    String hoten, email, ngaybatdau;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setIcon(R.mipmap.ic_logo);
        myActionBar.setDisplayShowHomeEnabled(true);
        layout_hoten = findViewById(R.id.layout_hoten);
        layout_email = findViewById(R.id.layout_email);
        layout_soluong = findViewById(R.id.layout_soluongkhach);
        layout_songay = findViewById(R.id.layout_songay);
        edt_hoten = findViewById(R.id.edt_hoten);
        edt_email = findViewById(R.id.edt_email);
        edt_soluong = findViewById(R.id.edt_soluongkhach);
        edt_songay = findViewById(R.id.edt_songay);
        txt_ngaybatdau = findViewById(R.id.txt_ngaybd);

        txt_ngaybatdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String formattedDate = formatDate(selectedYear, selectedMonth, selectedDay);
                    txt_ngaybatdau.setText(formattedDate);
                },
                year,
                month,
                dayOfMonth
        );

        datePickerDialog.show();
    }
    private String formatDate(int year, int month, int day) {
        return String.format("%02d/%02d/%04d", day, month + 1, year);
    }

    public void DangKy(View view){
        setResult(RESULT_OK);
        finish();
    }
    public void Huy(View view){
        setResult(RESULT_CANCELED);
        finish();
    }
}