package com.example.riskipradana.dagger2journaldev.di.component;

import android.content.Context;

import com.example.riskipradana.dagger2journaldev.MainActivity;
import com.example.riskipradana.dagger2journaldev.di.module.AdapterModule;
import com.example.riskipradana.dagger2journaldev.di.qualifier.ActivityContext;
import com.example.riskipradana.dagger2journaldev.di.scopes.ActivityScope;

import dagger.Component;

/**
 * This component would have access to the ApplicationComponent dependencies too.
 */

@ActivityScope
@Component(modules = AdapterModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();

    void injectMainActivity(MainActivity mainActivity);
}
