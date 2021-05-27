package com.example.partsiliconlibproject;

import android.app.Application;

import com.partsilicon.partsiliconlib.classes.Push;

import androidx.multidex.MultiDexApplication;

public class MyApp extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        new Push().subscribeToPush(this);
    }
}
