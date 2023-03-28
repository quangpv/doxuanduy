package com.example.truonghoc.presentation.feature.themhocsinh;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.data.repository.ThongSinhHocSinhRepository;
import com.example.truonghoc.domain.bo.HocSinhMoi;
import com.example.truonghoc.domain.ui.IChiTietHocSinh;
import com.example.truonghoc.domain.ui.IChiTietHocSinhEditable;
import com.example.truonghoc.presentation.helper.AppExecutors;

public class ThemHocSinhViewModel extends ViewModel {
    private final ThongSinhHocSinhRepository thongSinhHocSinhRepository = ThongSinhHocSinhRepository.getInstance();
    private final AppExecutors executors = AppExecutors.getInstance();

    public MutableLiveData<IChiTietHocSinhEditable> hocSinh = new MutableLiveData<>();
    public MutableLiveData<Boolean> themThanhCong = new MutableLiveData<>();
    public MutableLiveData<String> themThatBai = new MutableLiveData<>();

    public ThemHocSinhViewModel() {
        hocSinh.setValue(new HocSinhMoi());
    }

    public void add() {
        IChiTietHocSinh hocSinh1 = hocSinh.getValue();
        if (hocSinh1 == null) return;
        executors.execute(() -> {
            if (hocSinh1.getId().isEmpty()) {
                themThatBai.postValue("Mã học sinh trống");
                return;
            }
            if (thongSinhHocSinhRepository.exists(hocSinh1)) {
                themThatBai.postValue("Đã tồn tại học sinh " + hocSinh1.getId());
                return;
            }
            thongSinhHocSinhRepository.add(hocSinh1);
            themThanhCong.postValue(true);
        });
    }
}
