package com.example.jbt.placeofzeze.model;

import java.io.Serializable;

public class Place implements Serializable{
    private long id;
    private String name , address,icon;
    private double late,longi;

    public Place(long id, String name, String address, String icon, double late, double longi) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.icon = icon;
        this.late = late;
        this.longi = longi;
    }

    public Place(String name, String address, String icon, double late, double longi) {
        this.name = name;
        this.address = address;
        this.icon = icon;
        this.late = late;
        this.longi = longi;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getLate() {
        return late;
    }

    public void setLate(double late) {
        this.late = late;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    @Override
    public String toString() {
        return name;
    }
}
