package com.thud.dulichdalat.ui.lienhe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.thud.dulichdalat.databinding.FragmentLienHeBinding;
public class LienHeFragment extends Fragment {
    private FragmentLienHeBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LienHeViewModel lienHeViewModel = new
                ViewModelProvider(this).get(LienHeViewModel.class);
        binding = FragmentLienHeBinding.inflate(inflater,
                container, false);
        View root = binding.getRoot();
        final TextView textView = binding.txtLienhe;
        lienHeViewModel.getText().observe(
                getViewLifecycleOwner(), textView::setText);
        final Button button = binding.btnTrove;
        button.setOnClickListener(new TroVe());
        return root;
    }
    private class TroVe implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            getActivity().getOnBackPressedDispatcher().onBackPressed();
        }
    }
    @Override
    public void onDestroyView () {
        super.onDestroyView();
        binding = null;
    }
}
