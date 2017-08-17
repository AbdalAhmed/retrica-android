package com.fobid.retrica.libs.rx.operators;

import android.support.annotation.NonNull;

/**
 * Created by android01 on 2017. 8. 17..
 */

public final class Operators {

    private Operators() {
    }

    public static
    @NonNull
    <T> ApiErrorOperator<T> apiError() {
        return new ApiErrorOperator<>();
    }
}
