package com.example.truonghoc.presentation.helper;

import com.example.truonghoc.domain.ui.ValidateAble;

public class ValidationUtils {

    public static boolean isAllValid(CharSequence... fields) {
        for (CharSequence field : fields) {
            if (field instanceof ValidateAble) {
                boolean isValid = ((ValidateAble) field).isValid();
                if (!isValid) return false;
            }
        }
        return true;
    }
}
