package com.example.truonghoc.presentation.feature.hoso;

import androidx.lifecycle.MutableLiveData;

import com.example.truonghoc.data.repository.TruongHocRepository;
import com.example.truonghoc.domain.ui.IHoSo;
import com.example.truonghoc.domain.ui.IHoSoEditable;
import com.example.truonghoc.presentation.base.BaseViewModel;
import com.example.truonghoc.presentation.helper.ValidationUtils;

public class HoSoViewModel extends BaseViewModel {
    private final TruongHocRepository truongHocRepository = new TruongHocRepository();

    public MutableLiveData<IHoSo> hoso = new MutableLiveData<>();
    public MutableLiveData<Boolean> saveSuccess = new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();

    public void save() {
        IHoSo hoso1 = hoso.getValue();
        if (!(hoso1 instanceof IHoSoEditable)) {
            return;
        }
        launch(error, () -> {
            if (!ValidationUtils.isAllValid(
                    hoso1.getPhoneNumber(),
                    hoso1.getAddress(),
                    hoso1.getName()
            )) {
                throw new Exception("Lỗi nhập liệu");
            }
            truongHocRepository.save(hoso1);
            saveSuccess.postValue(true);
        });
    }

    public void tryFetch() {
        if (hoso.getValue() != null) return;
        launch(() -> {
            IHoSo h = truongHocRepository.getHoSo();
            if (h != null) hoso.postValue(truongHocRepository.getHoSo());
        });
    }
}
