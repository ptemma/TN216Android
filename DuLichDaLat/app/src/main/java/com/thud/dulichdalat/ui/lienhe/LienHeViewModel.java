package com.thud.dulichdalat.ui.lienhe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LienHeViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    public LienHeViewModel() {
        mText = new MutableLiveData<>();
        String strLienHe ="Du lịch Đà Lạt";
        strLienHe += "\n\nEmail: dalattourist@gmail.com";
        mText.setValue(strLienHe);
    }
    public LiveData<String> getText() {
        return mText;
    }
}