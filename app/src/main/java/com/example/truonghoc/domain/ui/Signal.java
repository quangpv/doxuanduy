package com.example.truonghoc.domain.ui;

import java.util.ArrayList;
import java.util.List;

public interface Signal {
    AutoCloseable subscribe(Runnable subscription);

    void emit();

    class SingleSubscription implements Signal {

        private Runnable mSubscription;

        @Override
        public AutoCloseable subscribe(Runnable subscription) {
            mSubscription = subscription;

            return () -> mSubscription = null;
        }

        @Override
        public void emit() {
            if (mSubscription != null) mSubscription.run();
        }
    }

    class MultipleSubscription implements Signal {

        private final List<Runnable> mSubscriptions = new ArrayList<>();

        @Override
        public AutoCloseable subscribe(Runnable subscription) {
            mSubscriptions.add(subscription);
            return () -> mSubscriptions.remove(subscription);
        }

        @Override
        public void emit() {
            for (Runnable mSubscription : mSubscriptions) {
                mSubscription.run();
            }
        }
    }
}
