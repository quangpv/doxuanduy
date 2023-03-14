package com.example.quanlythuvien.view.help;
import com.example.quanlythuvien.domain.ui.IKiemTraGiaTriNhap;

public class CheckLoi {
    public static boolean checkLoi(CharSequence... list) {
        for (CharSequence chuoi : list) {
            if (chuoi instanceof IKiemTraGiaTriNhap) {
                boolean giaTri = ((IKiemTraGiaTriNhap)chuoi).kiemTraDieuKienNhap();
                if(!giaTri) return false;
            }
        }
        return true;
    }
}
