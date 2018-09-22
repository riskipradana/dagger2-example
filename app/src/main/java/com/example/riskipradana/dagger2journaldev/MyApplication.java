package com.example.riskipradana.dagger2journaldev;

import android.app.Activity;
import android.app.Application;

import com.example.riskipradana.dagger2journaldev.di.component.ApplicationComponent;
import com.example.riskipradana.dagger2journaldev.di.component.DaggerApplicationComponent;
import com.example.riskipradana.dagger2journaldev.di.module.ContextModule;

import dagger.android.DaggerApplication;

public class MyApplication extends Application{

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // used to build the modules present in the component
        applicationComponent =
                DaggerApplicationComponent
                        .builder()
                        .contextModule(new ContextModule(this))
                        .build();

    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    // used to return the ApplicationComponent in our Activities.
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
