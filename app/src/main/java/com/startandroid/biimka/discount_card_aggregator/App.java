package com.startandroid.biimka.discount_card_aggregator;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this.getApplicationContext();
    }
}