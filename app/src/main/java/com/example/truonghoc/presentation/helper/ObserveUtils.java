package com.example.truonghoc.presentation.helper;

import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.example.truonghoc.domain.ui.ValidateAble;
import com.example.truonghoc.domain.ui.Signal;
import com.example.truonghoc.presentation.model.BiConsumer;


public class ObserveUtils {
    public static void bindValidation(LifecycleOwner owner, CharSequence observable, Consumer<ValidateAble> callback) {
        if (!(observable instanceof Signal) || !(observable instanceof ValidateAble)) {
            return;
        }
        Signal signal = (Signal) observable;
        ValidateAble validateAble = (ValidateAble) observable;
        AutoCloseable closeable = signal.subscribe(() -> callback.accept(validateAble));
        LifecycleOwner owner1 = owner instanceof Fragment ? ((Fragment) owner).getViewLifecycleOwner() : owner;

        owner1.getLifecycle().addObserver(new DefaultLifecycleObserver() {
            @Override
            public void onDestroy(@NonNull LifecycleOwner owner) {
                owner.getLifecycle().removeObserver(this);
                try {
                    closeable.close();
                } catch (Exception ignored) {
                }
            }
        });

    }

    public static void combineValidation(Consumer<Boolean> consumer, CharSequence... fields) {
        final boolean[] isAllValid = {ValidationUtils.isAllValid(fields)};
        Runnable onChange = () -> {
            boolean nextInvalid = ValidationUtils.isAllValid(fields);
            if (nextInvalid != isAllValid[0]) {
                isAllValid[0] = nextInvalid;
                consumer.accept(isAllValid[0]);
            }
        };
        for (CharSequence field : fields) {
            if (field instanceof ValidateAble && field instanceof Signal) {
                ((Signal) field).subscribe(onChange);
            }
        }
    }
}
