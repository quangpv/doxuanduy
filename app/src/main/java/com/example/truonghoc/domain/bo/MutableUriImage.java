package com.example.truonghoc.domain.bo;

import android.net.Uri;

import com.example.truonghoc.domain.ui.IImage;
import com.example.truonghoc.domain.ui.UriSettable;
import com.example.truonghoc.domain.ui.HasUri;

public class MutableUriImage implements IImage, HasUri, UriSettable {

    private Uri mUri;

    public MutableUriImage(Uri uri) {
        mUri = uri;
    }

    @Override
    public Uri getUri() {
        return mUri;
    }

    @Override
    public void setUri(Uri uri) {
        mUri = uri;
    }
}
