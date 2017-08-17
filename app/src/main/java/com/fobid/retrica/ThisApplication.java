package com.fobid.retrica;

import android.app.Application;
import android.support.annotation.NonNull;

import timber.log.Timber;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class ThisApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        Timber.plant(new Timber.DebugTree());
    }

    public
    @NonNull
    ApplicationComponent component() {
        return component;
    }
}
