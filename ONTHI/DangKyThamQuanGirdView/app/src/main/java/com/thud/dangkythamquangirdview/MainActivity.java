package com.thud.dangkythamquangirdview;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> runActivity;
    private Integer[] Images = { R.mipmap.dinhdoclap, R.mipmap.cuchi,
            R.mipmap.condao, R.mipmap.gioithieu };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView dgvMenu = findViewById(R.id.dgv_menu);
        ImageAdapter adapter = new ImageAdapter(this, Images);
        dgvMenu.setAdapter(adapter);

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
    }
}