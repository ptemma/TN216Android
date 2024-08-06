package com.thud.homebuild.giaodien;

import static com.thud.homebuild.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.thud.homebuild.R;
import com.thud.homebuild.databinding.ActivityHomeBinding;
import com.thud.homebuild.databinding.ActivityMainBinding;
import com.thud.homebuild.giaodien.cuahang.CuaHangFragment;
import com.thud.homebuild.giaodien.giohang.GioHangFragment;
import com.thud.homebuild.giaodien.thongbao.ThongBaoFragment;
import com.thud.homebuild.giaodien.trangcanhan.TrangCaNhanFragment;

public class Home extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        replaceFragment(new CuaHangFragment());
        setContentView(binding.getRoot());
        binding.bottomNavigation.setOnItemSelectedListener(item ->{
            int itemId = item.getItemId(); // Lấy ID của mục được chọn
            int cuahangId = R.id.cuahang;
            int giohangId = R.id.giohang;
            int thongbaoId = R.id.thongbao;
            int trangcanhanId = R.id.trangcanhan;
            if (itemId == cuahangId) {
                replaceFragment(new CuaHangFragment());
                return true;
            } else if (itemId == giohangId) {
                replaceFragment(new GioHangFragment());
                return true;
            } else if (itemId == thongbaoId) {
                replaceFragment(new ThongBaoFragment());
                return true;
            } else if (itemId == trangcanhanId) {
                replaceFragment(new TrangCaNhanFragment());
                return true;
            }
            return false;
        });


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String email = bundle.getString("email", "");

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}