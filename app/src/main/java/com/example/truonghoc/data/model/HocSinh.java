package com.example.truonghoc.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class HocSinh implements Parcelable {
    private String maHocSinh;
    private String hoVaTen;
    private String gioiTinh;
    private String sinhNgay;
    private String avatar;


    public HocSinh(String avatar,String maHocSinh, String hoVaTen, String gioiTinh, String sinhNgay) {
        this.maHocSinh = maHocSinh;
        this.hoVaTen = hoVaTen;
        this.gioiTinh = gioiTinh;
        this.sinhNgay = sinhNgay;
        this.avatar = avatar;
    }



    protected HocSinh(Parcel in) {
        maHocSinh = in.readString();
        hoVaTen = in.readString();
        gioiTinh = in.readString();
        sinhNgay = in.readString();
        avatar = in.readString();
    }

    public static final Creator<HocSinh> CREATOR = new Creator<HocSinh>() {
        @Override
        public HocSinh createFromParcel(Parcel in) {
            return new HocSinh(in);
        }

        @Override
        public HocSinh[] newArray(int size) {
            return new HocSinh[size];
        }
    };

    public String getMaHocSinh() {
        return maHocSinh;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSinhNgay() {
        return sinhNgay;
    }

    public void setMaHocSinh(String maHocSinh) {
        this.maHocSinh = maHocSinh;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setSinhNgay(String sinhNgay) {
        this.sinhNgay = sinhNgay;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(maHocSinh);
        dest.writeString(hoVaTen);
        dest.writeString(gioiTinh);
        dest.writeString(sinhNgay);
        dest.writeString(avatar);
    }
}

