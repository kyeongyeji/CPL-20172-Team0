package com.example.kimminji.jongpp;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * Created by Owner on 2017-11-22.
 */

public class BusUtil {
    private static final Handler mainThread = new Handler(Looper.getMainLooper());
    public static void postOnMain(final Bus bus, final Object event) {

        mainThread.post(new Runnable() {
            @Override
            public void run() {
                bus.post(event);
            }
        });
    }
}
