package com.fobid.retrica.libs.rx.transformers;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;

/**
 * Created by android01 on 2017. 8. 17..
 */

public final class TakeWhenTransformer<S, T> implements ObservableTransformer<S, S> {

    @android.support.annotation.NonNull
    private final Observable<T> when;

    public TakeWhenTransformer(final @android.support.annotation.NonNull Observable<T> when) {
        this.when = when;
    }

    @Override
    public ObservableSource<S> apply(@NonNull Observable<S> upstream) {
        return when.withLatestFrom(upstream, (__, x) -> x);
    }
}
