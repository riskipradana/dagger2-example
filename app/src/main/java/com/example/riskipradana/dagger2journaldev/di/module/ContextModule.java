package com.example.riskipradana.dagger2journaldev.di.module;

import android.content.Context;

import com.example.riskipradana.dagger2journaldev.di.qualifier.ApplicationContext;
import com.example.riskipradana.dagger2journaldev.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext(){
        return context;
    }

}
