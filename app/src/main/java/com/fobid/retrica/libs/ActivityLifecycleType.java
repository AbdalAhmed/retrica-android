package com.fobid.retrica.libs;

import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.Observable;

/**
 * Created by android01 on 2017. 8. 17..
 */

public interface ActivityLifecycleType {

    Observable<ActivityEvent> lifecycle();
}
