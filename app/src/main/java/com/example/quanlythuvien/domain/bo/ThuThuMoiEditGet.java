package com.example.quanlythuvien.domain.bo;

import com.example.quanlythuvien.domain.ui.IThuThuSet;

public class ThuThuMoiEditGet implements IThuThuSet {
    private String tk, mk;

    public ThuThuMoiEditGet() {
        this.tk = "";
        this.mk = "";
    }

    @Override
    public void setTk(String tk) {
        this.tk = tk;
    }

    @Override
    public void setMK(String mk) {
        this.mk = mk;
    }

    @Override
    public String getTk() {
        return tk;
    }

    @Override
    public String getMk() {
        return mk;
    }
}
