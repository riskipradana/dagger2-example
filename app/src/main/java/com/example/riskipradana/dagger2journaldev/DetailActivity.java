package com.example.riskipradana.dagger2journaldev;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.riskipradana.dagger2journaldev.di.component.ApplicationComponent;
import com.example.riskipradana.dagger2journaldev.di.component.DaggerDetailActivityComponent;
import com.example.riskipradana.dagger2journaldev.di.component.DetailActivityComponent;
import com.example.riskipradana.dagger2journaldev.di.qualifier.ApplicationContext;
import com.example.riskipradana.dagger2journaldev.pojo.Film;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Again the inject method is used to inject all the dependency fields.
 * The retrofit makes a request to the dynamic URL specified and displays the response in a TextView.
 */

public class DetailActivity extends AppCompatActivity {

    DetailActivityComponent detailActivityComponent;

    TextView textView;

    @Inject
    public ApiInterface apiInterface;

    @Inject
    @ApplicationContext
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView = findViewById(R.id.textView);

        String url = getIntent().getStringExtra("url");

        ApplicationComponent applicationComponent =
                MyApplication.get(this).getApplicationComponent();

        detailActivityComponent =
                DaggerDetailActivityComponent.builder()
                        .applicationComponent(applicationComponent)
                        .build();

        detailActivityComponent.inject(this);

        apiInterface.getFilmData(url, "json").enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                Film film = response.body();
                String text = "Film name:\n" + film.title + "\nDirector:\n" + film.director;
                textView.setText(text);
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {

            }
        });
    }
}
