package com.fobid.retrica.libs.rx.transformers;

import android.support.annotation.NonNull;

import io.reactivex.Observable;

/**
 * Created by android01 on 2017. 8. 17..
 */

public final class Transformers {

    private Transformers() {
    }

    public static <S, T> TakeWhenTransformer<S, T> takeWhen(final @NonNull Observable<T> when) {
        return new TakeWhenTransformer<>(when);
    }
}
