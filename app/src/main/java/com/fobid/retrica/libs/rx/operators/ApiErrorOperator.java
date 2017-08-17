package com.fobid.retrica.libs.rx.operators;

import com.fobid.retrica.services.ApiException;
import com.fobid.retrica.services.ResponseException;
import com.fobid.retrica.services.apiresponses.ErrorEnvelope;
import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.ObservableOperator;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class ApiErrorOperator<T> implements ObservableOperator<T, Response<T>> {

    @Override
    public Observer<? super Response<T>> apply(@NonNull Observer<? super T> observer) throws Exception {
        return new Observer<Response<T>>() {

            @Override
            public void onError(Throwable e) {
                observer.onError(e);
            }

            @Override
            public void onComplete() {
                observer.onComplete();
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(Response<T> response) {
                if (!response.isSuccessful()) {
                    try {
                        final ErrorEnvelope envelope = new Gson().fromJson(response.errorBody().string(), ErrorEnvelope.class);
                        observer.onError(new ApiException(envelope, response));
                    } catch (final @NonNull IOException e) {
                        observer.onError(new ResponseException(response));
                    }
                } else {
                    observer.onNext(response.body());
                    observer.onComplete();
                }
            }
        };
    }
}
