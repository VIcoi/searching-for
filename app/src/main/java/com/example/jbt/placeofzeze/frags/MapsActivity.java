package com.example.jbt.placeofzeze.frags;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jbt.placeofzeze.R;
import com.example.jbt.placeofzeze.model.Place;
import com.example.jbt.placeofzeze.model.PlaceDBHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_maps, container, false);
        // Inflate the layout for this fragment
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        return v;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());

        // Add a marker on userLocation or default in HOLON and move the camera
        LatLng userLocation = new LatLng(sp.getFloat("late",34.7712464f),sp.getFloat("longi",32.0093909f));
        mMap.addMarker(new MarkerOptions().position(userLocation).title("Yata!!!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,13));

    }

    public void placeClickedFromList (Place place){//get place clicked and show on map and user location
        mMap.clear();//clear before display
        LatLng placeLoc = new LatLng(place.getLate(), place.getLongi());
        mMap.addMarker(new MarkerOptions().position(placeLoc).title(place.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(placeLoc,13));

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        LatLng userLocation = new LatLng(sp.getFloat("late",34.7712464f),sp.getFloat("longi",32.0093909f));
        mMap.addMarker(new MarkerOptions().position(userLocation).title("Yata!!!"));

    }

    public void allPlacesFromDB(){//get places from DB and show on map and user location

        mMap.clear();//clear before display

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        LatLng userLocation = new LatLng(sp.getFloat("late",34.7712464f),sp.getFloat("longi",32.0093909f));
        mMap.addMarker(new MarkerOptions().position(userLocation).title("Yata!!!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,13));

        ArrayList<Place> places = new PlaceDBHelper(getContext()).getAllTableForList();

        for (int i = 0; i < places.size() ; i++) {

            LatLng placeLoc = new LatLng(places.get(i).getLate(),places.get(i).getLongi());
            mMap.addMarker(new MarkerOptions().position(placeLoc).title(places.get(i).getName()));

        }

    }



}
