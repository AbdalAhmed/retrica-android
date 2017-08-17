package com.fobid.retrica.libs.rx.transformers;

import android.support.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;

/**
 * Created by android01 on 2017. 8. 17..
 */

public final class NeverErrorTransformer<T> implements ObservableTransformer<T, T> {

    private final
    @Nullable
    Consumer<Throwable> errorAction;

    protected NeverErrorTransformer(final @Nullable Consumer<Throwable> errorAction) {
        this.errorAction = errorAction;
    }

    @Override
    public ObservableSource<T> apply(@io.reactivex.annotations.NonNull Observable<T> upstream) {
        return upstream
                .doOnError(e -> {
                    if (errorAction != null) {
                        errorAction.accept(e);
                    }
                })
                .onErrorResumeNext(Observable.empty());
    }
}
