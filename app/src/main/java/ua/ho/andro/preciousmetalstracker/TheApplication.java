package ua.ho.andro.preciousmetalstracker;

import android.app.Application;

public class TheApplication extends Application {

    private static TheApplication instance;

    public static TheApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }
}