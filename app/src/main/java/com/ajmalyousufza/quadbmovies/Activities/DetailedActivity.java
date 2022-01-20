package com.ajmalyousufza.quadbmovies.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ajmalyousufza.quadbmovies.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {

    TextView movie_name,rating,language,sched_day,sched_time,summary;
    ImageView movie_image;
    ProgressBar progressBar;

    String m_name,m_image,m_rating,m_language,m_sched_day,m_sched_time,m_summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        movie_name = findViewById(R.id.movie_name);
        rating = findViewById(R.id.rating);
        language = findViewById(R.id.language);
        sched_day = findViewById(R.id.day_schduled);
        sched_time = findViewById(R.id.time_schduled);
        summary = findViewById(R.id.summary);
        movie_image = findViewById(R.id.imageview);
        progressBar = findViewById(R.id.progressbar);

        m_name = getIntent().getStringExtra("m_name");
        m_image = getIntent().getStringExtra("m_image");
        m_rating = getIntent().getStringExtra("m_rating");
        m_language = getIntent().getStringExtra("m_language");
        m_sched_day = getIntent().getStringExtra("m_sche_day");
        m_sched_time = getIntent().getStringExtra("m_sche_time");
        m_summary = getIntent().getStringExtra("m_summary");

        movie_name.setText(m_name);
        rating.setText("Rating : "+m_rating);
        language.setText("Language : "+m_language);
        sched_day.setText("Scheduled day : "+m_sched_day);
        sched_time.setText("Scheduled time : "+m_sched_time);
        summary.setText("Summary : \n"+m_summary);
        Picasso.get().load(m_image).into(movie_image, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }
}