package com.fobid.retrica.services;

import android.support.annotation.NonNull;

import retrofit2.Response;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class ResponseException extends RuntimeException {

    private final Response response;

    public ResponseException(final @NonNull Response response) {
        this.response = response;
    }

    public
    @NonNull
    Response response() {
        return response;
    }
}
