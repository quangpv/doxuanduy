package com.example.truonghoc.presentation.helper;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.example.truonghoc.domain.ui.ValidateAble;
import com.example.truonghoc.domain.ui.Subject;


public class ObserveUtils {
    public static void bindValidation(LifecycleOwner owner, CharSequence observable, Consumer<ValidateAble> callback) {
        if (!(observable instanceof Subject) || !(observable instanceof ValidateAble)) {
            return;
        }
        Subject subject = (Subject) observable;
        ValidateAble validateAble = (ValidateAble) observable;
        AutoCloseable closeable = subject.subscribe(() -> callback.accept(validateAble));
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
        final boolean[] isAllValid = {isAllValid(fields)};
        Runnable onChange = () -> {
            boolean nextInvalid = isAllValid(fields);
            if (nextInvalid != isAllValid[0]) {
                isAllValid[0] = nextInvalid;
                consumer.accept(isAllValid[0]);
            }
        };
        for (CharSequence field : fields) {
            if (field instanceof ValidateAble && field instanceof Subject) {
                ((Subject) field).subscribe(onChange);
            }
        }
    }

    public static boolean isAllValid(CharSequence... fields) {
        boolean isAllValid = true;
        for (CharSequence field : fields) {
            if (field instanceof ValidateAble) {
                isAllValid = isAllValid && ((ValidateAble) field).isValid();
            }
        }
        return isAllValid;
    }
}
