package com.example.quanlythuvien.view.feature.sach.addsach;

import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;


import com.example.quanlythuvien.domain.bo.SachMoi;
import com.example.quanlythuvien.domain.ui.ISach;
import com.example.quanlythuvien.domain.ui.ISachGet;
import com.example.quanlythuvien.domain.ui.ISachSet;
import com.example.quanlythuvien.view.base.BaseViewModel;
import com.example.quanlythuvien.view.model.BiConsumer;
import com.example.quanlythuvien.view.model.ITextWatch;

public class AddSachViewModel extends BaseViewModel {
    MutableLiveData<ISach> sach = new MutableLiveData<>();
    MutableLiveData<String> thongBao = new MutableLiveData<>();
    MutableLiveData<String> thongBaoThanhCong = new MutableLiveData<>();


    public AddSachViewModel() {
        sach.postValue(new SachMoi());
    }

    public void langNgheEditText(@NonNull EditText editText, BiConsumer<String, ISachSet> callback) {
        editText.addTextChangedListener((ITextWatch) text -> appExecutors.execute(() -> {
            if (this.sach.getValue() == null) return;
            ISachSet iSachSet = (ISachSet) this.sach.getValue();
            callback.accept(text, iSachSet);
        }));
    }

    public void themSach() {
        ISachGet iSachGet = (ISachGet)sach.getValue();
        if (iSachGet == null) return;
        appExecutors.execute(() ->
        {
            if (iSachGet.getMaSach().isEmpty() || iSachGet.getTenSach().isEmpty()) {
                thongBao.postValue("Mã Sách Và Tên Sách Trống");
                return;
            }
            if (sachKho.kiemTraSachTonTaiTheoMa(iSachGet.getMaSach())){
                thongBao.postValue("Sách Đã Tồn Tại");
                return;
            }
            sachKho.themSachVaoKho(iSachGet);
            thongBaoThanhCong.postValue("Đã Thêm Sách");
        });
    }
}
