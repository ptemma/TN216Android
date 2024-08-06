package com.thud.dangkythamquanlistview;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    String[] diadiem = {"Dinh Độc lập", "Địa đạo Củ Chi", "Nhà tù Côn Đảo"};
    ActivityResultLauncher<Intent> runActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> listThucDon = new ArrayList<>();

        ListView listDiaDiem = findViewById(R.id.lst_diadiem);
        ArrayAdapter<String> adapter;
        for (String location : diadiem) {
            listThucDon.add(location);
        }
        listThucDon.add("Đăng ký tham quan");

            runActivity = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult o) {
                            if(o.getResultCode() == Activity.RESULT_OK){
                                Toast.makeText(MainActivity.this, "Đồng ý thuê!", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(MainActivity.this, "Không đồng ý!", Toast.LENGTH_LONG).show();

                            }
                        }
                    }
            );
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listThucDon);
        listDiaDiem.setAdapter(adapter);
        listDiaDiem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==3){
                    Intent intent =new Intent(MainActivity.this, DangKy.class);
                    runActivity.launch(intent);
                }
                else{
                    Intent intent = new Intent(MainActivity.this, GioiThieu.class);
                    intent.putExtra("locationIndex", position);
                    startActivity(intent);
                }

            }
        });

//        Intent intent = getIntent();
//        Bundle bundleNhan = intent.getExtras();
//        String hoten, email, ngaybatdau;
//        int soluong, songay;
//        hoten = bundleNhan.getString("Hoten", "");
//        email = bundleNhan.getString("email", "");
//        ngaybatdau = bundleNhan.getString("ngaybatdau", "");
//        songay = bundleNhan.getInt("songay", 0);
//        soluong = bundleNhan.getInt("soluongkhach", 0);
//
//        Toast.makeText(this, "họ tên trưởng đoàn" + hoten, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "email trưởng đoàn" + email, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "ngay bat dau" + ngaybatdau, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "số ngày đi" + songay, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "số lượng khách" + soluong, Toast.LENGTH_SHORT).show();

    }
}