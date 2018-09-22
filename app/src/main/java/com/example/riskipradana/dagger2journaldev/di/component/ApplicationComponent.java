package com.example.riskipradana.dagger2journaldev.di.component;


import android.content.Context;

import com.example.riskipradana.dagger2journaldev.MyApplication;
import com.example.riskipradana.dagger2journaldev.ApiInterface;
import com.example.riskipradana.dagger2journaldev.di.module.ContextModule;
import com.example.riskipradana.dagger2journaldev.di.module.RetrofitModule;
import com.example.riskipradana.dagger2journaldev.di.qualifier.ApplicationContext;
import com.example.riskipradana.dagger2journaldev.di.scopes.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    public ApiInterface getAPiInterface();

    @ApplicationContext
    public Context getContext();

    public void injectApplication(MyApplication application);
}
