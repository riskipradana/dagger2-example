package com.example.riskipradana.dagger2journaldev.di.component;

import com.example.riskipradana.dagger2journaldev.DetailActivity;
import com.example.riskipradana.dagger2journaldev.di.scopes.ActivityScope;

import dagger.Component;

@Component(dependencies = ApplicationComponent.class)
@ActivityScope
public interface DetailActivityComponent {

    void inject(DetailActivity detailActivity);
}
