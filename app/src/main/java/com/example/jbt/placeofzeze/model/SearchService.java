package com.example.jbt.placeofzeze.model;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;

import com.example.jbt.placeofzeze.model.Place;
import com.example.jbt.placeofzeze.model.PlaceDBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// a new theard (intent service_ to get info from json using okhttp
public class SearchService extends IntentService {

    public static final String ACTUION_BROD = "action_brod";


    public SearchService() {
        super("SearchService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) { //intent from listfrag with the user key word and position
            String usertext = intent.getStringExtra("keywords");
            double late , longi;
            late = intent.getDoubleExtra("late",32.0093909);
            longi = intent.getDoubleExtra("longi",34.7712464);

            if (usertext==null){ //if user has no key word
                usertext="";
            }

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

            boolean settingsRadius = sp.getBoolean("Radius",false);//Radius from setting act
            int a = 2000;
            if (!settingsRadius){
                 a = 4000;
            }


            OkHttpClient client = new OkHttpClient();//json - search to google getting the info on places
            Request request = new Request.Builder().url("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location" +
                    "=" + late + "," + longi + "&radius=" + a + "&keyword="+usertext+"&key=AIzaSyB83O48PNG_E19bSiPS4c3P2GZZf-1-m7c").build();
                    //user loction                   // radius  //            editext//


            try {

                Response response = client.newCall(request).execute();//getting the json response

                JSONObject object = new JSONObject(response.body().string());//json object

                JSONArray jsonArray = object.getJSONArray("results");//getting json array from object

                ArrayList<Place> PlacesArray = new ArrayList<>();//new Places ArrayList<>

                for (int i = 0 ; i < jsonArray.length() ; i++) {

                    JSONObject tempobject =  jsonArray.getJSONObject(i);//object from "results" jsonArray

                    //getting values from tempobject to my new place
                    String name = tempobject.getString("name");
                    String address = tempobject.getString("vicinity");
                    String icon = tempobject.getString("icon");
                    double late1 = tempobject.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                    double longi1 = tempobject.getJSONObject("geometry").getJSONObject("location").getDouble("lng");

                    PlacesArray.add(new Place(name,address,icon,late1,longi1));//adding new Place to new ArrayList<>

                    //TODO make dbhelper - the dbhelper will apdate the table on frag1 - make insert mathod 1 for array 1 for singel object

                }

                PlaceDBHelper helper = new PlaceDBHelper(this);
                helper.deleteDBList();//cleaning the db list
                helper.insertTableForList(PlacesArray);//inserting the new places array

                Intent broadcastIntent = new Intent(ACTUION_BROD);
                LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);//broadcastIntent to notify the its finished


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }


}
