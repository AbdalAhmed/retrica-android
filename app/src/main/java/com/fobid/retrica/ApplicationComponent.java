package com.fobid.retrica;

import com.fobid.retrica.libs.Environment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by android01 on 2017. 8. 17..
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Environment environment();

    void inject(ThisApplication application);
}
