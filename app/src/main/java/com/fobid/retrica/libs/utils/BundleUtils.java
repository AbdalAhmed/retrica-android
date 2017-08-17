package com.fobid.retrica.libs.utils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by android01 on 2017. 8. 17..
 */

public final class BundleUtils {

    private BundleUtils() {
    }

    public static
    @Nullable
    Bundle maybeGetBundle(final @Nullable Bundle state, final @NonNull String key) {
        if (state == null) {
            return null;
        }

        return state.getBundle(key);
    }
}
