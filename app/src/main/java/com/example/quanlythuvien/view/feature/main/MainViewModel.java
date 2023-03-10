package com.example.quanlythuvien.view.feature.main;

import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.quanlythuvien.domain.bo.ThuThuMoiEditGet;
import com.example.quanlythuvien.domain.ui.IThuThuSet;
import com.example.quanlythuvien.view.base.BaseViewModel;
import com.example.quanlythuvien.view.model.BiConsumer;
import com.example.quanlythuvien.view.model.ITextWatch;

public class MainViewModel extends BaseViewModel {
    MutableLiveData<IThuThuSet> thuThu = new MutableLiveData<>();
    MutableLiveData<String> thongBao = new MutableLiveData<>();
    MutableLiveData<String> thongBaoThanhCong = new MutableLiveData<>();

    public MainViewModel() {
        this.thuThu.setValue(new ThuThuMoiEditGet());
    }

    public void langNgheEditText(@NonNull EditText editText, BiConsumer<String, IThuThuSet> callback) {
        editText.addTextChangedListener((ITextWatch) text -> {
            if (this.thuThu.getValue() == null) return;
            callback.accept(text, this.thuThu.getValue());
        });

    }

    public void  dangNhapVaDangKi(Boolean value) {
        IThuThuSet thuThu = this.thuThu.getValue();
        if (thuThu == null) return;
        appExecutors.execute(() -> {
            if (thuThu.getTk().isEmpty() || thuThu.getMk().isEmpty()) {
                thongBao.postValue("Chưa Nhập Tài Khoản Hoặc Mật Khẩu");
                return;
            }
            if (value && thuThuKho.checkTonTai(thuThu.getTk())) {
                thongBao.postValue("Thu Thư Này Đã Tồn Tại");
                return;
            }
            if (value) {
                thuThuKho.themThuThu(thuThu);
                thongBao.postValue(" Thêm Thủ Thư Thành Công");
            }
            if (!value) {
                if(thuThuKho.kiemTraTaiKhoan(thuThu)) thongBaoThanhCong.postValue("Đăng Nhập Thành Công");
                else thongBao.postValue("Sai Mật Khẩu Hoặc Tài Khoản");
            }
        });
    }

}
