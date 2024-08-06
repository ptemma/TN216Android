package com.thud.chonmuativi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    String[] arrNames = { "Samsung", "Sharp", "Sony", "Toshiba" };
    Integer[] arrImages = { R.mipmap.tivi_samsung, R.mipmap.tivi_sharp, R.mipmap.tivi_sony, R.mipmap.tivi_toshiba };

    String strName, strDate;
    ImageView imgTivi;
    private TextView txtDate;
    private DatePickerDialog.OnDateSetListener myDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.basicicon);

        Spinner spinner = findViewById(R.id.spn_name);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrNames);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new ChonPhanTu());

        imgTivi = findViewById(R.id.img_tivi);
        txtDate = (TextView) findViewById(R.id.txt_date);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        strDate = dateFormat.format(calendar.getTime());
        txtDate.setText(strDate);
    }

    private class ChonPhanTu implements android.widget.AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            strName= arrNames[position];
            imgTivi.setImageResource(arrImages[position]);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            strName="";
        }
    }

    public void ChonNgay(View view){
        Calendar calendar = Calendar.getInstance();
        int intday = calendar.get(Calendar.DAY_OF_MONTH);
        int intmonth = calendar.get(Calendar.MONTH);
        int intyear = calendar.get(Calendar.YEAR);

        myDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                strDate = dayOfMonth + "/" + month + "/" + year;
                txtDate.setText(strDate);
            }
        };
        DatePickerDialog dialog = new DatePickerDialog(this,
                myDateSetListener, intyear, intmonth, intday);
        dialog.setTitle("Chọn ngày thực hiện");
        dialog.show();
    }

    public void ChonMua(View view) {
        CheckBox chkHd, chk3d, chkSmart;
        String strFunction, strSize, strMsg;
        RadioGroup grpSize;
        grpSize = findViewById(R.id.grp_size);
        int id = grpSize.getCheckedRadioButtonId();
        RadioButton rad = findViewById(id);
        strSize = rad.getText().toString().trim();
        chkHd = findViewById(R.id.chk_hd);
        chk3d = findViewById(R.id.chk_3d);
        chkSmart = findViewById(R.id.chk_smart);
        strFunction = "";
        if (chkHd.isChecked())
            strFunction += chkHd.getText() + "\n";
        if (chk3d.isChecked())
            strFunction += chk3d.getText() + "\n";
        if (chkSmart.isChecked()) {
            strFunction += chkSmart.getText();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin đã chọn");
        builder.setPositiveButton("Đóng",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        strMsg = "Thương hiệu: " + strName + "\n";
        strMsg += "Chức năng: " + strFunction + "\n";
        strMsg += "Kích thước: " + strSize + "\n";
        strMsg += "Ngày giao: " + strDate + "\n";
        builder.setMessage(strMsg);
        builder.create().show();
    }

    public void DongActivity(View view) {
        finish();
    }

}