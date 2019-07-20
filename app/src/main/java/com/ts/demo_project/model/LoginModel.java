package com.ts.demo_project.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.ts.demo_project.BR;
import com.ts.demo_project.R;

public class LoginModel extends BaseObservable {

    public ObservableField<Integer> emailError = new ObservableField<>();
    public ObservableField<Integer> passwordError = new ObservableField<>();
    private String mEmail;
    private boolean isRememberMe = true;

    private String mPassword;


    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
        notifyPropertyChanged(BR.valid);
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;

        notifyPropertyChanged(BR.valid);
    }

    @Bindable
    public boolean isValid() {

        boolean valid = isEmailValid(false);
        valid = isPasswordValid(false) && valid;
        return valid;


    }


    public boolean isEmailValid(boolean setMessage) {

        if (mEmail != null && mEmail.length() > 1) {
            emailError.set(null);
            return true;
        } else {
            if (setMessage) {
                emailError.set(R.string.please_enter_mobile_userid);
                return false;
            } else if (TextUtils.isEmpty(mEmail))
                emailError.set(R.string.please_enter_mobile_userid);
            return false;


        }

    }


    public boolean isPasswordValid(boolean setMessage) {
        if (mPassword != null && mPassword.length() > 1) {
            passwordError.set(null);
            return true;
        } else {
            if (setMessage) {
                passwordError.set(R.string.please_enter_password);
                return false;
            } else if (TextUtils.isEmpty(mPassword))
                passwordError.set(R.string.please_enter_password);
            return false;

        }
    }


}
