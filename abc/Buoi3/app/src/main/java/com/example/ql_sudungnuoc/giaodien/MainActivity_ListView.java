package com.example.ql_sudungnuoc.giaodien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ql_sudungnuoc.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_ListView extends AppCompatActivity {

    Class[] arrClasses = {
            ThongTinNhanVien.class,
            ThongTinKhachHang.class,
            ThongBao.class,
            DangNhap.class
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view);

        List<String> listThucDon;
        ListView listViewThucDon;

        listViewThucDon = findViewById(R.id.listView_ThucDon);
        ArrayAdapter<String> adapter;

        listThucDon = new ArrayList<>();
        listThucDon.add("Nhân viên");
        listThucDon.add("Thông báo");
        listThucDon.add("Đăng nhập lại");
        listThucDon.add("Thoát ứng dụng");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listThucDon);

        listViewThucDon.setAdapter(adapter);
        listViewThucDon.setOnItemClickListener(new ChonActivity());
    }

    public class ChonActivity implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> adapterView,
                                View view, int i, long l) {
            if(i == 4)
                finish();
            else {
                Intent intent = new Intent(MainActivity_ListView.this,
                        arrClasses[i]);
                startActivity(intent);
            }
        }

    }
}
