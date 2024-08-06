package com.thud.dulichdalat.ui.gioithieu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.thud.dulichdalat.databinding.FragmentGioiThieuBinding;

public class GioiThieuFragment extends Fragment {

    private FragmentGioiThieuBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GioiThieuViewModel gioiThieuViewModel = new
                ViewModelProvider(this).get(GioiThieuViewModel.class);
        binding = FragmentGioiThieuBinding.inflate(inflater,
                container, false);View root = binding.getRoot();
        final TextView textView = binding.txtGioithieu;
        gioiThieuViewModel.getText().observe(
                getViewLifecycleOwner(), textView::setText);
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}