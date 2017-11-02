package com.dev.weathertest.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dev.weathertest.R;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements MapFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            MapFragment mapFragment = new MapFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, mapFragment, MapFragment.TAG)
                    .commitNow();
        }
    }

    @Override
    public void openDetailScreen(LatLng latLng) {
        DetailFragment detailFragment = DetailFragment.newInstance(latLng);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("stack")
                .add(R.id.fragment_container, detailFragment, DetailFragment.TAG)
                .commit();
    }
}
