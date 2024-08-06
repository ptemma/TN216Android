package com.thud.dulichdalat.ui.dangky;

import androidx.lifecycle.ViewModelProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.thud.dulichdalat.R;
import com.thud.dulichdalat.databinding.FragmentDangKyBinding;

public class DangKyFragment extends Fragment {
    private FragmentDangKyBinding binding;
    private View root;
    String arrLoaiTour[] = {"Chinh Phục đỉnh Langbiang",
            "Du lịch nhà vườn",
            "Thành phố tình yêu",
            "Giao lưu Văn hóa Cồng chiêng",
            "Nam Thiên đệ nhất thác"};
    int arrDonGia[] = {220, 280, 170, 150, 350};
    Spinner spnLoaiTour;
    String strKhachHang, strEmail, strLoaiTour, strThanhToan;
    int dongia, sokhach, sotien;
    TextInputLayout layoutKhachHang, layoutEmail, layoutSoKhach;
    TextInputEditText edtKhachHang, edtEmail, edtSoKhach;
    EditText edtDonGia, edtSoTien;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        DangKyViewModel dangKyViewModel = new
                ViewModelProvider(this).get(DangKyViewModel.class);
        binding = FragmentDangKyBinding.inflate(inflater,
                container, false);
        root = binding.getRoot();
        edtKhachHang = binding.edtHotenkh;
        edtEmail = binding.edtEmailkh;
        edtDonGia = binding.edtDongia;
        edtSoKhach = binding.edtSokhach;
        edtSoTien = binding.edtSotien;
        layoutKhachHang = binding.layoutHotenkh;
        layoutEmail = binding.layoutEmailkh;
        layoutSoKhach = binding.layoutSokhach;
        spnLoaiTour = binding.spnLoaitour;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                arrLoaiTour);
        adapter.setDropDownViewResource(
                android.R.layout.simple_list_item_single_choice);
        spnLoaiTour.setAdapter(adapter);
        spnLoaiTour.setOnItemSelectedListener(new ChonLoaiTour());
        Button btnDangKy = binding.btnDangky;
        btnDangKy.setOnClickListener(new DangKyTour());
        Button btnTroVe = binding.btnTrove;
        btnTroVe.setOnClickListener(new QuayVe());
        return root;
    }
    private class ChonLoaiTour implements
            android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int i, long id) {
            strLoaiTour = arrLoaiTour[i];
            dongia = arrDonGia[i];
            edtDonGia.setText("Đơn giá: " + dongia);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            strLoaiTour = "";
            dongia = 0;
        }
    }
    private void GoiEmail(String[] address, String subject,
                          String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("message/rfc822");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        try {
            startActivity(Intent.createChooser(intent,
                    "Chọn ứng dụng gởi Email:"));
        } catch (ActivityNotFoundException err){
            Toast.makeText(getActivity(), "Lỗi thực hiện gởi Email",
                    Toast.LENGTH_LONG).show();
        }
    }
    private class DangKyTour implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            strKhachHang = edtKhachHang.getText().toString().trim();
            if (strKhachHang.length() == 0) {
                layoutKhachHang.setError("Lỗi chưa nhập họ tên khách hàng");
                edtKhachHang.requestFocus();
                return;
            } else
                layoutKhachHang.setError(null);
            strEmail = edtEmail.getText().toString().trim();
            if (strEmail.length() == 0) {
                layoutEmail.setError("Lỗi chưa nhập địa chỉ Email");
                edtEmail.requestFocus();
                return;
            } else
                layoutEmail.setError(null);
            String strSoKhach = edtSoKhach.getText().toString().trim();
            if (strSoKhach.length() == 0 ||
                    Integer.parseInt(strSoKhach) == 0) {
                layoutSoKhach.setError("Lỗi nhập số khách đặt Tour");
                edtSoKhach.requestFocus();
                return;
            } else {
                layoutSoKhach.setError(null);
                sokhach = Integer.parseInt(strSoKhach);
            }
            sotien = sokhach * dongia;
            edtSoTien.setText("Số tiền: " + sotien);
            RadioGroup grpThanhToan = binding.grpThanhtoan;
            int id = grpThanhToan.getCheckedRadioButtonId();
            RadioButton rad = root.findViewById(id);
            strThanhToan = rad.getText().toString();
            String strChuDe = "Thông tin đăng ký Tour du lịch";
            String strThongTin = "Họ tên khách hàng: " + strKhachHang;
            strThongTin += "\nLoại Tour: " + strLoaiTour;
            strThongTin += "\nĐơn giá: " + dongia;
            strThongTin += "\nSố khách: " + sokhach;
            strThongTin += "\nHình thức thanh toán: " + strThanhToan;
            strThongTin += "\nSố tiền: " + sotien;
            strThongTin += "\n\nChúc quý khách vui vẻ!";
            GoiEmail(new String[]{strEmail}, strChuDe, strThongTin);
        }
    }
    private class QuayVe implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            getActivity().getOnBackPressedDispatcher().onBackPressed();
        }
    }
}