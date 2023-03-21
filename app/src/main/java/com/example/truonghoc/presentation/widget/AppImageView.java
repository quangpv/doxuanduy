package com.example.truonghoc.presentation.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.truonghoc.R;
import com.example.truonghoc.domain.ui.HasBitmap;
import com.example.truonghoc.domain.ui.HasImage;
import com.example.truonghoc.domain.ui.HasResourceId;
import com.example.truonghoc.domain.ui.HasUri;
import com.example.truonghoc.domain.ui.HasUrl;
import com.example.truonghoc.domain.ui.IImage;

public class AppImageView extends AppCompatImageView {
    private boolean mIsCircleCrop = false;

    public AppImageView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public AppImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AppImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AppImageView);

            mIsCircleCrop = typedArray.getBoolean(R.styleable.AppImageView_circleCrop, false);
            typedArray.recycle();
        }
    }

    public void setImage(IImage image) {
        if (image instanceof HasImage) {
            setImage(((HasImage) image).getImage());
            return;
        }
        RequestManager glide = Glide.with(this);
        RequestBuilder<Drawable> builder = null;

        if (image instanceof HasUri) {
            Uri uri = ((HasUri) image).getUri();
            if (uri != null) builder = glide.load(uri);
        } else if (image instanceof HasUrl) {
            builder = glide.load(((HasUrl) image).getUrl());
        } else if (image instanceof HasResourceId) {
            builder = glide.load(((HasResourceId) image).getResourceId());
        } else if (image instanceof HasBitmap) {
            builder = glide.load(((HasBitmap) image).getBitmap());
        }

        if (builder == null) {
            return;
        }
        if (mIsCircleCrop) {
            builder = builder.transform(new CircleCrop());
        }
        builder = builder.skipMemoryCache(true);
        builder.into(this);
    }
}
