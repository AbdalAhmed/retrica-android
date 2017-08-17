package com.fobid.retrica.ui.presenters;

import android.support.annotation.NonNull;

import com.fobid.retrica.libs.Environment;
import com.fobid.retrica.models.GitHubUser;
import com.fobid.retrica.services.ApiClientType;
import com.fobid.retrica.services.apiresponses.ErrorEnvelope;
import com.fobid.retrica.ui.adapters.GitHubAdapter;
import com.fobid.retrica.ui.viewholders.GitHubViewHolder;
import com.fobid.retrica.ui.views.GitHubView;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.realm.Realm;

import static com.fobid.retrica.libs.rx.transformers.Transformers.pipeApiErrorsTo;
import static com.fobid.retrica.libs.rx.transformers.Transformers.pipeErrorsTo;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class GitHubPresenter extends BasePresenter<GitHubView> implements GitHubAdapter.Delegate {

    private final ApiClientType client;

    public GitHubPresenter(@NonNull Environment environment) {
        super(environment);

        client = environment.apiClient;

        showWebViewer = urlClick;

        syncClick
                .switchMap(__ -> users()
                        .doOnSubscribe(disposable -> showLoading.onNext(0))
                        .doAfterTerminate(() -> hideLoading.onNext(0)))
                .doOnNext(users -> {
                    final Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(
                            realm1 -> realm1.createOrUpdateAllFromJson(GitHubUser.class, new Gson().toJson(users)));
                    realm.close();
                })
                .compose(bindToLifecycle())
                .subscribe();

        syncClick.onNext(0);

        genericUsersError()
                .mergeWith(usersError.map(Throwable::getMessage))
                .compose(bindToLifecycle())
                .subscribe(showToast);
    }

    private
    @NonNull
    Observable<List<GitHubUser>> users() {
        return client.users()
                .compose(pipeApiErrorsTo(usersApiError))
                .compose(pipeErrorsTo(usersError));
    }

    private final PublishSubject<Object> syncClick = PublishSubject.create();
    private final PublishSubject<String> urlClick = PublishSubject.create();
    private Observable<String> showWebViewer;
    private final PublishSubject<Throwable> usersError = PublishSubject.create();
    private final PublishSubject<ErrorEnvelope> usersApiError = PublishSubject.create();

    private final BehaviorSubject<Object> showLoading = BehaviorSubject.create();
    private final BehaviorSubject<Object> hideLoading = BehaviorSubject.create();
    private final BehaviorSubject<String> showToast = BehaviorSubject.create();

    public void syncClick() {
        syncClick.onNext(0);
    }

    public
    @NonNull
    Observable<String> showWebViewer() {
        return showWebViewer;
    }

    @Override
    public void gitHugViewHolderUrlClick(@NonNull GitHubViewHolder viewHolder, @NonNull String url) {
        urlClick.onNext(url);
    }

    public
    @NonNull
    Observable<Object> showLoading() {
        return showLoading;
    }

    public
    @NonNull
    Observable<Object> hideLoading() {
        return hideLoading;
    }

    public
    @NonNull
    Observable<String> showToast() {
        return showToast;
    }

    public
    @NonNull
    Observable<String> genericUsersError() {
        return usersApiError
                .map(errorEnvelope -> errorEnvelope.message);
    }
}
