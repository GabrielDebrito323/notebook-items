package com.ed.globallogik;

import android.app.Application;


public class App extends Application  {

    static App instance;
    AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .networkModule(new NetworkModule()
                ).appModule(new AppModule(this))
                .build();
        instance = this;

    }


    public static App getInstance() {
        return instance;
    }

    public AppComponent getNetComponent() {
        return appComponent;
    }
}
