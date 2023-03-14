package com.example.quanlythuvien.view.base;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quanlythuvien.domain.khodata.SachKho;
import com.example.quanlythuvien.domain.khodata.ThuThuKho;
import com.example.quanlythuvien.domain.ui.IChayRunnalble;
import com.example.quanlythuvien.view.help.AppExecutors;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Future;


public abstract class BaseViewModel extends ViewModel {
    public AppExecutors appExecutors = AppExecutors.getInstance();
    public ThuThuKho thuThuKho = ThuThuKho.getInstance();
    public SachKho sachKho = SachKho.getInstance();
    public MutableLiveData<Throwable> thongBao = new MutableLiveData<>();

    @SuppressWarnings("all")
    public void chayCoLoiPhanHoi(LiveData<? extends Throwable> loi, IChayRunnalble runnable) {
        Future future = appExecutors.run(() -> {
            try {
                runnable.run();
            } catch (Throwable e) {
                ((MutableLiveData) loi).postValue(e);
                e.printStackTrace();
            }
        });
        addCloseable(new Closeable() {
            @Override
            public void close() throws IOException {
                future.cancel(true);
            }
        });
    }

    public void chayKhongPhanHoi(IChayRunnalble runnalble) {
        chayCoLoiPhanHoi(null, runnalble);
    }
}


