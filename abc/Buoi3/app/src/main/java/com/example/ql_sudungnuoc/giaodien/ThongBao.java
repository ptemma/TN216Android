package com.example.ql_sudungnuoc.giaodien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ql_sudungnuoc.R;

public class ThongBao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);
    }

    public void HienThiThongBao(View view) {
        EditText edtTitle, edtReceiver, edtAddress, edtContent;
        String strTitle, strReceiver, strAddress, strContent, strNotication;
        edtTitle = findViewById(R.id.edt_chude);
        edtReceiver= findViewById(R.id.edt_nguoinhan);
        edtAddress= findViewById(R.id.edt_diachi);
        edtContent= findViewById(R.id.edt_noidung);

        strTitle = edtTitle.getText().toString().trim();
        if(strTitle.length() <= 0){
            edtTitle.requestFocus();
            edtTitle.selectAll();
            Toast.makeText(this, "Chủ đề không được trống", Toast.LENGTH_SHORT).show();
            return;
        }
        strReceiver = edtReceiver.getText().toString().trim();
        if(strReceiver.length() <= 0){
            edtReceiver.requestFocus();
            edtReceiver.selectAll();
            Toast.makeText(this, "Người nhận không được trống", Toast.LENGTH_SHORT).show();
            return;
        }
        strAddress = edtAddress.getText().toString().trim();
        if(strAddress.length() <= 0){
            edtAddress.requestFocus();
            edtAddress.selectAll();
            Toast.makeText(this, "Địa chỉ không được trống", Toast.LENGTH_SHORT).show();
            return;
        }
        strContent = edtContent.getText().toString().trim();
        if(strContent.length() <= 0){
            edtContent.requestFocus();
            edtContent.selectAll();
            Toast.makeText(this, "Nội dung không được trống", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder thongBao = new AlertDialog.Builder(this);
        thongBao.setIcon(R.mipmap.send);
        thongBao.setTitle("Gửi thông báo");
        thongBao.setPositiveButton("Agree",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ThongBao.this, "Bạn vừa gửi thông báo", Toast.LENGTH_SHORT).show();
                    }
                });
        thongBao.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ThongBao.this, "Bạn hủy bỏ thông báo", Toast.LENGTH_SHORT).show();
                dialogInterface.cancel();
            }
        });
        strNotication = "Chủ đề: " + strTitle + "\n";
        strNotication += "Người nhận: " + strReceiver + "\n";
        strNotication += "Địa chỉ: " + strAddress + "\n";
        strNotication += "Nội dung: " + strContent;
        thongBao.setMessage(strNotication);
        thongBao.create().show();
    }

    public void DongActivity(View view) {
        finish();
    }
}