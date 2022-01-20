package com.ajmalyousufza.quadbmovies;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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


public class SearchFragment extends Fragment {


    ArrayList<HomeRVModel> homeRVModelArrayListSearch;
    SearchMovieAdapter.RecyclerViewClickListener recyclerViewClickListener;
    RecyclerView recyclerView;
    SearchMovieAdapter adapter;
    EditText editText;
    ImageView imageView;
    String url = "https://api.tvmaze.com/search/shows?q=all";
    int searchIndex = -1;
    String searchkey;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        homeRVModelArrayListSearch = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview1);
        editText = view.findViewById(R.id.searchbar1);
        imageView = view.findViewById(R.id.searchicon1);

//        assert getArguments() != null;
             searchkey = getArguments().getString("searchkey");


        editText.setText(searchkey);
        imageView.setOnClickListener(view1 -> {

            String searchk = editText.getText().toString();
            if(!searchkey.equals(editText.getText().toString())){
                getData(searchk);
            }
        });

        Toast.makeText(getContext(), searchkey , Toast.LENGTH_SHORT).show();
        getRecler();
        getData(searchkey);
        //getSearchData("https://api.tvmaze.com/search/shows?q=${"+searchkey+"}");
        return  view;
    }


    private void getData(String position) {

        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        recyclerView.setVisibility(View.VISIBLE);
                        //progressBar.setVisibility(View.GONE);

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

                                if(jsonObject.getJSONObject("show").getString("name").equals(position)){
                                    searchIndex = i;
                                    homeRVModelArrayListSearch.add(new HomeRVModel(
                                            new Show(name, summary, new image(image_url_medium, image_url_original),
                                                    new schedule(schedule_time, schedule_days), new rating(rating), language)
                                    ));
                                }

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


    private void getSearchData(String searchmovieurl) {

        RequestQueue queue1 = Volley.newRequestQueue(getContext());

        JsonArrayRequest jsonArrayRequest1 = new JsonArrayRequest(
                Request.Method.GET,
                searchmovieurl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {



                        try {
                            JSONObject jsonObject = response.getJSONObject(1);

                            String name = jsonObject.getJSONObject("show").getString("name");
                            String language = jsonObject.getJSONObject("show").getString("language");
                            String image_url_original = jsonObject.getJSONObject("show").getJSONObject("image").getString("original");
                            String image_url_medium = jsonObject.getJSONObject("show").getJSONObject("image").getString("medium");
                            String summary = jsonObject.getJSONObject("show").getString("summary");
                            String schedule_time = jsonObject.getJSONObject("show").getJSONObject("schedule").getString("time");
                            String schedule_days = jsonObject.getJSONObject("show").getJSONObject("schedule").getString("days");
                            String rating = jsonObject.getJSONObject("show").getJSONObject("rating").getString("average");
                            HomeRVModel homeRVModel;

                            homeRVModelArrayListSearch.add(homeRVModel = new HomeRVModel(
                                    new Show(name, summary, new image(image_url_medium, image_url_original),
                                            new schedule(schedule_time, schedule_days), new rating(rating), language)
                            ));

                            getRecler();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );

        queue1.add(jsonArrayRequest1);

    }

    public void setOnClickListener(){
        recyclerViewClickListener = new SearchMovieAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

                Intent intent = new Intent(getContext(),DetailedActivity.class);
                intent.putExtra("m_name",homeRVModelArrayListSearch.get(position).getShow().getName());
                intent.putExtra("m_image",homeRVModelArrayListSearch.get(position).getShow().getImage().getOriginal());
                intent.putExtra("m_rating",homeRVModelArrayListSearch.get(position).getShow().getRating().getAverage());
                intent.putExtra("m_sche_day",homeRVModelArrayListSearch.get(position).getShow().getSchedule().getDays());
                intent.putExtra("m_sche_time",homeRVModelArrayListSearch.get(position).getShow().getSchedule().getTime());
                intent.putExtra("m_summary",homeRVModelArrayListSearch.get(position).getShow().getSummary());
                intent.putExtra("m_language",homeRVModelArrayListSearch.get(position).getShow().getLanguage());

                startActivity(intent);
            }
        };
    }

    private void getRecler() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        adapter = new SearchMovieAdapter(homeRVModelArrayListSearch,getContext(),recyclerViewClickListener);
        recyclerView.setAdapter(adapter);
    }
}