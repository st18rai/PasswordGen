package com.st18apps.passwordgen;

import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.orm.SugarApp;
import com.squareup.leakcanary.LeakCanary;

import io.fabric.sdk.android.Fabric;

/**
 * Created by st18rai on 10.06.17.
 */

public class App extends SugarApp {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        Fabric.with(this, new Crashlytics());
        Log.i("crashlytics", "it created");

    }

    public static App getApp() {
        return instance;
    }

}
