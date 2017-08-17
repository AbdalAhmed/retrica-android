package com.fobid.retrica.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;

import com.fobid.retrica.libs.qualifiers.RequiresPresenter;
import com.fobid.retrica.libs.utils.BundleUtils;
import com.fobid.retrica.libs.utils.PresenterManager;
import com.fobid.retrica.ui.presenters.BasePresenter;
import com.fobid.retrica.ui.views.BaseView;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class BaseActivity<Presenter extends BasePresenter> extends AppCompatActivity implements BaseView {

    private final PublishSubject<Object> back = PublishSubject.create();
    private final BehaviorSubject<ActivityEvent> lifecycle = BehaviorSubject.create();
    private static final String PRESENTER_KEY = "presenter";

    protected Presenter presenter;

    public Presenter presenter() {
        return presenter;
    }

    @Override
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycle.hide();
    }

    public <T> ObservableTransformer<T, T> bindUntilEvent(final ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycle, event);
    }

    public <T> ObservableTransformer<T, T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycle);
    }

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycle.onNext(ActivityEvent.CREATE);
        assignPresenter(savedInstanceState);
        presenter.intent(getIntent());
    }

    @CallSuper
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @CallSuper
    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        presenter.intent(intent);
    }

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();
        lifecycle.onNext(ActivityEvent.START);
        presenter.onStart();

        back.compose(bindUntilEvent(ActivityEvent.STOP))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(__ -> goBack(), Throwable::printStackTrace);
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        lifecycle.onNext(ActivityEvent.RESUME);
        presenter.onResume();
    }

    @CallSuper
    @Override
    protected void onPause() {
        lifecycle.onNext(ActivityEvent.PAUSE);
        presenter.onPause();
        super.onPause();
    }

    @CallSuper
    @Override
    protected void onStop() {
        lifecycle.onNext(ActivityEvent.STOP);
        presenter.onStop();
        super.onStop();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        lifecycle.onNext(ActivityEvent.DESTROY);
        super.onDestroy();

        if (isFinishing()) {
            if (presenter != null) {
                PresenterManager.getInstance().destroy(presenter);
                presenter = null;
            }
        }

    }

    @CallSuper
    @Override
    @Deprecated
    public void onBackPressed() {
        back();
    }

    public void back() {
        back.onNext(0);
    }

    @CallSuper
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        final Bundle viewModelEnvelope = new Bundle();
        if (presenter != null) {
            PresenterManager.getInstance().save(presenter, viewModelEnvelope);
        }

        outState.putBundle(PRESENTER_KEY, viewModelEnvelope);
    }

    protected
    @Nullable
    Pair<Integer, Integer> exitTransition() {
        return null;
    }

    protected final void startActivityWithTransition(final @NonNull Intent intent, final @AnimRes int enterAnim,
                                                     final @AnimRes int exitAnim) {
        startActivity(intent);
        overridePendingTransition(enterAnim, exitAnim);
    }

    private void goBack() {
        super.onBackPressed();

        final Pair<Integer, Integer> exitTransitions = exitTransition();
        if (exitTransitions != null) {
            overridePendingTransition(exitTransitions.first, exitTransitions.second);
        }
    }

    private void assignPresenter(final @Nullable Bundle presenterEnvelope) {
        if (presenter == null) {
            final RequiresPresenter annotation = getClass().getAnnotation(RequiresPresenter.class);
            final Class<Presenter> presenterClass = annotation == null ? null : (Class<Presenter>) annotation.value();
            if (presenterClass != null) {
                presenter = (Presenter) PresenterManager.getInstance().fetch(this,
                        presenterClass,
                        BundleUtils.maybeGetBundle(presenterEnvelope, PRESENTER_KEY));
            }
        }
    }
}
