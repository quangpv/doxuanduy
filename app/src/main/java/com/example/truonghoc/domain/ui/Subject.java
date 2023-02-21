package com.example.truonghoc.domain.ui;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    AutoCloseable subscribe(Runnable subscription);

    void notifyChange();

    class SingleSubscription implements Subject {

        private Runnable mSubscription;

        @Override
        public AutoCloseable subscribe(Runnable subscription) {
            mSubscription = subscription;
            return () -> mSubscription = null;
        }

        @Override
        public void notifyChange() {
            if (mSubscription != null) mSubscription.run();
        }
    }

    class MultipleSubscription implements Subject {

        private List<Runnable> mSubscriptions = new ArrayList<>();

        @Override
        public AutoCloseable subscribe(Runnable subscription) {
            mSubscriptions.add(subscription);
            return () -> mSubscriptions.clear();
        }

        @Override
        public void notifyChange() {
            for (Runnable mSubscription : mSubscriptions) {
                mSubscription.run();
            }
        }
    }
}
