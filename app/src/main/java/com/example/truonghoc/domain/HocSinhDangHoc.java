package com.example.truonghoc.domain;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "hocSinhDangHoc")
public class HocSinhDangHoc implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public long Id;
    @Embedded
    HocSinh hocSinh;
    @Embedded
    KhoiLop khoiLop;

    public HocSinhDangHoc(HocSinh hocSinh, KhoiLop khoiLop) {
        this.hocSinh = hocSinh;
        this.khoiLop = khoiLop;
    }

    protected HocSinhDangHoc(Parcel in) {
        Id = in.readLong();
        hocSinh = in.readParcelable(HocSinh.class.getClassLoader());
        khoiLop = in.readParcelable(KhoiLop.class.getClassLoader());
    }

    public static final Creator<HocSinhDangHoc> CREATOR = new Creator<HocSinhDangHoc>() {
        @Override
        public HocSinhDangHoc createFromParcel(Parcel in) {
            return new HocSinhDangHoc(in);
        }

        @Override
        public HocSinhDangHoc[] newArray(int size) {
            return new HocSinhDangHoc[size];
        }
    };

    public HocSinh getHocSinh() {
        return hocSinh;
    }

    public KhoiLop getKhoiLop() {
        return khoiLop;
    }

    public void setHocSinh(HocSinh hocSinh) {
        this.hocSinh = hocSinh;
    }

    public void setKhoiLop(KhoiLop khoiLop) {
        this.khoiLop = khoiLop;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(Id);
        dest.writeParcelable(hocSinh, flags);
        dest.writeParcelable(khoiLop, flags);
    }
}
