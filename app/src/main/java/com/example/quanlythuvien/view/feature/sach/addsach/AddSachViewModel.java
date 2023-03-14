package com.example.quanlythuvien.view.feature.sach.addsach;

import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;


import com.example.quanlythuvien.domain.bo.SachMoi;
import com.example.quanlythuvien.domain.ui.IChayRunnalble;
import com.example.quanlythuvien.domain.ui.ISach;
import com.example.quanlythuvien.domain.ui.ISachGet;
import com.example.quanlythuvien.domain.ui.ISachSet;
import com.example.quanlythuvien.view.base.BaseViewModel;
import com.example.quanlythuvien.view.help.CheckLoi;
import com.example.quanlythuvien.view.model.BiConsumer;
import com.example.quanlythuvien.view.model.ITextWatch;

public class AddSachViewModel extends BaseViewModel {
    MutableLiveData<ISach> sach = new MutableLiveData<>();
    MutableLiveData<String> thongBaoThanhCong = new MutableLiveData<>();


    public AddSachViewModel() {
        sach.setValue(new SachMoi());
    }

    public void langNgheEditText(@NonNull EditText editText, BiConsumer<String, ISachSet> callback) {
        editText.addTextChangedListener((ITextWatch) text -> {
            if (this.sach.getValue() == null) return;
            ISachSet iSachSet = (ISachSet) this.sach.getValue();
            callback.accept(text, iSachSet);
        });
    }

    public void themSach() {
        ISachGet iSachGet = (ISachGet) sach.getValue();
        if (iSachGet == null) return;
        String maSach = iSachGet.getMaSach().toString();
        chayCoLoiPhanHoi(thongBao, () -> {
            boolean value = CheckLoi.checkLoi(iSachGet.getTenSach(), iSachGet.getTenSach());
            if (!value) throw new Exception("Có mục nhập sai");
            if (sachKho.kiemTraSachTonTaiTheoMa(maSach))
                throw new Exception("Mã Sách Này Đã Tồn Tại");
            sachKho.themSachVaoKho(iSachGet);
            thongBaoThanhCong.postValue("Đã Thêm Sách");
        });
    }
}
