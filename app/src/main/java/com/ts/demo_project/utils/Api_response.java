package com.ts.demo_project.utils;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class Api_response {

    public final Status status;

    @Nullable
    public final Object data;

    @android.support.annotation.Nullable
    public final Throwable error;


    private Api_response(Status status, @Nullable Object data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static Api_response loading() {
        return new Api_response(Status.LOADING, null, null);
    }

    public static Api_response success(@NonNull Object data) {
        return new Api_response(Status.SUCCESS, data, null);
    }

    public static Api_response error(@android.support.annotation.NonNull Throwable error) {
        return new Api_response(Api_response.Status.ERROR, null, error);
    }


    public enum Status {
        LOADING,
        SUCCESS,
        ERROR,
        COMPLETED
    }
}
