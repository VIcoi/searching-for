package com.example.jbt.placeofzeze.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jbt.placeofzeze.model.Place;

import java.util.ArrayList;

public class PlaceDBHelper extends SQLiteOpenHelper {

    //making Columns on DB one for places table one for favplaces table
    public static final String TABLE_NAME_LIST = "places";
    public static final String TABLE_NAME_FAVO = "favplaces";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_ADDRESS = "address";
    public static final String COL_ICON = "icon";
    public static final String COL_LATE = "late";
    public static final String COL_LONGI = "longi";

    public PlaceDBHelper(Context context) {
        super(context, "dbPlaces", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = String.format("create table %s ( %s integer primary key autoincrement, %s text , %s text , %s text , %s real , %s real )",
                TABLE_NAME_LIST, COL_ID , COL_NAME , COL_ADDRESS , COL_ICON , COL_LATE , COL_LONGI );//places table

        sqLiteDatabase.execSQL(sql);

        String sql1 = String.format("create table %s ( %s integer primary key autoincrement, %s text , %s text , %s text , %s real , %s real )",
                TABLE_NAME_FAVO, COL_ID , COL_NAME , COL_ADDRESS , COL_ICON , COL_LATE , COL_LONGI );//favplaces table

        sqLiteDatabase.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertTableForList (ArrayList<Place> PlacesArray){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        for (int i = 0; i <PlacesArray.size() ; i++) {//loop for inserting place by place to DB using insert mathod
            values.put(COL_NAME,PlacesArray.get(i).getName());
            values.put(COL_ADDRESS,PlacesArray.get(i).getAddress());
            values.put(COL_ICON,PlacesArray.get(i).getIcon());
            values.put(COL_LATE,PlacesArray.get(i).getLate());
            values.put(COL_LONGI,PlacesArray.get(i).getLongi());

            database.insert(TABLE_NAME_LIST,null,values);

        }

        database.close();

    }

    public void deleteDBList (){//delete all List from DB
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_NAME_LIST,null,null);

        database.close();

    }


    public ArrayList<Place> getAllTableForList (){//sending all places as a list(places) taken from DB

        ArrayList<Place> places = new ArrayList<>();


        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME_LIST,null,null,null,null,null,null);

        while (cursor.moveToNext()){

            long id = cursor.getLong(cursor.getColumnIndex(COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
            String address = cursor.getString(cursor.getColumnIndex(COL_ADDRESS));
            String icon = cursor.getString(cursor.getColumnIndex(COL_ICON));
            double late = cursor.getDouble(cursor.getColumnIndex(COL_LATE));
            double longi = cursor.getDouble(cursor.getColumnIndex(COL_LONGI));

            places.add(new Place(id,name,address,icon,late,longi));

        }
        database.close();
        return places;
    }

    //****************************************************************************************************************
    //****************************************************************************************************************
    //****************************************************************************************************************
    //****************************************************************************************************************

        //TABLET Methods


    public void insertTableForListFAVO (Place place){//insert place to favoritesDB
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();


            values.put(COL_NAME,place.getName());
            values.put(COL_ADDRESS,place.getAddress());
            values.put(COL_ICON,place.getIcon());
            values.put(COL_LATE,place.getLate());
            values.put(COL_LONGI,place.getLongi());

            database.insert(TABLE_NAME_FAVO,null,values);


        database.close();

    }


    public void deleteDBItemFAVO (String id){//delete place from favoritesDB
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_NAME_FAVO,COL_ID + " = ?" ,new String[] {id});


        database.close();

    }



    public ArrayList<Place> getAllTableForListFAVO (){//sending all places as a list(placesFavo) taken from DB

        ArrayList<Place> placesFavo = new ArrayList<>();


        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME_FAVO,null,null,null,null,null,null);

        while (cursor.moveToNext()){

            long id = cursor.getLong(cursor.getColumnIndex(COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
            String address = cursor.getString(cursor.getColumnIndex(COL_ADDRESS));
            String icon = cursor.getString(cursor.getColumnIndex(COL_ICON));
            double late = cursor.getDouble(cursor.getColumnIndex(COL_LATE));
            double longi = cursor.getDouble(cursor.getColumnIndex(COL_LONGI));

            placesFavo.add(new Place(id,name,address,icon,late,longi));

        }
        database.close();
        return placesFavo;
    }


}
