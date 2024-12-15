package com.example.cinebooker.LeDucThien.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Integer> selectedMaTinhThanh = new MutableLiveData<>();

    public LiveData<Integer> getMaTinhThanh() {
        return selectedMaTinhThanh;
    }

    public void setMaTinhThanh(int maTinhThanh) {
        selectedMaTinhThanh.setValue(maTinhThanh);
    }
}
