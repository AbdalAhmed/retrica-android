package com.fobid.retrica.services;

import android.support.annotation.NonNull;

import com.fobid.retrica.services.apiresponses.ErrorEnvelope;

import retrofit2.Response;

/**
 * Created by android01 on 2017. 8. 17..
 */

public final class ApiException extends ResponseException {

    private final ErrorEnvelope errorEnvelope;

    public ApiException(final @NonNull ErrorEnvelope errorEnvelope, @NonNull Response response) {
        super(response);
        this.errorEnvelope = errorEnvelope;
    }

    public
    @NonNull
    ErrorEnvelope errorEnvelope() {
        return errorEnvelope;
    }
}
