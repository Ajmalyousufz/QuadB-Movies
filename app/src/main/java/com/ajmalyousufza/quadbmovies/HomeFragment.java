package com.ajmalyousufza.quadbmovies;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HomeFragment extends Fragment {


    RecyclerView recyclerView;
    HomeRVAdapter adapter;
    ArrayList<HomeRVModel> homeRVModelArrayList;
    ProgressBar progressBar;
    HomeRVAdapter.RecyclerViewClickListener recyclerViewClickListener;

    String url = "https://api.tvmaze.com/search/shows?q=all";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setVisibility(View.GONE);
        progressBar = view.findViewById(R.id.progressbar);
        setOnClickListener();
        homeRVModelArrayList = new ArrayList<>();

        getRecler();
        getData();

        return view;
    }



    private void getData() {

        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        recyclerView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);

                                String name = jsonObject.getJSONObject("show").getString("name");
                                String language = jsonObject.getJSONObject("show").getString("language");
                                String image_url_original = jsonObject.getJSONObject("show").getJSONObject("image").getString("original");
                                String image_url_medium = jsonObject.getJSONObject("show").getJSONObject("image").getString("medium");
                                String summary = jsonObject.getJSONObject("show").getString("summary");
                                String schedule_time = jsonObject.getJSONObject("show").getJSONObject("schedule").getString("time");
                                String schedule_days = jsonObject.getJSONObject("show").getJSONObject("schedule").getString("days");
                                String rating = jsonObject.getJSONObject("show").getJSONObject("rating").getString("average");

                                homeRVModelArrayList.add(new HomeRVModel(
                                        new Show(name, summary, new image(image_url_medium, image_url_original),
                                                new schedule(schedule_time, schedule_days), new rating(rating), language)
                                ));

                                getRecler();

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getContext(), "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );

        queue.add(jsonArrayRequest);

    }

    public void setOnClickListener(){
        recyclerViewClickListener = new HomeRVAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

                Intent intent = new Intent(getContext(),DetailedActivity.class);
                intent.putExtra("m_name",homeRVModelArrayList.get(position).getShow().getName());
                intent.putExtra("m_image",homeRVModelArrayList.get(position).getShow().getImage().getOriginal());
                intent.putExtra("m_rating",homeRVModelArrayList.get(position).getShow().getRating().getAverage());
                intent.putExtra("m_sche_day",homeRVModelArrayList.get(position).getShow().getSchedule().getDays());
                intent.putExtra("m_sche_time",homeRVModelArrayList.get(position).getShow().getSchedule().getTime());
                intent.putExtra("m_summary",homeRVModelArrayList.get(position).getShow().getSummary());
                intent.putExtra("m_language",homeRVModelArrayList.get(position).getShow().getLanguage());

                startActivity(intent);
            }
        };
    }

    private void getRecler() {
        adapter = new HomeRVAdapter(homeRVModelArrayList,getContext(),recyclerViewClickListener);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(adapter);
    }
}