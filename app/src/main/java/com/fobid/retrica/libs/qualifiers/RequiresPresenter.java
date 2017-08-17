package com.fobid.retrica.libs.qualifiers;

import com.fobid.retrica.ui.presenters.BasePresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by android01 on 2017. 8. 17..
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPresenter {

    Class<? extends BasePresenter> value();
}
