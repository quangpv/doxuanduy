package com.example.quanlythuvien.domain.khodata;

import com.example.quanlythuvien.data.ThuVienDao;
import com.example.quanlythuvien.data.ThuVienDataBase;
import com.example.quanlythuvien.domain.Sach;
import com.example.quanlythuvien.domain.ui.ISachGet;
import com.example.quanlythuvien.domain.ui.ISachItemList;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SachKho {
    private static final SachKho sachKho = new SachKho();
    ThuVienDao thuVienDao = ThuVienDataBase.getInstance().ThuVienDao();

    public static SachKho getInstance() {
        return sachKho;
    }


    public List<ISachItemList> laySach(String s) {
        String keySearch = s.toLowerCase(Locale.ROOT);
        List<Sach> sachList = thuVienDao.layTatCaSach();
        List<ISachItemList> sachLoc = new ArrayList<>();
        for (Sach sach : sachList) {
            if (soSanh(keySearch, sach)) sachLoc.add(taoItemSach(sach));
        }
        return sachLoc;
    }

    private ISachItemList taoItemSach(Sach sach) {
        return new ISachItemList() {
            @Override
            public String getMaSach() {
                return sach.getMaSach();
            }

            @Override
            public String getTenSach() {
                return sach.getTenSach();
            }

            @Override
            public int getTongSach() {
                return sach.getTong();
            }

            @Override
            public int getTonSach() {
                return sach.getSoLuongConLai();
            }
        };
    }

    private boolean soSanh(String keySearch, Sach sach) {
        if (keySearch.isEmpty()) return true;
        String tenSach = sach.getTenSach().toLowerCase(Locale.ROOT);
        return tenSach.contains(keySearch);
    }

    public void xoaSachTheoMaSach(String idSach) {
        thuVienDao.xoaSachTheoId(idSach);
    }

    public boolean kiemTraSachTonTaiTheoMa(String maSach) {
        return thuVienDao.kiemTraSachTonTai(maSach);
    }

    public void themSachVaoKho(ISachGet iSachGet) {
        Sach sach = new Sach(iSachGet.getMaSach().toString(), iSachGet.getTenSach().toString(), iSachGet.getLoaiSach(), iSachGet.getTenTacGia(), iSachGet.getNhaXuatBan(), iSachGet.getNamXuatBan(), iSachGet.getTongSach());
        thuVienDao.themSach(sach);
    }

    public ISachGet laySachTheoMaSach(String maSach) {
        Sach sach = thuVienDao.laySachTheoMaSach(maSach);
        return new ISachGet() {
            @Override
            public String getMaSach() {
                return sach.getMaSach();
            }

            @Override
            public String getTenSach() {
                return sach.getTenSach();
            }

            @Override
            public String getLoaiSach() {
                return sach.getLoaiSach();
            }

            @Override
            public String getTenTacGia() {
                return sach.getTenTacGia();
            }

            @Override
            public String getNhaXuatBan() {
                return sach.getNhaXuatBan();
            }

            @Override
            public String getNamXuatBan() {
                return sach.getNamXuatBan();
            }

            @Override
            public int getTongSach() {
                return sach.getTong();
            }

            @Override
            public int getChoThue() {
                return sach.getChoThue();
            }
        };
    }

    public void capNhapSach(ISachGet iSach) {
        Sach sach = thuVienDao.laySachTheoMaSach(iSach.getMaSach().toString());
        Sach sachCapNhat = new Sach(iSach.getMaSach().toString(), iSach.getTenSach().toString(), iSach.getLoaiSach(), iSach.getTenTacGia(), iSach.getNhaXuatBan(), iSach.getNamXuatBan(), iSach.getTongSach());
        sachCapNhat.id = sach.id;
        thuVienDao.capNhatSach(sachCapNhat);
    }
}
