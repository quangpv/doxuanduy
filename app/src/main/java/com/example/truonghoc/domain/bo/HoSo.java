package com.example.truonghoc.domain.bo;

import com.example.truonghoc.data.model.ThongTinTruongHocEntity;
import com.example.truonghoc.domain.ui.IHoSo;
import com.example.truonghoc.domain.ui.IImage;

public class HoSo implements IHoSo {

    private final ThongTinTruongHocEntity truongHocEntity;

    public HoSo(ThongTinTruongHocEntity truongHocEntity) {
        this.truongHocEntity = truongHocEntity;
        this.truongHocEntity.getImageUri();
    }

    @Override
    public String getPhoneNumber() {
        return truongHocEntity.getSdtTruong();
    }

    @Override
    public String getAddress() {
        return truongHocEntity.getDiaChiTruong();
    }

    @Override
    public String getName() {
        return truongHocEntity.getTenTruong();
    }

    @Override
    public IImage getImage() {
        return null;
    }

}