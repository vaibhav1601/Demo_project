package com.ts.demo_project.repository;

import com.google.gson.JsonElement;
import com.ts.demo_project.api.ApiServiceGenerator;
import com.ts.demo_project.model.response.LoginRequest;
import com.ts.demo_project.service.ApiService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

public class LoginRepo {


    Map<String, String> map = new HashMap<>();
    private ApiService mApiService;

    public LoginRepo() {
        this.mApiService = ApiServiceGenerator.createService(ApiService.class);

    }


    public Observable<JsonElement> executeLogin(LoginRequest loginRequest) {

        return mApiService.getLogin(map, loginRequest);
    }


}
