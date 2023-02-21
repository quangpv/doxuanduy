package com.example.truonghoc.data.repository;

import com.example.truonghoc.data.datasource.AppDatasource;
import com.example.truonghoc.data.model.ThongTinTruongHocEntity;
import com.example.truonghoc.domain.ui.IHoSo;
import com.example.truonghoc.domain.ui.IHoSoEditable;

public class TruongHocRepository {
    private final AppDatasource appDatasource = AppDatasource.getInstance();

    public IHoSo getHoSo() {
        ThongTinTruongHocEntity truongHocEntity = appDatasource.layThongTinTruong();
        return new HoSoEditable(truongHocEntity);
    }

    public void save(IHoSo hoso) {
        appDatasource.setThongTinTruong(new ThongTinTruongHocEntity(
                hoso.getName(),
                hoso.getAddress(),
                hoso.getPhoneNumber()
        ));
    }

    private static class HoSo implements IHoSo {

        private final ThongTinTruongHocEntity truongHocEntity;

        public HoSo(ThongTinTruongHocEntity truongHocEntity) {
            this.truongHocEntity = truongHocEntity;
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
    }

    private static class HoSoEditable implements IHoSoEditable {
        private String mPhoneNumber;
        private String mAddress;
        private String mName;

        public HoSoEditable(ThongTinTruongHocEntity truongHocEntity) {
            mPhoneNumber = truongHocEntity.getSdtTruong();
            mAddress = truongHocEntity.getDiaChiTruong();
            mName = truongHocEntity.getTenTruong();
        }

        @Override
        public void setName(String name) {
            mName = name;
        }

        @Override
        public void setAddress(String address) {
            mAddress = address;
        }

        @Override
        public void setPhoneNumber(String phoneNumber) {
            mPhoneNumber = phoneNumber;
        }

        @Override
        public String getPhoneNumber() {
            return mPhoneNumber;
        }

        @Override
        public String getAddress() {
            return mAddress;
        }

        @Override
        public String getName() {
            return mName;
        }
    }
}
