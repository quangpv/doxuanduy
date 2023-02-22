package com.example.truonghoc.domain.bo;

import android.net.Uri;

import com.example.truonghoc.domain.ui.HasUri;
import com.example.truonghoc.domain.ui.IImage;

public class UriImage implements IImage, HasUri {

    private Uri mUri;

    public UriImage(Uri uri) {
        this.mUri = uri;
    }

    @Override
    public Uri getUri() {
        return mUri;
    }
}