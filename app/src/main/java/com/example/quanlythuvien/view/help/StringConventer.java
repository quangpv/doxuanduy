package com.example.quanlythuvien.view.help;

import java.text.Normalizer;
import java.util.Locale;

public class StringConventer {

    public static String xoaDauChu(String noiDungDau) {
        return Normalizer.normalize(noiDungDau, Normalizer.Form.NFD)
                .toLowerCase(Locale.ROOT)
                .replaceAll("\\p{M}", "")
                .replace('đ', 'd')
                .replace('Đ', 'D');
    }
}
