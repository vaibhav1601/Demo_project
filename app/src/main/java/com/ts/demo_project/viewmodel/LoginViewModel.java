package com.ts.demo_project.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ts.demo_project.model.LoginModel;
import com.ts.demo_project.model.response.LoginRequest;
import com.ts.demo_project.repository.LoginRepo;
import com.ts.demo_project.utils.Api_response;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends AndroidViewModel {
    public final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<Api_response> mLiveData = new MutableLiveData<>();
    private LoginModel login;
    private LoginRepo mLoginRepo;
    private View.OnFocusChangeListener onFocusEmail;
    private View.OnFocusChangeListener onFocusPassword;
    private MutableLiveData<LoginModel> buttonClick = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mLoginRepo = new LoginRepo();
    }


    public void init() {
        login = new LoginModel();
        onFocusEmail = new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focused) {
                EditText et = (EditText) view;
                if (et.getText().length() > 0 && !focused) {
                    login.isEmailValid(true);
                }
            }
        };

        onFocusPassword = new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focused) {
                EditText et = (EditText) view;
                if (et.getText().length() > 0 && !focused) {
                    login.isPasswordValid(true);
                }
            }
        };
    }


    public MutableLiveData<Api_response> getLiveData() {
        return mLiveData;
    }


    public void hitLoginApi(LoginRequest loginRequest) {


        disposables.add(mLoginRepo.executeLogin(loginRequest).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> mLiveData.setValue(Api_response.loading()))
                .subscribe(
                        result -> mLiveData.setValue(Api_response.success(result)),
                        throwable -> mLiveData.setValue(Api_response.error(throwable))
                ));

    }


    public void onButtonClick() {
        if (login.isValid()) {
            LoginRequest mLoginRequest = new LoginRequest();
            buttonClick.setValue(login);

            if (!TextUtils.isEmpty(login.getPassword())) {
                mLoginRequest.setPassword(login.getPassword());
            }
            if (login.getEmail().matches("^[0-9]*$") && login.getEmail().length() == 10)
                mLoginRequest.setUsername(login.getEmail());
            else mLoginRequest.setUsername(login.getEmail());


            hitLoginApi(mLoginRequest);
        }

    }


    public String getUserName() {
        return login.getEmail();
    }


    public String getPassword() {
        return login.getPassword();
    }


}
