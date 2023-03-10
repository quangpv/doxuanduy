package com.example.truonghoc.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class HocSinh implements Parcelable {
    private String maHocSinh,hoVaTen,gioiTinh,sinhNgay;

    public HocSinh(String maHocSinh, String hoVaTen, String gioiTinh, String sinhNgay) {
        this.maHocSinh = maHocSinh;
        this.hoVaTen = hoVaTen;
        this.gioiTinh = gioiTinh;
        this.sinhNgay = sinhNgay;
    }

    protected HocSinh(Parcel in) {
        maHocSinh = in.readString();
        hoVaTen = in.readString();
        gioiTinh = in.readString();
        sinhNgay = in.readString();
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
    }
}

