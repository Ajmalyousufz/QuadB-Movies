package com.ajmalyousufza.quadbmovies.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajmalyousufza.quadbmovies.Models.HomeRVModel;
import com.ajmalyousufza.quadbmovies.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.ViewHolder> {

    ArrayList<HomeRVModel> homeRVModelArrayList;
    Context context;
    RecyclerViewClickListener listener1;
    int pos;

    public SearchMovieAdapter(ArrayList<HomeRVModel> homeRVModelArrayList, Context context,RecyclerViewClickListener listener1,int pos) {
        this.homeRVModelArrayList = homeRVModelArrayList;
        this.context = context;
        this.listener1=listener1;
        this.pos=pos;
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
        void onClick1(View v,int position);
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

            listener1.onClick1(view,getAdapterPosition());

        }
    }
}
