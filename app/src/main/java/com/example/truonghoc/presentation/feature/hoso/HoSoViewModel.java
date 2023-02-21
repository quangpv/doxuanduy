package com.example.truonghoc.presentation.feature.hoso;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.data.repository.TruongHocRepository;
import com.example.truonghoc.domain.ui.IHoSo;
import com.example.truonghoc.domain.ui.IHoSoEditable;
import com.example.truonghoc.presentation.helper.AppExecutors;

public class HoSoViewModel extends ViewModel {
    private final TruongHocRepository truongHocRepository = new TruongHocRepository();
    private final AppExecutors executors = AppExecutors.getInstance();

    public MutableLiveData<IHoSo> hoso = new MutableLiveData<>();
    public MutableLiveData<Boolean> saveSuccess = new MutableLiveData<>();

    public void save() {
        IHoSo hoso1 = hoso.getValue();
        if (!(hoso1 instanceof IHoSoEditable)) {
            return;
        }
        executors.execute(() -> {
            truongHocRepository.save(hoso1);
            saveSuccess.postValue(true);
        });
    }

    public void tryFetch() {
        if (hoso.getValue() != null) return;
        executors.execute(() -> {
            IHoSo h = truongHocRepository.getHoSo();
            if (h != null) hoso.postValue(truongHocRepository.getHoSo());
        });
    }
}
