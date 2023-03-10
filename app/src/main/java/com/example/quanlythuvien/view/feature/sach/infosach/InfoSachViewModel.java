package com.example.quanlythuvien.view.feature.sach.infosach;


import android.content.Intent;
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


public class InfoSachViewModel extends BaseViewModel {
    MutableLiveData<ISach> sach = new MutableLiveData<>();


    public void setId(Intent intent) {
        if (intent == null) return;
        String maSach = intent.getStringExtra("id");
        appExecutors.execute(() -> timSachTheoMaSach(maSach));

    }

    private void timSachTheoMaSach(String maSach) {
        ISachGet iSachGet= sachKho.laySachTheoMaSach(maSach);
        sach.postValue(iSachGet);
    }

    public void langNgheEditText(@NonNull EditText editText, BiConsumer<String, ISachSet> callback) {
        editText.addTextChangedListener((ITextWatch) text -> appExecutors.execute(() -> {
            if (this.sach.getValue() == null) return;
            if (this.sach.getValue() instanceof SachMoi)
                callback.accept(text, (ISachSet) this.sach.getValue());
        }));
    }

    public void batSuaSach() {
        appExecutors.execute(() -> {
            ISachGet isach = (ISachGet) this.sach.getValue();
            if (isach == null) return;
            if (isach instanceof ISachSet) return;
            sach.postValue(new SachMoi(isach));
        });

    }

    public void tatSuaSach() {
        appExecutors.execute(() -> {
            ISach iSach = this.sach.getValue();
            if (iSach instanceof SachMoi) {
                ISachGet iSachGet = ((SachMoi) iSach).laySachKhongSua();
                this.sach.postValue(iSachGet);
            }
        });

    }

    public boolean daCoSua() {
        ISach iSach = this.sach.getValue();
        if (iSach instanceof SachMoi) {
            return ((SachMoi) iSach).kiemTraDaSuaChua();
        }
        return false;
    }

    public void luuHocSinh() {
        ISach iSach = this.sach.getValue();
        if (iSach == null) return;
        if (!(iSach instanceof SachMoi)) return;
        appExecutors.execute(() -> {
            sachKho.capNhapSach((ISachGet) iSach);
            timSachTheoMaSach(((SachMoi) iSach).getMaSach());
        });
    }

}
