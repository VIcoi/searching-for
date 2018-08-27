package com.example.jbt.placeofzeze.model;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jbt.placeofzeze.R;
import com.squareup.picasso.Picasso;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.RecyclerHolder> {
    private ArrayList<Place> places = new ArrayList<>();
    private Context context;
    private onClickItemFromList listener;
    private int type ;
    private PlaceDBHelper helper;

        //constractors
    public AdapterRecycler(Context context, int type) {
        this.context = context;
        this.type = type;
        this.listener = (onClickItemFromList) context;
    }

    public AdapterRecycler(ArrayList<Place> places, Context context, int type) {
        this.places = places;
        this.type = type;
        this.context = context;
        this.listener = (onClickItemFromList) context;
    }


    public void setPlaces(ArrayList<Place> places) {// methud for getting the places array
        this.places = places;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.place_item_list,parent,false));//setting the layout as place_item_list
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        holder.bind(places.get(position));//making the bind method on each place to set as an item
    }

    @Override
    public int getItemCount() {//size of arraylist
        if(places == null){
            return 0;
        }
        return places.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView name,address,distance;
        private Place place;
        private ImageView icon;
        public RecyclerHolder(View itemView) {
            super(itemView);

            //finding all views in the item
            name = itemView.findViewById(R.id.textViewName);
            address = itemView.findViewById(R.id.textViewAddress);
            distance = itemView.findViewById(R.id.textViewDistance);
            icon = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        public void bind (Place place){ // binding each place
            this.place = place;
            name.setText(place.getName());
            address.setText(place.getAddress());

            //user latlng
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            float userLat = sp.getFloat("late", (float) 32.0093909);
            float userLng = sp.getFloat("longi", (float) 34.7712464);
            //place latlng
            float placeLat = (float) place.getLate();
            float placeLongi = (float) place.getLongi();


            //distance from user to place calc
            double ERadius = 6371000;
            double dLat = Math.toRadians(placeLat-userLat);
            double dLongi = Math.toRadians(placeLongi-userLng);
            double dd = Math.sin(dLat/2)*Math.sin(dLat/2)+
                    Math.cos(Math.toRadians(userLat))* Math.cos(Math.toRadians(placeLat))*
                            Math.sin(dLongi/2)*Math.sin(dLongi/2);
            double dd1 = 2*Math.atan2(Math.sqrt(dd),Math.sqrt(1-dd));
            float dist = (float) (ERadius*dd1);
            //TODO do the settings and get distance type (km's/mila's) form sp

                boolean dt = sp.getBoolean("Dtype",false);

            DecimalFormat df = new DecimalFormat("0.00");
                //km's or mille's
            //TODO mila's
                if (dt ==false){
                    if (dist>1600){
                        dist = (float) (dist/1609.344);
                        distance.setText(df.format(dist)+" Mille's");
                    }else {
                        dist = (float) ((dist*100)/30.48);
                        distance.setText(df.format(dist)+" yard");
                    }

            }else {

                //TODO km's
                    if (dist>1500){
                        dist = dist/1000;
                        distance.setText(df.format(dist)+"Km's");
                    }else {
                        distance.setText(df.format(dist)+"m");
                    }

            }
            sp.edit().putFloat("dist",dist).apply();

            Picasso.with(context).load(place.getIcon()).error(R.drawable.btnsearch).into(icon);//place icon



        }

        @Override
        public void onClick(View view) {

            //todo make intent with latlng of item going to the map and pointing on the item

            listener.placeClicked(places.get(getAdapterPosition()));//when clicking on item from the list - go to map and show

        }

        @Override
        public boolean onLongClick(final View view) {

            if (type==1){ // search is type 1
                final AlertDialog dialog = new AlertDialog.Builder(context).create();
                dialog.setTitle(R.string.searchDT);
                dialog.setMessage(context.getString(R.string.searchDM));
                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, context.getString(R.string.searchBN), new DialogInterface.OnClickListener() {  // btn on dialog Add To Favorites
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //todo Add To Favorites

                        helper=new PlaceDBHelper(context);
                       helper.insertTableForListFAVO(place);

                    }
                });
                        //Share?
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, context.getString(R.string.searchBP), new DialogInterface.OnClickListener() { // btn on dialog Share
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //todo Share

                        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        String shareBodyText = "Look At This -  "+name.getText()+"\n"+address.getText();
                        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                        context.startActivity(intent);

                    }
                });

                dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel", new DialogInterface.OnClickListener() { // // btn on dialog to exit dialog
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }else { // favorites is type 2

                final AlertDialog dialog = new AlertDialog.Builder(context).create();
                dialog.setTitle(R.string.favoritesDT);
                dialog.setMessage(context.getString(R.string.favoritesDM));
                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, context.getString(R.string.favoritesDBN), new DialogInterface.OnClickListener() {  // btn on dialog Add To Favorites
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //todo Delete From Favorites
                        helper=new PlaceDBHelper(context);
                        helper.deleteDBItemFAVO((place.getId())+"");
                        places.remove(place);
                        notifyItemRemoved(getAdapterPosition());

                    }
                });

                dialog.setButton(AlertDialog.BUTTON_POSITIVE,context.getString(R.string.searchBP), new DialogInterface.OnClickListener() { // btn on dialog Share
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //todo Share
                        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        String shareBodyText = "Look At This "+name+"\n"+address;
                        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                        context.startActivity(intent);

                    }
                });

                dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel", new DialogInterface.OnClickListener() { // // btn on dialog to exit dialog
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }

            return true;
        }
    }

    public interface onClickItemFromList {//interface - a listener used on main to send place to mapfrag
         void placeClicked(Place place);

    }

}
