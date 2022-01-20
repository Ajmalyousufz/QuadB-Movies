package com.ajmalyousufza.quadbmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.ViewHolder> {

    ArrayList<HomeRVModel> homeRVModelArrayList;
    Context context;
    RecyclerViewClickListener listener;

    public SearchMovieAdapter(ArrayList<HomeRVModel> homeRVModelArrayList, Context context,RecyclerViewClickListener listener) {
        this.homeRVModelArrayList = homeRVModelArrayList;
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_movie_item,parent,false));
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

    public interface RecyclerViewClickListener{
        void onClick(View v,int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView movie_image;
        TextView movie_name;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movie_image =  itemView.findViewById(R.id.movie_image_search);
            movie_name= itemView.findViewById(R.id.movie_name_search);
            linearLayout = itemView.findViewById(R.id.linlayitemsearch);

            linearLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            listener.onClick(view,getAdapterPosition());

        }
    }
}
