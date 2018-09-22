package com.example.riskipradana.dagger2journaldev;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.riskipradana.dagger2journaldev.di.component.ApplicationComponent;
import com.example.riskipradana.dagger2journaldev.di.component.DaggerMainActivityComponent;
import com.example.riskipradana.dagger2journaldev.di.component.MainActivityComponent;
import com.example.riskipradana.dagger2journaldev.di.module.MainActivityContextModule;
import com.example.riskipradana.dagger2journaldev.di.qualifier.ActivityContext;
import com.example.riskipradana.dagger2journaldev.di.qualifier.ApplicationContext;
import com.example.riskipradana.dagger2journaldev.pojo.StarWars;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The class implements the RecyclerViewAdapter.ClickListener interface callback
 * which triggers a launchIntent method whenever the RecyclerView row gets clicked.
 *
 * Note that the Context injected needs to be specified with the relevant qualifier we had earlier defined.
 */

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener{

    MainActivityComponent mainActivityComponent;

    RecyclerView recyclerView;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject ApiInterface apiInterface;

    @Inject
    @ApplicationContext
    private Context context;

    @Inject
    @ActivityContext
    private Context activityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        ApplicationComponent applicationComponent =
                MyApplication.get(this).getApplicationComponent();

        mainActivityComponent =
                DaggerMainActivityComponent.builder()
                        .mainActivityContextModule(new MainActivityContextModule(this))
                        .applicationComponent(applicationComponent)
                        .build();

        /**
         * Once this happens: mainActivityComponent.injectMainActivity(this);,
         * The fields present with the @Inject would be automatically injected.
         *
         * The rest is doing a Retrofit call and setting the data in the RecyclerViewAdapter.
         */
        mainActivityComponent.injectMainActivity(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        apiInterface.getPeople("json").enqueue(new Callback<StarWars>() {
            @Override
            public void onResponse(Call<StarWars> call, Response<StarWars> response) {
                populateRecyclerView(response.body().results);
            }

            @Override
            public void onFailure(Call<StarWars> call, Throwable t) {

            }
        });
    }

    private void populateRecyclerView(List<StarWars.People> response){
        recyclerViewAdapter.setData(response);
    }

    @Override
    public void launchIntent(String url) {
        Toast.makeText(context, "RecyclerView Row selected", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(activityContext, DetailActivity.class).putExtra("url", url));
    }
}
