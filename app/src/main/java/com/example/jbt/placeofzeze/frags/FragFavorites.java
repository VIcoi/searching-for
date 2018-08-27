package com.example.jbt.placeofzeze.frags;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jbt.placeofzeze.model.AdapterRecycler;
import com.example.jbt.placeofzeze.model.PlaceDBHelper;
import com.example.jbt.placeofzeze.R;


public class FragFavorites extends Fragment {

    private RecyclerView recyclerViewFavorites;
    private PlaceDBHelper placeDBHelper;
    private AdapterRecycler adapterRecycler;
    //private ArrayList<Place> places;


    public FragFavorites() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // taking Inflate the layout for this fragment as a view
        View v = inflater.inflate(R.layout.fragment_frag_favorites, container, false);
        //finding recyclerViewFavorites with v and setting adapter
        recyclerViewFavorites = v.findViewById(R.id.recyclerViewFavorites);
        adapterRecycler = new AdapterRecycler(getContext(), 2);//the int 2 is var for choose the constractor
        placeDBHelper = new PlaceDBHelper(getContext());

        recyclerViewFavorites.setAdapter(adapterRecycler);
        recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(getContext()));

        adapterRecycler.setPlaces(placeDBHelper.getAllTableForListFAVO ());// getting all places from favorites db

        return v;
    }






}
