package com.fobid.retrica.services;

import android.support.annotation.NonNull;

import com.fobid.retrica.libs.rx.operators.ApiErrorOperator;
import com.fobid.retrica.libs.rx.operators.Operators;
import com.fobid.retrica.models.GitHubUser;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class ApiClient implements ApiClientType {

    private final ApiService apiService;

    public ApiClient(final @NonNull ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<List<GitHubUser>> users() {
        return apiService.fetchUsers()
                .lift(apiErrorOperator())
                .subscribeOn(Schedulers.io());
    }

    private
    @NonNull
    <T> ApiErrorOperator<T> apiErrorOperator() {
        return Operators.apiError();
    }
}
