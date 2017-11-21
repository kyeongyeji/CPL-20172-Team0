package com.example.kimminji.jongpp;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Owner on 2017-11-22.
 */

public class CustomBus extends Bus {
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private static final Bus BUS = new Bus(ThreadEnforcer.MAIN);

    public static Bus getInstance() {
        return BUS;
    }

    private CustomBus() {
        // No instances.
    }

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    CustomBus.super.post(event);
                }
            });
        }
    }

}
