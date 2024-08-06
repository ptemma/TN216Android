package com.thud.traodoidulieuvoiwebserver;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GoiDuLieuDenServer extends AppCompatActivity {

    TextInputEditText edtHoTen, edtQuocGia, edtNoiDung;
    TextInputLayout layoutHoTen, layoutQuocGia, layoutNoiDung;
    String strHoTen, strQuocGia, strNoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goi_du_lieu_den_server);
        edtHoTen = findViewById(R.id.edt_hoten);
        layoutHoTen = findViewById(R.id.layout_hoten);
        edtQuocGia = findViewById(R.id.edt_quocgia);
        layoutQuocGia = findViewById(R.id.layout_quocgia);
        edtNoiDung = findViewById(R.id.edt_noidung);
        layoutNoiDung = findViewById(R.id.layout_noidung);
    }

    public void ThucHienGoiDuLieu(View view) {
        strHoTen = edtHoTen.getText().toString().trim();
        strQuocGia = edtQuocGia.getText().toString().trim();
        strNoiDung = edtNoiDung.getText().toString().trim();

        if (strHoTen.isEmpty() || strQuocGia.isEmpty() || strNoiDung.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Publics.HasInternet(this)) {
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sendToServer();
                }
            });
        } else {
            Toast.makeText(this, "Lỗi kết nối Internet!", Toast.LENGTH_LONG).show();
        }
    }

    private void sendToServer() {
        try {
            URL url = new URL(Publics.URLGOIDULIEU);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.connect();

            JSONObject dulieu = new JSONObject();
            dulieu.put("name", strHoTen);
            dulieu.put("country", strQuocGia);
            dulieu.put("twitter", strNoiDung);

            OutputStream os = conn.getOutputStream();
            OutputStreamWriter out = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter writer = new BufferedWriter(out);
            writer.write(dulieu.toString());
            writer.flush();

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(GoiDuLieuDenServer.this, "Gởi dữ liệu thành công!", Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(GoiDuLieuDenServer.this, "Gởi dữ liệu không thành công!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            writer.close();
            out.close();
            os.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void XoaNoiDung(View view) {
        edtHoTen.setText("");
        edtQuocGia.setText("");
        edtNoiDung.setText("");
        edtHoTen.requestFocus();
    }
}