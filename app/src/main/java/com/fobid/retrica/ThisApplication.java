package com.fobid.retrica;

import android.app.Application;
import android.support.annotation.NonNull;

import com.akaita.java.rxjava2debug.RxJava2Debug;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class ThisApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);

        Timber.plant(new Timber.DebugTree());
        RxJava2Debug.enableRxJava2AssemblyTracking();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }

    public
    @NonNull
    ApplicationComponent component() {
        return component;
    }
}
