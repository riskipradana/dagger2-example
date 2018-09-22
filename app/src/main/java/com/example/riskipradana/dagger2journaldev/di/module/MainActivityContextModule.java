package com.example.riskipradana.dagger2journaldev.di.module;

import android.content.Context;

import com.example.riskipradana.dagger2journaldev.MainActivity;
import com.example.riskipradana.dagger2journaldev.di.qualifier.ActivityContext;
import com.example.riskipradana.dagger2journaldev.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule {

    private MainActivity mainActivity;

    public Context context;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainActivity providesMainActivity(){
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context providesContext(){
        return context;
    }
}
