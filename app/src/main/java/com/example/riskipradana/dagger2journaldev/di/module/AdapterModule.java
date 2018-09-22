package com.example.riskipradana.dagger2journaldev.di.module;

import com.example.riskipradana.dagger2journaldev.MainActivity;
import com.example.riskipradana.dagger2journaldev.RecyclerViewAdapter;
import com.example.riskipradana.dagger2journaldev.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getStarWarsPeopleList(RecyclerViewAdapter.ClickListener clickListener){
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(MainActivity mainActivity) {
        return (RecyclerViewAdapter.ClickListener) mainActivity;
    }
}
