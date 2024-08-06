package com.thud.homebuild.giaodien.trangcanhan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.thud.homebuild.R;
import com.thud.homebuild.dulieu.User;
import com.thud.homebuild.giaodien.Login;
import com.thud.homebuild.giaodien.Signup;
import com.thud.homebuild.giaodien.admin.NoiThat;
import com.thud.homebuild.xuly.UserAdapter;

public class TrangCaNhanFragment extends Fragment {

    private TextInputEditText edtFullName,edtEmail, edtPassword ;
    private Button btnUpdateProfile, btnChangeInfo, btnAn;

    UserAdapter userAdapter;
    User user;

    public TrangCaNhanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_ca_nhan, container, false);
        // Tìm và lưu trữ ID của các view vào biến tương ứng
        edtFullName = view.findViewById(R.id.edt_fullName);
        edtEmail = view.findViewById(R.id.edt_email);
        edtPassword = view.findViewById(R.id.edt_matkhau);
        btnChangeInfo = view.findViewById(R.id.btn_changePassword);
        btnUpdateProfile = view.findViewById(R.id.btn_updateProfile);
        btnAn= view.findViewById(R.id.btn_addItem);
        userAdapter = new UserAdapter(getActivity());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("profile", Context.MODE_PRIVATE);
        String email;

        email = sharedPreferences.getString("email", "");
        edtEmail.setText(email);
        if(email.equals("admin@gmail.com")){
            btnAn.setVisibility(View.VISIBLE);
        }else {
            btnAn.setVisibility(View.GONE);
        }
        user = userAdapter.getUser(email);
        edtFullName.setText(user.getFullname());
        edtPassword.setText(user.getPassword());

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnChangeInfo.setEnabled(true);
                edtFullName.requestFocus();
                btnUpdateProfile.setEnabled(false);
                edtPassword.setEnabled(true);
            }
        });
        btnChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtFullName.setEnabled(true);
                btnUpdateProfile.setEnabled(true);
                btnChangeInfo.setEnabled(false);
                edtFullName.requestFocus();
                String newFullName = edtFullName.getText().toString().trim();
                String newpassword = edtPassword.getText().toString().trim();
                int result = userAdapter.updateUser(user.getId(), newpassword, newFullName, email);
                if(result != -1){
                    Toast.makeText(getActivity(), "Thay đổi thành công", Toast.LENGTH_SHORT).show();

                }
            }

        });

        btnAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoiThat.class);
                startActivity(intent);
            }
        });

        return view;
    }



}