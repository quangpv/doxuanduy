package com.example.truonghoc.presentation.helper;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import androidx.core.util.Consumer;

public class DialogFactory {
    public static AlertDialog.Builder createHuySuaThongTinHocSinh(Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage("Hủy Sửa Thông Tin ?");
        dialog.setNegativeButton("Yes", (d, which) -> {
            if (context instanceof Activity) ((Activity) context).finish();
        });
        dialog.setPositiveButton("No", (d, which) -> {
        });
        return dialog;
    }

    public static AlertDialog.Builder createXacNhanSuaThongTinHocSinh(Context context, Runnable onAccept, Runnable onCancel) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage("Lưu thông tin thay đổi?");
        dialog.setNegativeButton("Yes", (d, which) -> {
            onAccept.run();
            d.dismiss();
        });
        dialog.setPositiveButton("No", (d, which) -> {
            onCancel.run();
            d.dismiss();
        });
        return dialog;
    }

    public static AlertDialog.Builder createTakePhotoOrGallery(Context context, Consumer<Boolean> consumer) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage("Chọn ảnh");
        dialog.setNegativeButton("Chụp ảnh", (d, which) -> {
            consumer.accept(true);
            d.dismiss();
        });
        dialog.setPositiveButton("Mở thư mục ảnh", (d, which) -> {
            consumer.accept(false);
            d.dismiss();
        });
        return dialog;
    }
}
