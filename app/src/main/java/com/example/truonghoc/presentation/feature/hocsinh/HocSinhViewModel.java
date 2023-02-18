package com.example.truonghoc.presentation.feature.hocsinh;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.domain.IHocSinh;
import com.example.truonghoc.presentation.helper.AppExecutors;

import java.util.List;

public class HocSinhViewModel extends ViewModel {
    private final AppExecutors appExecutors = AppExecutors.getInstance();
    private final HocSinhRepository hocSinhRepository = HocSinhRepository.getInstance();

    public final MutableLiveData<List<IHocSinh>> hocSinhList = new MutableLiveData<>();
    private String mSearch = "";

    public void search(String text) {
        mSearch = text;
        appExecutors.execute(() -> {
            hocSinhList.postValue(hocSinhRepository.getHocSinhList(text));
        });
    }

    public void tryFetch() {
        search(mSearch);
    }
}
