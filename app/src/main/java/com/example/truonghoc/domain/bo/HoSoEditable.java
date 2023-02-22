package com.example.truonghoc.domain.bo;

import androidx.annotation.NonNull;

import com.example.truonghoc.data.model.ThongTinTruongHocEntity;
import com.example.truonghoc.domain.ui.IHoSoEditable;
import com.example.truonghoc.domain.ui.Signal;
import com.example.truonghoc.domain.ui.ValidateAble;

import java.util.regex.Pattern;

public class HoSoEditable implements IHoSoEditable {
    private final ValidateAbleChars mPhoneNumber;
    private final ValidateAbleChars mAddress;
    private final ValidateAbleChars mName;

    public HoSoEditable(ThongTinTruongHocEntity truongHocEntity) {
        mPhoneNumber = new PhoneNumberValidationChars(truongHocEntity.getSdtTruong());
        mAddress = new ValidateAbleChars(truongHocEntity.getDiaChiTruong());
        mName = new ValidateAbleChars(truongHocEntity.getTenTruong());
    }

    @Override
    public void setName(String name) {
        mName.setValue(name);
    }

    @Override
    public void setAddress(String address) {
        mAddress.setValue(address);
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber.setValue(phoneNumber);
    }

    @Override
    public CharSequence getPhoneNumber() {
        return mPhoneNumber;
    }

    @Override
    public CharSequence getAddress() {
        return mAddress;
    }

    @Override
    public CharSequence getName() {
        return mName;
    }

    static class PhoneNumberValidationChars extends ValidateAbleChars {
        private final Pattern phonePattern = Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$");

        public PhoneNumberValidationChars(String original) {
            super(original);
        }

        @Override
        public boolean isValid() {
            if (length() == 0) {
                mErrorMessage = "Số điện thoại không được để trống";
                return false;
            }
            if (length() < 6) {
                mErrorMessage = "Số điện thoại có chiều dài ít nhất 6 ký tự";
                return false;
            }
            if (!phonePattern.matcher(mValue).matches()) {
                mErrorMessage = "Số điện thoại không đúng định dạng";
                return false;
            }
            return true;
        }
    }

    static class ValidateAbleChars extends Signal.MultipleSubscription implements CharSequence, ValidateAble {

        protected String mErrorMessage = "";

        public ValidateAbleChars(String original) {
            this.mValue = original;
        }

        protected String mValue;

        void setValue(String value) {
            mValue = value;
            emit();
        }

        @Override
        public int length() {
            return mValue.length();
        }

        @Override
        public char charAt(int index) {
            return mValue.charAt(index);
        }

        @NonNull
        @Override
        public CharSequence subSequence(int start, int end) {
            return mValue.subSequence(start, end);
        }

        @NonNull
        @Override
        public String toString() {
            return mValue;
        }

        @Override
        public boolean isValid() {
            boolean isValid = length() > 0;
            if (!isValid) mErrorMessage = "Không được để trống";
            return isValid;
        }

        @Override
        public String getMessage() {
            return mErrorMessage;
        }
    }
}