package com.example.truonghoc.data.repository;

import com.example.truonghoc.data.datasource.AppDatasource;
import com.example.truonghoc.data.model.ThongTinTruongHocEntity;
import com.example.truonghoc.domain.bo.HoSoEditable;
import com.example.truonghoc.domain.ui.IHoSo;

public class TruongHocRepository {
    private final AppDatasource appDatasource = AppDatasource.getInstance();

    public IHoSo getHoSo() {
        ThongTinTruongHocEntity truongHocEntity = appDatasource.layThongTinTruong();
        return new HoSoEditable(truongHocEntity);
    }

    public void save(IHoSo hoso) {
        appDatasource.setThongTinTruong(new ThongTinTruongHocEntity(
                hoso.getName().toString(),
                hoso.getAddress().toString(),
                hoso.getPhoneNumber().toString()
        ));
    }
}
