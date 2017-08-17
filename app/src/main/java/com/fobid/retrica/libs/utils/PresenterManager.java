package com.fobid.retrica.libs.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fobid.retrica.ThisApplication;
import com.fobid.retrica.libs.Environment;
import com.fobid.retrica.ui.presenters.BasePresenter;
import com.fobid.retrica.ui.views.BaseView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class PresenterManager<View extends BaseView> {

    private static final String PRESENTER_ID_KEY = "presenter_id";
    private static final String PRESENTER_STATE_KEY = "presenter_state";

    private static final PresenterManager instance = new PresenterManager();
    private Map<String, BasePresenter> presenters = new HashMap<>();

    public static
    @NonNull
    PresenterManager getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T extends BasePresenter> T fetch(final @NonNull Context context,
                                             final @NonNull Class<T> presenterClass,
                                             final @Nullable Bundle savedInstanceState) {
        final String id = fetchId(savedInstanceState);
        BasePresenter BasePresenter = presenters.get(id);

        if (BasePresenter == null) {
            BasePresenter = create(context, presenterClass, savedInstanceState, id);
        }

        return (T) BasePresenter;
    }

    public void destroy(final @NonNull BasePresenter activityBasePresenter) {
        activityBasePresenter.onDestroy();

        final Iterator<Map.Entry<String, BasePresenter>> iterator = presenters.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, BasePresenter> entry = iterator.next();
            if (activityBasePresenter.equals(entry.getValue())) {
                iterator.remove();
            }
        }
    }

    public void save(final @NonNull BasePresenter BasePresenter,
                     final @NonNull Bundle envelope) {
        envelope.putString(PRESENTER_ID_KEY, findIdForPresenter(BasePresenter));

        final Bundle state = new Bundle();
        envelope.putBundle(PRESENTER_STATE_KEY, state);
    }

    private <T extends BasePresenter> BasePresenter create(final @NonNull Context context,
                                                           final @NonNull Class<T> presenterClass,
                                                           final @Nullable Bundle savedInstanceState,
                                                           final @NonNull String id) {

        final ThisApplication application = (ThisApplication) context.getApplicationContext();
        final Environment environment = application.component().environment();
        final BasePresenter BasePresenter;

        try {
            final Constructor constructor = presenterClass.getConstructor(Environment.class);
            BasePresenter = (BasePresenter) constructor.newInstance(environment);

            // Need to catch these exceptions separately, otherwise the compiler turns them into `ReflectiveOperationException`.
            // That exception is only available in API19+
        } catch (IllegalAccessException exception) {
            throw new RuntimeException(exception);
        } catch (InvocationTargetException exception) {
            throw new RuntimeException(exception);
        } catch (InstantiationException exception) {
            throw new RuntimeException(exception);
        } catch (NoSuchMethodException exception) {
            throw new RuntimeException(exception);
        }

        presenters.put(id, BasePresenter);
        BasePresenter.onCreate(context, BundleUtils.maybeGetBundle(savedInstanceState, PRESENTER_STATE_KEY));

        return BasePresenter;
    }

    private String fetchId(final @Nullable Bundle savedInstanceState) {
        return savedInstanceState != null ?
                savedInstanceState.getString(PRESENTER_ID_KEY) :
                UUID.randomUUID().toString();
    }

    private String findIdForPresenter(final @NonNull BasePresenter BasePresenter) {
        for (final Map.Entry<String, BasePresenter> entry : presenters.entrySet()) {
            if (BasePresenter.equals(entry.getValue())) {
                return entry.getKey();
            }
        }

        throw new RuntimeException("Cannot find presenter in map!");
    }
}
