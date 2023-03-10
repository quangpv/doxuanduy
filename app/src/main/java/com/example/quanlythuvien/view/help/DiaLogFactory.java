package com.example.quanlythuvien.view.help;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class DiaLogFactory {
    public static AlertDialog.Builder showDialog(Context context, String s, Runnable onAccept, Runnable onCancel){
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
