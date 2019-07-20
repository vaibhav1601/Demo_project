package com.ts.demo_project.service;

import com.google.gson.JsonElement;
import com.ts.demo_project.model.response.LoginRequest;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface ApiService {

    @POST("login")
    Observable<JsonElement> getLogin(@HeaderMap Map<String, String> headerMap, @Body LoginRequest loginRequest);

}
