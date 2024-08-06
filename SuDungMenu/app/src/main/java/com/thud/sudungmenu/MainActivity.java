package com.thud.sudungmenu;

import static com.thud.sudungmenu.R.id.listview_diadiem;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> listDiaDiem;
    String strDiaDiem = "";
    ListView listviewDiaDiem;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.globeicon);
        listviewDiaDiem = findViewById(R.id.listview_diadiem);
        listDiaDiem = new ArrayList<String>();
        listDiaDiem.add("Đà Lạt");
        listDiaDiem.add("Long Hải");
        listDiaDiem.add("Nha Trang");
        listDiaDiem.add("Vịnh Hạ Long");
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listDiaDiem);
        listviewDiaDiem.setAdapter(adapter);
        listviewDiaDiem.setOnItemClickListener(new ChonDiaDiem());
        registerForContextMenu(listviewDiaDiem);
    }

    private class ChonDiaDiem implements
            android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
            strDiaDiem = listDiaDiem.get(position);
            Toast.makeText(MainActivity.this,"Địa điểm đã chọn: " + strDiaDiem, Toast.LENGTH_SHORT).show();
        }
    }
//yeu chu nhe
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == R.id.mnu_thich){
            Toast.makeText(this, "Địa điểm yêu thích", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == R.id.mnu_khuyenmai){
            Toast.makeText(this, "Khuyến mãi", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == R.id.mnu_camnangdl){
            Toast.makeText(this, "Cẩm nang du lịch", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == R.id.mnu_tietkiem){
            Toast.makeText(this, "Tour tiết kiệm", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == R.id.mnu_tieuchuan){
            Toast.makeText(this, "Tour tiêu chuẩn", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == R.id.mnu_caocap){
            Toast.makeText(this, "Tour cao cấp", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(item.getItemId() == R.id.mnu_chitiet){
            Toast.makeText(this, "Thông tin chi tiết", Toast.LENGTH_SHORT).show();
            return  true;
        }else if(item.getItemId() == R.id.mnu_dattour){
            Toast.makeText(this, "Đặt tour", Toast.LENGTH_SHORT).show();
            return  true;
        }
        else if(item.getItemId() == R.id.mnu_xoadd){
            Toast.makeText(this, "Xóa địa điểm", Toast.LENGTH_SHORT).show();
            return  true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}