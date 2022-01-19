package com.ajmalyousufza.quadbmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeRVAdapter extends RecyclerView.Adapter<HomeRVAdapter.ViewHolder> {

    ArrayList<HomeRVModel> homeRVModelArrayList;
    Context context;

    public HomeRVAdapter(ArrayList<HomeRVModel> homeRVModelArrayList, Context context) {
        this.homeRVModelArrayList = homeRVModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_movie_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.movie_name.setText(homeRVModelArrayList.get(position).getShow().getName().toString());
        Picasso.get().load(homeRVModelArrayList.get(position).getShow().getImage().getMedium()).into(holder.movie_image, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return homeRVModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView movie_image;
        TextView movie_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

          movie_image =  itemView.findViewById(R.id.movie_image_home);
           movie_name= itemView.findViewById(R.id.movie_name_home);

        }
    }
}
