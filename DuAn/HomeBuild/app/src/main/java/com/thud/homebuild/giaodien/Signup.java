package com.thud.homebuild.giaodien;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.thud.homebuild.R;
import com.thud.homebuild.dulieu.Cart;
import com.thud.homebuild.dulieu.User;
import com.thud.homebuild.xuly.CartAdapter;
import com.thud.homebuild.xuly.UserAdapter;

public class Signup extends AppCompatActivity {

    TextInputLayout layoutEmail, layoutPassword, layoutCheckPassword, layoutFullName;
    TextInputEditText edtEmail, edtPassword, edtCheckPassword, edtFullName;
    UserAdapter userAdapter;
    CartAdapter cartAdapter;
    Button btnSignup;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        layoutEmail = findViewById(R.id.layout_emailsignup);
        layoutPassword = findViewById(R.id.layout_passwordsignup);
        layoutCheckPassword = findViewById(R.id.layout_checkpassword);
        layoutFullName = findViewById(R.id.layout_fullName);

        edtEmail = findViewById(R.id.edt_emailsignup);
        edtPassword = findViewById(R.id.edt_passwordsignup);
        edtCheckPassword = findViewById(R.id.edt_checkpassword);
        edtFullName = findViewById(R.id.edt_fullName);
        btnSignup = findViewById(R.id.btn_signup);
        userAdapter = new UserAdapter(this);
        cartAdapter = new CartAdapter(this);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String checkpassword = edtCheckPassword.getText().toString();
                String fullName = edtFullName.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    layoutEmail.setError("Vui lòng nhập email");
                    layoutEmail.requestFocus();
                    return;
                } else {
                    layoutEmail.setError(null);
                }

                if (TextUtils.isEmpty(fullName)) {
                    layoutFullName.setError("Vui lòng nhập họ và tên");
                    layoutFullName.requestFocus();
                    return;
                } else {
                    layoutFullName.setError(null);
                }

                if (TextUtils.isEmpty(password)) {
                    layoutPassword.setError("Vui lòng nhập mật khẩu");
                    layoutPassword.requestFocus();
                    return;
                } else {
                    layoutPassword.setError(null);
                }
                if (TextUtils.isEmpty(checkpassword)) {
                    layoutCheckPassword.setError("Vui lòng xác nhận mật khẩu");
                    layoutCheckPassword.requestFocus();
                    return;
                } else {
                    layoutCheckPassword.setError(null);
                }
                if(password.equals(checkpassword)){
                    if (!userAdapter.checkUser(email)){
                        long result = userAdapter.addUser(email, fullName, password);
                        if (result != -1 ) {
                            user = userAdapter.getUser(email);
                            cartAdapter.addCart(user.getId());
                            Toast.makeText(Signup.this, "Đăng ký tài khoản thành công" , Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Signup.this, Login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Signup.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Signup.this, "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Signup.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void viewDangNhap(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userAdapter.close();
    }


}