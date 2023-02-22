package com.example.truonghoc.data.repository;

import android.content.ContentResolver;
import android.net.Uri;

import com.example.truonghoc.data.datasource.AppDatasource;
import com.example.truonghoc.data.model.ThongTinTruongHocEntity;
import com.example.truonghoc.domain.bo.HoSoEditable;
import com.example.truonghoc.domain.ui.IHoSo;
import com.example.truonghoc.domain.ui.IImage;
import com.example.truonghoc.presentation.helper.AppFileManager;
import com.example.truonghoc.domain.ui.HasUri;

import java.util.Objects;

public class TruongHocRepository {
    private final AppDatasource appDatasource = AppDatasource.getInstance();
    private final AppFileManager appFileManager = AppFileManager.getInstance();

    public IHoSo getHoSo() {
        ThongTinTruongHocEntity truongHocEntity = appDatasource.layThongTinTruong();
        return new HoSoEditable(truongHocEntity);
    }

    public void save(IHoSo hoso) {
        IImage image = hoso.getImage();
        String imageUri = "";
        if (image instanceof HasUri) {
            Uri uri = ((HasUri) image).getUri();
            if (Objects.equals(uri.getScheme(), ContentResolver.SCHEME_CONTENT)) {
                uri = appFileManager.saveToAppFolder(uri);
            }
            imageUri = uri.toString();
        }
        appDatasource.setThongTinTruong(new ThongTinTruongHocEntity(
                hoso.getName().toString(),
                hoso.getAddress().toString(),
                hoso.getPhoneNumber().toString(),
                imageUri
        ));
    }
}
