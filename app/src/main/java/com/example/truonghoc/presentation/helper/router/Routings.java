package com.example.truonghoc.presentation.helper.router;

import com.example.truonghoc.presentation.feature.themhocsinh.ThemHocSinhActivity;
import com.example.truonghoc.presentation.feature.thongtinhocsinh.ThongTinHocSinhActivity;

public class Routings {

    public static class TaoMoiHocSinh implements Router.Routing {

        @Override
        public Class<?> getDestinationClass() {
            return ThemHocSinhActivity.class;
        }
    }

    public static class ThongTinHocSinh implements Router.Routing {
        public final String id;

        public ThongTinHocSinh(String id) {
            this.id = id;
        }

        @Override
        public Class<?> getDestinationClass() {
            return ThongTinHocSinhActivity.class;
        }
    }
}
