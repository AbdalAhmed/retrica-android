package com.fobid.retrica.services;

import android.support.annotation.NonNull;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class ApiClient implements ApiClientType {

    private final ApiService apiService;

    public ApiClient(final @NonNull ApiService apiService) {
        this.apiService = apiService;
    }
}
