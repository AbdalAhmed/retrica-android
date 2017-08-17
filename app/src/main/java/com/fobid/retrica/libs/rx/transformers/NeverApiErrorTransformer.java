package com.fobid.retrica.libs.rx.transformers;

import android.support.annotation.Nullable;

import com.fobid.retrica.services.apiresponses.ErrorEnvelope;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;

/**
 * Created by android01 on 2017. 8. 17..
 */

final class NeverApiErrorTransformer<T> implements ObservableTransformer<T, T> {
    private final
    @Nullable
    Consumer<ErrorEnvelope> errorAction;

    protected NeverApiErrorTransformer(final @Nullable Consumer<ErrorEnvelope> errorAction) {
        this.errorAction = errorAction;
    }

    @Override
    public ObservableSource<T> apply(@io.reactivex.annotations.NonNull Observable<T> upstream) {
        return upstream
                .doOnError(e -> {
                    final ErrorEnvelope env = ErrorEnvelope.fromThrowable(e);
                    if (env != null && errorAction != null) {
                        errorAction.accept(env);
                    }
                })
                .onErrorResumeNext(e -> {
                    if (ErrorEnvelope.fromThrowable(e) == null) {
                        return Observable.error(e);
                    } else {
                        return Observable.empty();
                    }
                });
    }
}
