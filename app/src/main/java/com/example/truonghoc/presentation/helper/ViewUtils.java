package com.example.truonghoc.presentation.helper;

import android.view.View;
import android.widget.TextView;

import androidx.core.util.Consumer;

import com.example.truonghoc.domain.ui.ValidateAble;
import com.example.truonghoc.presentation.exception.InvalidParamException;

public class ViewUtils {
    public static void show(View view, boolean isShow) {
        int nextState = isShow ? View.VISIBLE : View.GONE;
        if (view.getVisibility() == nextState) return;
        view.setVisibility(nextState);
    }

    public static <T extends View> void show(T view, boolean isShow, Consumer<T> consumer) {
        show(view, isShow);
        if (view.getVisibility() == View.VISIBLE) {
            consumer.accept(view);
        }
    }

    public static <T extends View> void show(T view, ValidateAble validateAble) {
        boolean isNotValid = !validateAble.isValid();
        show(view, isNotValid);
        if (isNotValid && view instanceof TextView) {
            ((TextView) view).setText(validateAble.getMessage());
        }
    }

    public static void setActivated(View view, boolean activated) {
        if (view.isActivated() == activated) return;
        view.setActivated(activated);
    }

    public static void showError(View root, InvalidParamException it) {
        TextView errorView = root.findViewById(it.viewId);
        errorView.setText(it.getMessage());
        show(errorView, true);
    }
}
