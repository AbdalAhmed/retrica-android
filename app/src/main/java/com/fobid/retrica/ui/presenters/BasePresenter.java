package com.fobid.retrica.ui.presenters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.util.Pair;

import com.fobid.retrica.libs.Environment;
import com.fobid.retrica.ui.activities.BaseActivity;
import com.fobid.retrica.ui.views.BaseView;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class BasePresenter<View extends BaseView> {

    private final BehaviorSubject<ActivityEvent> lifecycle = BehaviorSubject.create();

    private final PublishSubject<View> viewChange = PublishSubject.create();
    private final Observable<View> view = viewChange.filter(v -> v != null);

    private final PublishSubject<Intent> intent = PublishSubject.create();


    public BasePresenter(final @NonNull Environment environment) {
    }

    public void intent(final @NonNull Intent intent) {
        this.intent.onNext(intent);
    }

    @CallSuper
    public void onCreate(Context context, Bundle savedInstanceState) {
        lifecycle.onNext(ActivityEvent.CREATE);
    }

    @CallSuper
    public void onStart() {
        lifecycle.onNext(ActivityEvent.START);
    }

    @CallSuper
    public void onResume() {
        lifecycle.onNext(ActivityEvent.RESUME);
    }

    @CallSuper
    public void onPause() {
        lifecycle.onNext(ActivityEvent.PAUSE);
    }

    @CallSuper
    public void onStop() {
        lifecycle.onNext(ActivityEvent.STOP);
    }

    @CallSuper
    public void onDestroy() {
        lifecycle.onNext(ActivityEvent.DESTROY);
    }

    protected
    @NonNull
    Observable<Intent> intent() {
        return intent;
    }

    public final <T> ObservableTransformer<T, T> bindUntilEvent(final ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycle, event);
    }

    public
    @NonNull
    <T> ObservableTransformer<T, T> bindToLifecycle() {
        return source -> source.takeUntil(
                view.switchMap(v -> v.lifecycle().map(e -> Pair.create(v, e)))
                        .filter(ve -> isFinished(ve.first, ve.second))
        );
    }

    private boolean isFinished(final @NonNull View view, final @NonNull ActivityEvent event) {

        if (view instanceof BaseActivity) {
            return event == ActivityEvent.DESTROY && ((BaseActivity) view).isFinishing();
        }

        return event == ActivityEvent.DESTROY;
    }
}
