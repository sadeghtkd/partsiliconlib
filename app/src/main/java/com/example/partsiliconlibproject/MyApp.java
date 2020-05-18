package com.example.partsiliconlibproject;

import android.app.Application;

import com.partsilicon.partsiliconlib.classes.Push;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new Push().subscribeToPush(this);
    }
}
