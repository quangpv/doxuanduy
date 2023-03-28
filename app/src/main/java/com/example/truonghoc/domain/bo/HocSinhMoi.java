package com.example.truonghoc.domain.bo;

import com.example.truonghoc.domain.ui.HasSetId;
import com.example.truonghoc.domain.ui.IChiTietHocSinhEditable;
import com.example.truonghoc.domain.ui.IDate;

public class HocSinhMoi implements IChiTietHocSinhEditable, HasSetId {
    private String mLop;
    private String mName;
    private String mGender;
    private IDate mDob;
    private String mId;

    public HocSinhMoi() {
        mId = "";
        mLop = "";
        mName = "";
        mGender = "";
        mDob = new MutableDate("");
    }

    @Override
    public void setId(String id) {
        mId = id;
    }

    @Override
    public String getLop() {
        return mLop;
    }

    @Override
    public String getId() {
        return mId;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getGender() {
        return mGender;
    }

    @Override
    public IDate getDob() {
        return mDob;
    }

    @Override
    public void setName(String name) {
        this.mName = name;
    }

    @Override
    public void setLop(String lop) {
        this.mLop = lop;
    }

    @Override
    public void setGioiTinh(String gioiTinh) {
        this.mGender = gioiTinh;
    }
}
