package com.example.quanlythuvien.view.base;


import androidx.lifecycle.ViewModel;
import com.example.quanlythuvien.domain.khodata.SachKho;
import com.example.quanlythuvien.domain.khodata.ThuThuKho;
import com.example.quanlythuvien.view.help.AppExecutors;

public abstract class BaseViewModel extends ViewModel {
    public AppExecutors appExecutors = AppExecutors.getInstance();
    public ThuThuKho thuThuKho = ThuThuKho.getInstance();
    public SachKho sachKho = SachKho.getInstance();
}


