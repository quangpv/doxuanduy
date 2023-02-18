package com.example.truonghoc.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class KhoiLop implements Parcelable {
    private String khoiLop;

    public KhoiLop(String khoiLop) {
        this.khoiLop = khoiLop;
    }

    protected KhoiLop(Parcel in) {
        khoiLop = in.readString();
    }

    public static final Creator<KhoiLop> CREATOR = new Creator<KhoiLop>() {
        @Override
        public KhoiLop createFromParcel(Parcel in) {
            return new KhoiLop(in);
        }

        @Override
        public KhoiLop[] newArray(int size) {
            return new KhoiLop[size];
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
