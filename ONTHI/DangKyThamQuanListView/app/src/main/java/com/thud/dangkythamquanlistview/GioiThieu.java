package com.thud.dangkythamquanlistview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GioiThieu extends AppCompatActivity {

    String[] diadiem = {"Dinh Độc lập","Địa đạo Củ Chi","Nhà tù Côn Đảo"};
    Integer[] hinhanh = {R.drawable.dinhdoclap, R.drawable.cuchi, R.drawable.condao};
    int currentIndex =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setIcon(R.mipmap.ic_logo);
        myActionBar.setDisplayShowHomeEnabled(true);
        int locationIndex = getIntent().getIntExtra("locationIndex", 0);
        TextView txtDiaDiem = findViewById(R.id.txt_diadiem);
        ImageView imgDiaDiem = findViewById(R.id.img_diadiem);
        txtDiaDiem.setText(diadiem[locationIndex]);
        imgDiaDiem.setImageResource(hinhanh[locationIndex]);
        imgDiaDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Increment the index to display the next location
                currentIndex = (currentIndex + 1) % diadiem.length;
                txtDiaDiem.setText(diadiem[currentIndex]);
                imgDiaDiem.setImageResource(hinhanh[currentIndex]);
            }
        });
    }
}