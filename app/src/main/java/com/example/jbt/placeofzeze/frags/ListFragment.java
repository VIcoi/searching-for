package com.example.jbt.placeofzeze.frags;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jbt.placeofzeze.model.AdapterRecycler;
import com.example.jbt.placeofzeze.model.PlaceDBHelper;
import com.example.jbt.placeofzeze.R;
import com.example.jbt.placeofzeze.model.SearchService;

import static com.example.jbt.placeofzeze.model.SearchService.ACTUION_BROD;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements View.OnClickListener, LocationListener {
    //creating vars
    private static final int LOCATION_REQUEST_CODE = 45;
    private EditText editTextSearch;
    private ImageView imageBtnSearch;
    private RecyclerView recyclerViewSearch;
    private LocationManager locationManager;
    double userLate,userLongi;
    private PlaceDBHelper placeDBHelper;
    private AdapterRecycler adapterRecycler;



    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);// declering View
        editTextSearch = view.findViewById(R.id.editTextSearch);  //using View to find views inside View
        recyclerViewSearch = view.findViewById(R.id.recyclerViewFavorites);//using View to find views inside View
        adapterRecycler = new AdapterRecycler(getContext(), 1);
        placeDBHelper = new PlaceDBHelper(getContext());
        recyclerViewSearch.setAdapter(adapterRecycler);

        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        imageBtnSearch = view.findViewById(R.id.imageBtnSearch);//using View to find views inside View
        imageBtnSearch.setOnClickListener(this);

        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        final PlacesReceiver receiver = new PlacesReceiver();

        LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver,new IntentFilter(ACTUION_BROD));

        userLoction();


        return view;
    }



    private void userLoction() { //checking and asking promossion form user to his location using GPS
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(getActivity() , new String[] {Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_REQUEST_CODE);
        }else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1500,100,this); // loction will be find with GPS every 1500ms's or 100 meters
        }
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getContext(), SearchService.class);//intent to search service
        intent.putExtra("keywords", editTextSearch.getText().toString());//user keyword
        //user location
        intent.putExtra("late", userLate);
        intent.putExtra("longi", userLongi);

        getContext().startService(intent);//getContext() because its un fragment

    }

    @Override  //getting result from user for asking promossion
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == LOCATION_REQUEST_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                userLoction();//if PERMISSION_GRANTED do this methud to get location

            } else {
                //if promossion is not granted do a toast
                Toast.makeText(getContext(), R.string.noPrem, Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onLocationChanged(Location location) {//get user location and put in SP
        userLongi = location.getLongitude();
        userLate = location.getLatitude();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        sp.edit().putFloat("late", (float) userLate)
                .putFloat("longi", (float) userLongi)
                .apply();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public class PlacesReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {//when receive broadcast
           //TODO create ADPTER and create method for geting table form DB and connect in to the PlaceDBHelper to the ADAPTER



            adapterRecycler.setPlaces(placeDBHelper.getAllTableForList());


//            placeDBHelper.getAllTableForList(); TODO - this mathod to get arrayPalces from DB USE IT ONLY AFTER CRATING AN ADAPTER...
        }
    }

    @Override
    public void onStart() {//get list on start not on create - if already created and user comes beck from another act
        super.onStart();

        adapterRecycler.setPlaces(placeDBHelper.getAllTableForList());

    }
}
