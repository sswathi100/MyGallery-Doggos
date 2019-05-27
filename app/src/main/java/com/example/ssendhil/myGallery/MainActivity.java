package com.example.ssendhil.myGallery;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.Observable;
import java.util.Observer;
import android.support.v7.widget.Toolbar;
import android.widget.RatingBar;
public class MainActivity extends AppCompatActivity implements Observer {
    Model mModel;
    private RecyclerView rView;
    private MyRecyclerAdapter rVAdapter;
    private RatingBar ratingBar;
    // could have a layoutmanager for the recyclerview declared here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("CAME Here", "Created");
        mModel = Model.getInstance();
        mModel.addObserver(this);
        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        rView = findViewById(R.id.recycler_view);
        rView.setLayoutManager(new GridLayoutManager(this, 1));
        //mModel.load();
        rVAdapter = new MyRecyclerAdapter(mModel.getMyIcons(), getApplicationContext(), mModel);
        rView.setAdapter(rVAdapter);
        mModel.setAdapter(rVAdapter);
        findViewById(R.id.refreshButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CAME HERE", "gonna call load method from model");
                mModel.load();
            }
        });
        findViewById(R.id.clearButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModel.clearAll();
            }
        });
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mModel.setFilter((int) rating);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove observer when activity is destroyed.
        mModel.deleteObserver(this);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("CAME Here", "Cofig changed");
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rView.setLayoutManager(new GridLayoutManager(this, 2));
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rView.setLayoutManager(new GridLayoutManager(this, 1));
        }
    }
    @Override
    public void update(Observable o, Object arg) {
        ratingBar.setRating(mModel.getFilter());
        rVAdapter.updateList(mModel.getMyIcons());
        Log.d("CAME HERE", "SIZE: " + rVAdapter.getItemCount());
    }
}