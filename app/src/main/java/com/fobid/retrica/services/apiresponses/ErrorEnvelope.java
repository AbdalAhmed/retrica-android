package com.fobid.retrica.services.apiresponses;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fobid.retrica.services.ApiException;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class ErrorEnvelope {

    public static
    @Nullable
    ErrorEnvelope fromThrowable(final @NonNull Throwable t) {
        if (t instanceof ApiException) {
            final ApiException exception = (ApiException) t;
            return exception.errorEnvelope();
        }

        return null;
    }
}
