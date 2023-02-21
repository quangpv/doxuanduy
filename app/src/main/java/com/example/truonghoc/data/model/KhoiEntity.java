package com.example.truonghoc.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class KhoiEntity implements Parcelable {
    private String khoiLop;

    public KhoiEntity(String khoiLop) {
        this.khoiLop = khoiLop;
    }

    protected KhoiEntity(Parcel in) {
        khoiLop = in.readString();
    }

    public static final Creator<KhoiEntity> CREATOR = new Creator<KhoiEntity>() {
        @Override
        public KhoiEntity createFromParcel(Parcel in) {
            return new KhoiEntity(in);
        }

        @Override
        public KhoiEntity[] newArray(int size) {
            return new KhoiEntity[size];
        }
    };

    public String getKhoiLop() {
        return khoiLop;
    }

    public void setKhoiLop(String khoiLop) {
        this.khoiLop = khoiLop;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(khoiLop);
    }
}
