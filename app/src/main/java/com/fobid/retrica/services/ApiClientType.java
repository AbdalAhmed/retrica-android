package com.fobid.retrica.services;

import com.fobid.retrica.models.GitHubUser;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by android01 on 2017. 8. 17..
 */

public interface ApiClientType {

    Observable<List<GitHubUser>> users();
}
