package com.one.netease.event_sample;

import android.app.Application;

/**
 * @author diaokaibin@gmail.com on 2020-02-03.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


//        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();

    }
}
