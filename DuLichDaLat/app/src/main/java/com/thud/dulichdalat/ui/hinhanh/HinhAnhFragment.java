package com.thud.dulichdalat.ui.hinhanh;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.thud.dulichdalat.R;
import com.thud.dulichdalat.bus.ImageAdapter;
import com.thud.dulichdalat.databinding.FragmentHinhAnhBinding;

public class HinhAnhFragment extends Fragment {
    private FragmentHinhAnhBinding binding;
    private Integer[] Images = {
            R.mipmap.dalat01,
            R.mipmap.dalat02,
            R.mipmap.dalat03,
            R.mipmap.dalat04 };
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HinhAnhViewModel hinhAnhViewModel = new
                ViewModelProvider(this).get(HinhAnhViewModel.class);
        binding = FragmentHinhAnhBinding.inflate(inflater,
                container, false);
        View root = binding.getRoot();
        final GridView gridView = binding.gdvHinhanh;
        gridView.setAdapter(new ImageAdapter(getActivity(), Images));
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}