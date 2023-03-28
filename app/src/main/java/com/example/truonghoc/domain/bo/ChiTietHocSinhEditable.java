package com.example.truonghoc.domain.bo;

import com.example.truonghoc.domain.ui.HasIsEdited;
import com.example.truonghoc.domain.ui.IChiTietHocSinh;
import com.example.truonghoc.domain.ui.IChiTietHocSinhEditable;
import com.example.truonghoc.domain.ui.IDate;

import java.util.Objects;

public class ChiTietHocSinhEditable implements IChiTietHocSinhEditable, HasIsEdited {
    private final IChiTietHocSinh hocSinh;
    private String mLop;
    private String mName;
    private String mGender;
    private IDate mDob;

    public ChiTietHocSinhEditable(IChiTietHocSinh hocSinh) {
        this.hocSinh = hocSinh;
        mLop = hocSinh.getLop();
        mName = hocSinh.getName();
        mGender = hocSinh.getGender();
        mDob = new MutableDate(hocSinh.getDob().toString());
    }

    @Override
    public String getLop() {
        return mLop;
    }

    @Override
    public String getId() {
        return hocSinh.getId();
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
    public boolean isEdited() {
        return !Objects.equals(hocSinh.getLop(), mLop)
                || !Objects.equals(hocSinh.getDob().toString(), mDob.toString())
                || !Objects.equals(hocSinh.getGender(), mGender)
                || !Objects.equals(hocSinh.getName(), mName);
    }

    public IChiTietHocSinh getHocSinhReadonly() {
        return hocSinh;
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
