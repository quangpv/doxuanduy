package com.example.truonghoc.presentation.feature.thongtinhocsinh;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.data.repository.ThongSinhHocSinhRepository;
import com.example.truonghoc.domain.bo.ChiTietHocSinhEditable;
import com.example.truonghoc.domain.ui.HasIsEdited;
import com.example.truonghoc.domain.ui.IChiTietHocSinh;
import com.example.truonghoc.domain.ui.IChiTietHocSinhEditable;
import com.example.truonghoc.presentation.helper.AppExecutors;

public class ThongTinHocSinhViewModel extends ViewModel {
    private final AppExecutors executors = AppExecutors.getInstance();
    private final ThongSinhHocSinhRepository thongSinhHocSinhRepository = ThongSinhHocSinhRepository.getInstance();

    public MutableLiveData<IChiTietHocSinh> chiTiet = new MutableLiveData<>();
    public MutableLiveData<IChiTietHocSinh> xoaThanhCong = new MutableLiveData<>();

    public boolean isEdit() {
        if (chiTiet.getValue() instanceof HasIsEdited) {
            return ((HasIsEdited) chiTiet.getValue()).isEdited();
        }
        return false;
    }

    public void setId(String id) {
        executors.execute(() -> fetchHocSinh(id));
    }

    private void fetchHocSinh(String id) {
        IChiTietHocSinh hocSinh = thongSinhHocSinhRepository.findById(id);
        if (hocSinh != null) chiTiet.postValue(hocSinh);
    }

    public void enableEdit() {
        IChiTietHocSinh hocSinh = chiTiet.getValue();
        if (hocSinh == null) return;
        if (hocSinh instanceof IChiTietHocSinhEditable) return;
        chiTiet.setValue(new ChiTietHocSinhEditable(hocSinh));
    }

    public void save() {
        IChiTietHocSinh hocSinh = chiTiet.getValue();
        if (hocSinh == null) return;
        if (!(hocSinh instanceof IChiTietHocSinhEditable)) return;
        executors.execute(() -> {
            thongSinhHocSinhRepository.save(hocSinh);
            fetchHocSinh(hocSinh.getId());
        });
    }

    public void disableEdit() {
        IChiTietHocSinh hocSinh = chiTiet.getValue();
        if (hocSinh == null) return;
        chiTiet.setValue(((ChiTietHocSinhEditable) hocSinh).getHocSinhReadonly());
    }

    public void delete(String id) {
        executors.execute(() -> {
            thongSinhHocSinhRepository.delete(id);
            xoaThanhCong.postValue(null);
        });
    }
}
