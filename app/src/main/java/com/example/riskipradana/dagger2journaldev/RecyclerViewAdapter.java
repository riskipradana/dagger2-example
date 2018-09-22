package com.example.riskipradana.dagger2journaldev;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.riskipradana.dagger2journaldev.pojo.StarWars;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<StarWars.People> data;
    private ClickListener clickListener;

    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        this.data = new ArrayList<>();
    }

    @Inject


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName;
        private TextView txtBirthYear;
        private ConstraintLayout constraintLayoutContainer;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtBirthYear = itemView.findViewById(R.id.txtBirthYear);
            constraintLayoutContainer = itemView.findViewById(R.id.constraintLayout);

            constraintLayoutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.launchIntent(data.get(getAdapterPosition()).films.get(0));
                }
            });
        }
    }

    public interface ClickListener {

        void launchIntent(String filmName);
    }

    public void setData(List<StarWars.People> data){
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
