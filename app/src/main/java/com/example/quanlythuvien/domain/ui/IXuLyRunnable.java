package com.example.quanlythuvien.domain.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public interface IXuLyRunnable {
    AutoCloseable dangKy(Runnable runnable);

    void chayRunnable();

      class NhieuRunnable implements IXuLyRunnable{
        List<Runnable> danhSach = new ArrayList<>();


        @Override
        public AutoCloseable dangKy(Runnable runnable) {
            danhSach.add(runnable);
            return new AutoCloseable() {
                @Override
                public void close() throws Exception {
                    danhSach.remove(runnable);
                }
            };
        }

        @Override
        public void chayRunnable() {
            for (Runnable runnable: danhSach){
                runnable.run();
            }
        }
    }
}
