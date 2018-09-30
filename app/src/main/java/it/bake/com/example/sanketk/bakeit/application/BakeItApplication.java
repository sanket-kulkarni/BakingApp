package it.bake.com.example.sanketk.bakeit.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by sanketk on 15-Sep-18.
 */

public class BakeItApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
