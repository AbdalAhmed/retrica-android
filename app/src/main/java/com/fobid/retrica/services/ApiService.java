package com.fobid.retrica.services;

import com.fobid.retrica.models.GitHubUser;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by android01 on 2017. 8. 17..
 */

public interface ApiService {

    @GET("users")
    Observable<Response<List<GitHubUser>>> fetchUsers();
}
