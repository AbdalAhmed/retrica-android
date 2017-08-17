package com.fobid.retrica.libs.rx.transformers;

import android.support.annotation.NonNull;

import com.fobid.retrica.services.apiresponses.ErrorEnvelope;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by android01 on 2017. 8. 17..
 */

public final class Transformers {

    private Transformers() {
    }

    public static <S, T> TakeWhenTransformer<S, T> takeWhen(final @NonNull Observable<T> when) {
        return new TakeWhenTransformer<>(when);
    }

    public static <T> NeverErrorTransformer<T> pipeErrorsTo(final @NonNull PublishSubject<Throwable> errorSubject) {
        return new NeverErrorTransformer<>(errorSubject::onNext);
    }

    public static <T> NeverApiErrorTransformer<T> pipeApiErrorsTo(final @NonNull PublishSubject<ErrorEnvelope> errorSubject) {
        return new NeverApiErrorTransformer<>(errorSubject::onNext);
    }
}
