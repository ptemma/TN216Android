package com.thud.homebuild.giaodien;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Trace;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.thud.homebuild.R;
import com.thud.homebuild.dulieu.User;
import com.thud.homebuild.giaodien.cuahang.CuaHangFragment;
import com.thud.homebuild.xuly.UserAdapter;

public class Login extends AppCompatActivity {
    TextInputLayout layoutEmail, layoutPassword;
    TextInputEditText edtEmail, edtPassword;
    UserAdapter userAdapter;
    SharedPreferences sharedPreferences;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userAdapter = new UserAdapter(this);
        layoutEmail = findViewById(R.id.layout_emaillogin);
        layoutPassword = findViewById(R.id.layout_passwordlogin);

        edtEmail = findViewById(R.id.edt_emaillogin);
        edtPassword = findViewById(R.id.edt_passwordlogin);
        sharedPreferences = getSharedPreferences("profile", MODE_PRIVATE);
    }
    public void signIn(View view){
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if(!userAdapter.checkUser(email)){
            layoutEmail.setError("Email chưa được đăng ký");
            layoutEmail.requestFocus();
            return;
        } else if (email == "") {
            layoutEmail.setError("Vui lòng nhập Email");
            layoutEmail.requestFocus();
            return;
        } else {
            layoutEmail.setError(null);
        }

        if (password == "") {
            layoutPassword.setError("Vui lòng nhập mật khẩu");
            layoutPassword.requestFocus();
            return;
        } else {
            layoutPassword.setError(null);
        }
        if(userAdapter.checkLogin(email, password)){
            edtPassword.setText("");

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", email);
            editor.apply();

            Toast.makeText(this, email, Toast.LENGTH_SHORT).show();

            Bundle bundle = new Bundle();
            bundle.putString("email", email);
            Intent intent = new Intent(this, Home.class);
            intent.putExtras(bundle);
            startActivity(intent);

        }else {
            // Xác thực không thành công, hiển thị thông báo lỗi cho người dùng
            Toast.makeText(this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
        }

    }
    public void viewDangKy(View view){
        Intent intent= new Intent(this, Signup.class);
        startActivity(intent);
    }
}