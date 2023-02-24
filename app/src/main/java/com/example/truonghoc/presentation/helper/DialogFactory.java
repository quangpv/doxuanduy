package com.example.truonghoc.presentation.helper;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AlertDialog;

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
    public static AlertDialog.Builder showDialog(Context context,String s,Runnable onAccept, Runnable onCancel){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(s);
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
}
