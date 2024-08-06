package thud.chisokhoicothe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextInputEditText edtTrongLuong, edtChieuCao, edtBmi, edtKetLuan;
    TextInputLayout layoutTrongLuong, layoutChieuCao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar myActionBar = getSupportActionBar();
        if(myActionBar != null) {
            myActionBar.setDisplayShowHomeEnabled(true);
            myActionBar.setIcon(R.mipmap.basicicon_2);
        }
        edtTrongLuong = findViewById(R.id.edt_trongluong);
        edtChieuCao = findViewById(R.id.edt_chieucao);
        edtBmi = findViewById(R.id.edt_mbi);
        edtKetLuan = findViewById(R.id.edt_ketluan);
        layoutTrongLuong = findViewById(R.id.layout_trongluong);
        layoutChieuCao = findViewById(R.id.layout_chieucao);

    }

    public void XacDinhKetQua(View view) {
        String strTrongLuong, strChieuCao;
        strTrongLuong = edtTrongLuong.getText().toString().trim();
        if (strTrongLuong.length() < 1) {
            edtTrongLuong.requestFocus();
            edtTrongLuong.selectAll();
            layoutTrongLuong.setError("Chưa nhập trọng lượng!");
            return;
        }
        else
            layoutTrongLuong.setError(null);
        strChieuCao = edtChieuCao.getText().toString().trim();
        if (strChieuCao.length() < 1) {
            edtChieuCao.requestFocus();
            edtChieuCao.selectAll();
            layoutChieuCao.setError("Chưa nhập chiều cao!");
            return;
        }
        else {
            layoutChieuCao.setError(null);
        }
        float trongluong = Float.parseFloat(strTrongLuong);
        float chieucao = Float.parseFloat(strChieuCao);
        float bmi = trongluong/ (chieucao * chieucao);
        String ketluan = "";
        if(bmi < 18)
            ketluan = "Bạn hơi gầy!";
        else if(bmi < 25)
            ketluan = "Bạn bình thường!";
        else if(bmi < 30)
            ketluan = "Bạn hơi mập!";
        else if(bmi < 35)
            ketluan = "Bạn mập rồi!";
        else
            ketluan = "Bạn quá mập rồi!";
        DecimalFormat decFormat = new DecimalFormat("#.00");
        edtBmi.setText("" + decFormat.format(bmi));
        edtKetLuan.setText(ketluan);
    }

    public void Dong(View view) {
        finish();
    }
}